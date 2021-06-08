package work.ambitlu.dynamic.datasource.support;

/**
 * 动态数据源常量
 *
 * @author Ambi 赵帅
 * @date 2021-04-27 21:35
 */
public enum DdConstants {

	MASTER("master"),
	SLAVE("slave"),
	DRUID_DATASOURCE("com.alibaba.druid.pool.DruidDataSource"),
	HIKARI_DATASOURCE("com.zaxxer.hikari.HikariDataSource"),
	BEECP_DATASOURCE("cn.beecp.BeeDataSource"),
	DBCP2_DATASOURCE("org.apache.commons.dbcp2.BasicDataSource")
	;

	private String value;

	DdConstants(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
