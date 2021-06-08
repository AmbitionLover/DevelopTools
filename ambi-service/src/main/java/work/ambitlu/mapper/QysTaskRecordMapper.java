package work.ambitlu.mapper;

import work.ambitlu.entity.QysTaskRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Ambi
 * @since 2021-06-08
 */
public interface QysTaskRecordMapper extends BaseMapper<QysTaskRecord> {

	List<QysTaskRecord> queryAllByType(String type);

}
