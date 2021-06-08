package work.ambitlu.dynamic.datasource.autoconfigure;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import work.ambitlu.dynamic.datasource.creator.*;

import java.util.List;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021-04-28 14:03
 */
@Slf4j
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(DynamicDataSourceProperties.class)
public class DynamicDataSourceCreatorAutoConfiguration {

	public static final int DRUID_ORDER = 10;
	public static final int HIKARI_ORDER = 20;
	public static final int DEFAULT_ORDER = 100;

	private final DynamicDataSourceProperties properties;

	@Primary
	@Bean
	@ConditionalOnMissingBean
	public DefaultDataSourceCreator dataSourceCreator(List<DataSourceCreator> dataSourceCreators) {
		DefaultDataSourceCreator defaultDataSourceCreator = new DefaultDataSourceCreator();
		defaultDataSourceCreator.setProperties(properties);
		defaultDataSourceCreator.setCreators(dataSourceCreators);
		return defaultDataSourceCreator;
	}

	@Bean
	@Order(DEFAULT_ORDER)
	@ConditionalOnMissingBean
	public BasicDataSourceCreator basicDataSourceCreator() {
		return new BasicDataSourceCreator();
	}


	/**
	 * 存在Druid数据源时, 加入创建器
	 */
	@ConditionalOnClass(DruidDataSource.class)
	@Configuration
	public class DruidDataSourceCreatorConfiguration {
		@Bean
		@Order(DRUID_ORDER)
		@ConditionalOnMissingBean
		public DruidDataSourceCreator druidDataSourceCreator() {
			return new DruidDataSourceCreator(properties.getDruid());
		}

	}


	/**
	 * 存在Hikari数据源时, 加入创建器
	 */
	@ConditionalOnClass(HikariDataSource.class)
	@Configuration
	public class HikariDataSourceCreatorConfiguration {
		@Bean
		@Order(HIKARI_ORDER)
		@ConditionalOnMissingBean
		public HikariDataSourceCreator hikariDataSourceCreator() {
			return new HikariDataSourceCreator(properties.getHikari());
		}
	}



}
