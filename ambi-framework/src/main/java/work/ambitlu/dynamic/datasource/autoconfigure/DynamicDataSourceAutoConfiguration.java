package work.ambitlu.dynamic.datasource.autoconfigure;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Role;
import work.ambitlu.dynamic.datasource.DynamicRoutingDataSource;
import work.ambitlu.dynamic.datasource.aop.DynamicDataSourceAnnotationAdvisor;
import work.ambitlu.dynamic.datasource.aop.DynamicDataSourceAnnotationInterceptor;
import work.ambitlu.dynamic.datasource.autoconfigure.druid.DruidDynamicDataSourceConfiguration;
import work.ambitlu.dynamic.datasource.processor.DsHeaderProcessor;
import work.ambitlu.dynamic.datasource.processor.DsProcessor;
import work.ambitlu.dynamic.datasource.provider.DbDynamicDataSourceProvider;
import work.ambitlu.dynamic.datasource.provider.DynamicDataSourceProvider;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 动态数据源核心自动配置类
 *
 * @author Ambi 赵帅
 * @date 2021-04-27 19:10
 */
@Slf4j
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(DynamicDataSourceProperties.class)
@AutoConfigureBefore(value = DataSourceAutoConfiguration.class, name = "com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure")
@Import(value = {DruidDynamicDataSourceConfiguration.class, DynamicDataSourceCreatorAutoConfiguration.class, DynamicDataSourceHealthCheckConfiguration.class})
@ConditionalOnProperty(prefix = DynamicDataSourceProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class DynamicDataSourceAutoConfiguration {


	private final DynamicDataSourceProperties properties;


	@Bean
	@ConditionalOnMissingBean
	public DynamicDataSourceProvider dynamicDataSourceProvider() {
		Map<String, DataSourceProperty> datasourceMap = properties.getDatasource();
		return new DbDynamicDataSourceProvider(datasourceMap.get("master"));
	}

	@Bean
	@ConditionalOnMissingBean
	public DataSource dataSource(DynamicDataSourceProvider dynamicDataSourceProvider) {
		DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource();
		dataSource.setPrimary(properties.getPrimary());
		dataSource.setStrict(properties.getStrict());
		dataSource.setProvider(dynamicDataSourceProvider);
		return dataSource;
	}

	@Role(value = BeanDefinition.ROLE_INFRASTRUCTURE)
	@Bean
	public Advisor dynamicDatasourceAnnotationAdvisor(DsProcessor dsProcessor) {
		DynamicDataSourceAnnotationInterceptor interceptor = new DynamicDataSourceAnnotationInterceptor(properties.isAllowedPublicOnly(), dsProcessor);
		DynamicDataSourceAnnotationAdvisor advisor = new DynamicDataSourceAnnotationAdvisor(interceptor);
		advisor.setOrder(properties.getOrder());
		return advisor;
	}

	//@Role(value = BeanDefinition.ROLE_INFRASTRUCTURE)
	//@ConditionalOnProperty(prefix = DynamicDataSourceProperties.PREFIX, name = "seata", havingValue = "false", matchIfMissing = true)
	//@Bean
	//public Advisor dynamicTransactionAdvisor() {
	//	AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
	//	pointcut.setExpression("@annotation(com.baomidou.dynamic.datasource.annotation.DSTransactional)");
	//	return new DefaultPointcutAdvisor(pointcut, new DynamicLocalTransactionAdvisor());
	//}

	/**
	 * 动态处理器
	 */
	@Bean
	@ConditionalOnMissingBean
	public DsProcessor dsProcessor(BeanFactory beanFactory) {
		DsHeaderProcessor headerProcessor = new DsHeaderProcessor();
		return headerProcessor;
	}

}
