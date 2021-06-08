package work.ambitlu.dynamic.datasource.autoconfigure.druid;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import com.alibaba.druid.spring.boot.autoconfigure.stat.DruidFilterConfiguration;
import com.alibaba.druid.spring.boot.autoconfigure.stat.DruidSpringAopConfiguration;
import com.alibaba.druid.spring.boot.autoconfigure.stat.DruidStatViewServletConfiguration;
import com.alibaba.druid.spring.boot.autoconfigure.stat.DruidWebStatFilterConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 从原生DruidDataSourceAutoConfigure复制
 *
 * @author Ambi 赵帅
 * @date 2021-04-27 21:07
 */
@Configuration
@ConditionalOnClass(DruidDataSourceAutoConfigure.class)
@EnableConfigurationProperties({DruidStatProperties.class})
@Import({
		DruidSpringAopConfiguration.class,
		DruidStatViewServletConfiguration.class,
		DruidWebStatFilterConfiguration.class,
		DruidFilterConfiguration.class})
public class DruidDynamicDataSourceConfiguration {

}
