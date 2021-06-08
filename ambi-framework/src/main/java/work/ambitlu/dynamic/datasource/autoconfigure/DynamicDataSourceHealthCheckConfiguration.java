package work.ambitlu.dynamic.datasource.autoconfigure;

import org.springframework.context.annotation.Configuration;

/**
 * 数据源健康状态检查
 *
 * @author Ambi 赵帅
 * @date 2021-04-28 14:04
 */
@Configuration
public class DynamicDataSourceHealthCheckConfiguration {

	private static final String DYNAMIC_HEALTH_CHECK = DynamicDataSourceProperties.PREFIX + ".health";



}
