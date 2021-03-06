package work.ambitlu.bean.vo;

import lombok.Data;

import javax.sql.DataSource;
import java.io.Serializable;

/**
 * DataSourceProperty的vo
 *
 * @author Ambi 赵帅
 */
@Data
public class DataSourcePropertyVo implements Serializable {


	private static final long serialVersionUID = 1L;

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

}
