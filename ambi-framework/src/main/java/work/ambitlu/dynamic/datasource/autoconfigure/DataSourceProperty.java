package work.ambitlu.dynamic.datasource.autoconfigure;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.util.StringUtils;
import work.ambitlu.dynamic.datasource.autoconfigure.druid.DruidConfig;
import work.ambitlu.dynamic.datasource.autoconfigure.hikari.HikariCpConfig;
import work.ambitlu.utils.CryptoUtils;

import javax.sql.DataSource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据源
 *
 * @author Ambi 赵帅
 * @date 2021-04-27 19:16
 */
@Slf4j
@Data
@Accessors(chain = true)
public class DataSourceProperty {

	/**
	 * 加密正则
	 */
	private static final Pattern ENC_PATTERN = Pattern.compile("^ENC\\((.*)\\)$");

	/**
	 * 连接池名称(只是一个名称标识)</br> 默认是配置文件上的名称
	 */
	private String poolName;
	/**
	 * 连接池类型，如果不设置自动查找 Druid > HikariCp
	 */
	private Class<? extends DataSource> type;
	/**
	 * JDBC driver
	 */
	private String driverClassName;
	/**
	 * JDBC url 地址
	 */
	private String url;
	/**
	 * JDBC 用户名
	 */
	private String username;
	/**
	 * JDBC 密码
	 */
	private String password;
	/**
	 * jndi数据源名称(设置即表示启用)
	 */
	private String jndiName;
	/**
	 * 自动运行的建表脚本
	 */
	private String schema;
	/**
	 * 自动运行的数据脚本
	 */
	private String data;
	/**
	 *
	 */
	private Boolean seata = true;
	/**
	 *
	 */
	private Boolean p6spy = true;
	/**
	 * lazy init datasource
	 */
	private Boolean lazy;
	/**
	 * 错误是否继续 默认 true
	 */
	private boolean continueOnError = true;
	/**
	 * 分隔符 默认 ;
	 */
	private String separator = ";";
	/**
	 * Druid参数配置
	 */
	@NestedConfigurationProperty
	private DruidConfig druid = new DruidConfig();
	/**
	 * HikariCp参数配置
	 */
	@NestedConfigurationProperty
	private HikariCpConfig hikari = new HikariCpConfig();

	/**
	 * 解密公匙(如果未设置默认使用全局的)
	 */
	private String publicKey;

	public String getUrl() {
		return decrypt(url);
	}

	public String getUsername() {
		return decrypt(username);
	}

	public String getPassword() {
		return decrypt(password);
	}

	/**
	 * 字符串解密
	 */
	private String decrypt(String cipherText) {
		if (StringUtils.hasText(cipherText)) {
			Matcher matcher = ENC_PATTERN.matcher(cipherText);
			if (matcher.find()) {
				try {
					return CryptoUtils.decrypt(publicKey, matcher.group(1));
				} catch (Exception e) {
					log.error("DynamicDataSourceProperties.decrypt error ", e);
				}
			}
		}
		return cipherText;
	}

}
