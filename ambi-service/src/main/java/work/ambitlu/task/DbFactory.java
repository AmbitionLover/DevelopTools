package work.ambitlu.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import work.ambitlu.bean.event.AppTokenTaskEvent;
import work.ambitlu.bean.vo.TaskVo;

/**
 * work.ambitlu.task
 * 用于修复数据的问题
 * @author Ambi 赵帅
 * @Month 09
 */
@Component
@Slf4j
public class DbFactory implements AbstractTaskFactory{

	@Autowired
	private ApplicationContext applicationContext;


	@Override
	public TaskVo executeTask() {
		String dbName = "QDSTDJC_TEST,GQBT,WSWEI";
		String[] strings = dbName.split(",");
		for (String string : strings) {
			applicationContext.publishEvent(new AppTokenTaskEvent(string));
		}
		return new TaskVo();
	}
}
