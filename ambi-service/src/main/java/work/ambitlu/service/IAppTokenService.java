package work.ambitlu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import work.ambitlu.entity.AppToken;


/**
 * <p>
 * 企业接入令牌表 服务类
 * </p>
 *
 * @author 李磊
 * @since 2021-04-25
 */
public interface IAppTokenService extends IService<AppToken> {

	AppToken create();

	String de(String token);

	void delete();

}
