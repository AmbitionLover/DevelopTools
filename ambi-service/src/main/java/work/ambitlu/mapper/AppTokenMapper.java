package work.ambitlu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import work.ambitlu.entity.AppToken;

/**
 * <p>
 * 企业接入令牌表 Mapper 接口
 * </p>
 *
 * @author 李磊
 * @since 2021-04-25
 */
public interface AppTokenMapper extends BaseMapper<AppToken> {

	AppToken get(String token);

}
