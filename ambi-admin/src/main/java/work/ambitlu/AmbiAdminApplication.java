package work.ambitlu;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 管理端启动程序
 *
 * @author Ambi 赵帅
 * @date 2021-04-25 13:56
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
@MapperScan({"work.ambitlu.mapper","work.ambitlu.core.mapper"})
public class AmbiAdminApplication {


	public static void main(String[] args) {
		SpringApplication.run(AmbiAdminApplication.class, args);
		System.out.println("(♥◠‿◠)ﾉﾞ  Ambi -  契约锁工具类 启动成功   ლ(´ڡ`ლ)ﾞ  \n" );
	}

}
