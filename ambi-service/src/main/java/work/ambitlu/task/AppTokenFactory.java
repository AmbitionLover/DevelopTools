package work.ambitlu.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import work.ambitlu.bean.event.AppTokenTaskEvent;
import work.ambitlu.bean.vo.TaskVo;
import work.ambitlu.core.service.SalveDataBaseService;
import work.ambitlu.domain.OperatorType;
import work.ambitlu.dynamic.datasource.autoconfigure.DataSourceProperty;
import work.ambitlu.entity.QysTaskRecord;
import work.ambitlu.service.IAppTokenService;
import work.ambitlu.service.IQysTaskRecordService;

import java.util.List;

/**
 * 系统令牌执行任务
 *
 * @author Ambi 赵帅
 */
@Component
@Slf4j
public class AppTokenFactory implements AbstractTaskFactory{

	@Autowired
	private SalveDataBaseService salveDataBaseService;
	@Autowired
	private IAppTokenService appTokenService;
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private IQysTaskRecordService qysTaskRecordService;

	@Override
	public TaskVo executeTask() {
		log.info("startIng AppTokenFactory.......");

		TaskVo taskVo = new TaskVo();

		/*
		1. 获取所有数据源 暂存当前操作数据源
		过滤出需要执行的
		 */
		List<String> stringList = salveDataBaseService.dataBase();

		List<QysTaskRecord> qysTaskRecords = qysTaskRecordService.queryAllByType(OperatorType.APPTOKEN);

		stringList.removeIf(s -> qysTaskRecords.stream().anyMatch(qysTaskRecord -> qysTaskRecord.getSourceName().equals(s)));

		taskVo.setTotal(stringList.size());

		DataSourceProperty qys = salveDataBaseService.get("qys");

		/*
		 * 2、对每个数据源执行 create Apptoken
		 * 3、记录每次执行的成功与否
		 */
		for (String dataSourceName : stringList) {
			try {
				log.debug("startIng changeDataBase.......dataSourceName:{}",dataSourceName);
				log.info(dataSourceName + "////////");
				salveDataBaseService.changeDataBase(dataSourceName);
				appTokenService.create();
				taskVo.addSuccess();
				applicationContext.publishEvent(new AppTokenTaskEvent(dataSourceName));
			} catch (Exception e) {
				taskVo.addErrorMessage(dataSourceName ,e.getMessage());
			} finally {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					log.error("线程暂停失败:{}",e.getMessage());
				}
			}
		}
		/*
		复原 操作数据库
		 */
		salveDataBaseService.changeDataBase(qys);
		log.info("stop AppTokenFactory.......");
		return taskVo;
	}
}
