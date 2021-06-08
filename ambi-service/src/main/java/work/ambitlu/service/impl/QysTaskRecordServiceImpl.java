package work.ambitlu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import work.ambitlu.domain.OperatorType;
import work.ambitlu.entity.QysTaskRecord;
import work.ambitlu.mapper.QysTaskRecordMapper;
import work.ambitlu.service.IQysTaskRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ambi
 * @since 2021-06-08
 */
@Service
public class QysTaskRecordServiceImpl extends ServiceImpl<QysTaskRecordMapper, QysTaskRecord> implements IQysTaskRecordService {

	@Autowired
	private QysTaskRecordMapper qysTaskRecordMapper;

	@Override
	public List<QysTaskRecord> queryAllByType(OperatorType type) {
		return qysTaskRecordMapper.queryAllByType(type.name());
	}
}
