package work.ambitlu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.ambitlu.dynamic.datasource.annotation.DS;
import work.ambitlu.entity.AppToken;
import work.ambitlu.entity.Company;
import work.ambitlu.mapper.AppTokenMapper;
import work.ambitlu.service.IAppTokenService;
import work.ambitlu.service.ICompanyService;
import work.ambitlu.service.ISysConfigService;
import work.ambitlu.utils.SecretAesUtil;

import java.util.Date;


/**
 * <p>
 * 企业接入令牌表 服务实现类
 * </p>
 *
 * @author Ambi
 * @since 2021-04-25
 */
@Service
public class AppTokenServiceImpl extends ServiceImpl<AppTokenMapper, AppToken> implements IAppTokenService {


	private final String TOKEN_NAME = "ZhaoShuaiA";
	private final String TOKEN_SECRET = "e32s/kykomJee67jfpcpKP5hAuAxico6O+pwCBYli7c=";

	@Autowired
	private ICompanyService companyService;
	@Autowired
	private ISysConfigService sysConfigService;
	@Autowired
	private AppTokenMapper appTokenMapper;

	@DS("qys")
	@Override
	public AppToken create() {


		Integer version = sysConfigService.getVersion();

		AppToken appToken = new AppToken();

		AppToken one = getAppToken(TOKEN_NAME);

		if (one != null){
			return one;
		}

		appToken.setAccessSecret(TOKEN_SECRET);
		appToken.setAccessToken(TOKEN_NAME);
		appToken.setName("Ambi~自动化工具专用");

		appToken.setUsed(true);
		appToken.setCreateTime(new Date());

		if (version > 290){
			appToken.setAvailableSealType("ALL");
		}

		if (version > 410){
			appToken.setValidTime("LONG_TERM");
		}

		if (version > 423){
			appToken.setCallbackConfig("GLOBAL");
		}

		if (version > 420){
			Company platform = companyService.platform();
			appToken.setDefaultSignatory(platform.getId());
			appToken.setAvailableSealType("ALL");
		}

		this.save(appToken);
		return appToken;
	}

	private AppToken getAppToken(String token) {
		return appTokenMapper.get(token);
	}

	@Override
	public String de(String token) {
		AppToken one = getAppToken(token);
		String s = null;
		try {
			s = SecretAesUtil.secretDecrypt(token, one.getAccessSecret());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public static void main(String[] args) {
		try {
			String s6HTEoBWd0 = SecretAesUtil.secretDecrypt("S6HTEoBWd0", "BE+9rM1hlCAEZITvQUK08R1KRTi3W0wPPa0m4zDBo7I=");
			System.out.println(s6HTEoBWd0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete() {
		AppToken one = getAppToken(TOKEN_NAME);

		if (one != null){
			this.removeById(one.getId());
		}
	}
}
