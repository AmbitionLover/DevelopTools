package ambitlu.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import work.ambitlu.AmbiAdminApplication;
import work.ambitlu.entity.AppToken;
import work.ambitlu.service.IAppTokenService;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021-04-26 15:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AmbiAdminApplication.class)
class AppTokenServiceImplTest {

	@Autowired
	private IAppTokenService appTokenService;

	@Test
	public void create(){
		AppToken appToken = appTokenService.create();
		System.out.println(appToken);
	}

	@Test
	public void de(){

	}

}