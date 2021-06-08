package work.ambitlu.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import work.ambitlu.service.IAppTokenService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021-04-26 15:00
 */
@RunWith(SpringRunner.class)
class AppTokenServiceImplTest {

	@Autowired
	private IAppTokenService appTokenService;

	@Test
	public void create(){
		String gx14moF8G1 = appTokenService.de("gx14moF8G1");
		System.out.println(gx14moF8G1);
	}

}