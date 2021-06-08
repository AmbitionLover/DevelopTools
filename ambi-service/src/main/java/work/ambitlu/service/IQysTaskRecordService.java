package work.ambitlu.service;

import work.ambitlu.domain.OperatorType;
import work.ambitlu.entity.QysTaskRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ambi
 * @since 2021-06-08
 */
public interface IQysTaskRecordService extends IService<QysTaskRecord> {

	List<QysTaskRecord> queryAllByType(OperatorType type);

}
