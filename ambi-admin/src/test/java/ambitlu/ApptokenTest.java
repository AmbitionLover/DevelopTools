package ambitlu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import work.ambitlu.service.IAppTokenService;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021-04-26 14:49
 */
@SpringBootTest
public class ApptokenTest {

	@Autowired
	private IAppTokenService appTokenService;

	@Test
	public void create(){
		String gx14moF8G1 = appTokenService.de("gx14moF8G1");
		System.out.println(gx14moF8G1);
	}

}
