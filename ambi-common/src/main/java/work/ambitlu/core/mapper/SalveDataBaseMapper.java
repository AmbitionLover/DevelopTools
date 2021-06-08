package work.ambitlu.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import work.ambitlu.core.domian.QysDataSource;

import java.util.List;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 */
@Mapper
public interface SalveDataBaseMapper extends BaseMapper<QysDataSource> {


	List<String> databases();

}
