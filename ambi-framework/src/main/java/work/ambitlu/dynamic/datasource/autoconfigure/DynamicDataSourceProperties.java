package work.ambitlu.dynamic.datasource.autoconfigure;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.core.Ordered;
import work.ambitlu.dynamic.datasource.autoconfigure.druid.DruidConfig;
import work.ambitlu.dynamic.datasource.autoconfigure.hikari.HikariCpConfig;
import work.ambitlu.utils.CryptoUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * DynamicDataSourceProperties
 *
 * 从配置文件中取数据源
 *
 * @author Ambi 赵帅
 * @see DataSourceProperties
 * @date 2021-04-27 19:10
 */
@Slf4j
@Getter
@Setter
@ConfigurationProperties(prefix = DynamicDataSourceProperties.PREFIX)
public class DynamicDataSourceProperties {

	public static final String PREFIX = "spring.datasource.dynamic";
	public static final String HEALTH = PREFIX + ".health";
	public static final String DEFAULT_VALID_QUERY = "SELECT 1";

	/**
	 * 必须设置默认的库,默认master
	 */
	private String primary = "master";
	/**
	 * 是否启用严格模式,默认不启动. 严格模式下未匹配到数据源直接报错, 非严格模式下则使用默认数据源primary所设置的数据源
	 */
	private Boolean strict = false;
	/**
	 * 是否使用p6spy输出，默认不输出
	 */
	private Boolean p6spy = false;
	/**
	 * 是否使用开启seata，默认不开启
	 */
	private Boolean seata = false;
	/**
	 * 是否懒加载数据源
	 */
	private Boolean lazy = false;
	/**
	 * 是否使用 spring actuator 监控检查，默认不检查
	 */
	private boolean health = false;
	/**
	 * 监控检查SQL
	 */
	private String healthValidQuery = DEFAULT_VALID_QUERY;
	/**
	 * 每一个数据源
	 */
	private Map<String, DataSourceProperty> datasource = new LinkedHashMap<>();
	/**
	 * aop切面顺序，默认优先级最高
	 */
	private Integer order = Ordered.HIGHEST_PRECEDENCE;
	/**
	 * Druid全局参数配置
	 */
	@NestedConfigurationProperty
	private DruidConfig druid = new DruidConfig();
	/**
	 * HikariCp全局参数配置
	 */
	@NestedConfigurationProperty
	private HikariCpConfig hikari = new HikariCpConfig();

	/**
	 * 全局默认publicKey
	 */
	private String publicKey = CryptoUtils.DEFAULT_PUBLIC_KEY_STRING;
	/**
	 * aop 切面是否只允许切 public 方法
	 */
	private boolean allowedPublicOnly = true;

}
