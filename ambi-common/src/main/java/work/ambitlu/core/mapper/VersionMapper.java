package work.ambitlu.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import work.ambitlu.core.domian.Version;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021-04-27 16:05
 */
@Mapper
public interface VersionMapper extends BaseMapper<Version> {
}
