package work.ambitlu.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import work.ambitlu.bean.vo.SwaggerProperties;


/**
 * Swagger2API文档的配置
 *
 * @author Ambi 赵帅
 * @date 2021/1/9 17:21
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

	@Override
	public SwaggerProperties swaggerProperties() {
		return SwaggerProperties.builder()
				.apiBasePackage("work.ambitlu.web.controller;work.ambitlu.core.controller")
				.title("ZLG后台系统")
				.description("ZLG前台相关接口文档")
				.contactName("Ambi")
				.version("1.0")
				.enableSecurity(true)
				.build();
	}
}