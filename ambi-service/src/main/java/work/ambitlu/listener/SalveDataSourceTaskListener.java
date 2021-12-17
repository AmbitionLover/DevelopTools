package work.ambitlu.listener;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import work.ambitlu.bean.event.AppTokenTaskEvent;
import work.ambitlu.core.service.SalveDataBaseService;
import work.ambitlu.domain.OperatorType;
import work.ambitlu.entity.QysTaskRecord;
import work.ambitlu.service.IQysTaskRecordService;

/**
 * 副数据源 执行记录
 *
 * @author Ambi 赵帅
 */
@Component
@AllArgsConstructor
public class SalveDataSourceTaskListener {

	@Autowired
	private SalveDataBaseService salveDataBaseService;
	@Autowired
	private IQysTaskRecordService qysTaskRecordService;

	@EventListener(AppTokenTaskEvent.class)
	public void AppTokenEvent(AppTokenTaskEvent event){
		QysTaskRecord qysTaskRecord = new QysTaskRecord();
		qysTaskRecord.setSourceName(event.getSourceName());
		qysTaskRecord.setType(OperatorType.APPTOKEN);
		qysTaskRecordService.save(qysTaskRecord);
	}


}
