package work.ambitlu.dynamic.datasource.creator;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import work.ambitlu.dynamic.datasource.autoconfigure.DataSourceProperty;
import work.ambitlu.dynamic.datasource.autoconfigure.hikari.HikariCpConfig;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static work.ambitlu.dynamic.datasource.support.DdConstants.HIKARI_DATASOURCE;

/**
 * Hikari数据源创建器
 * 这个是boot项目自带的
 *
 * @author Ambi 赵帅
 * @date 2021-04-28 15:27
 */
@Data
@AllArgsConstructor
public class HikariDataSourceCreator implements DataSourceCreator {

	private static Boolean hikariExists = false;
	private static Method configCopyMethod = null;

	static {

		try {
			Class.forName(HIKARI_DATASOURCE.getValue());
			hikariExists = true;
			fetchMethod();
		} catch (ClassNotFoundException ignored) {
		}
	}

	private HikariCpConfig hikariCpConfig;

	/**
	 * to support springboot 1.5 and 2.x
	 * HikariConfig 2.x use 'copyState' to copy config
	 * HikariConfig 3.x use 'copyStateTo' to copy config
	 */
	@SuppressWarnings("JavaReflectionMemberAccess")
	private static void fetchMethod() {
		try {
			configCopyMethod = HikariConfig.class.getMethod("copyState", HikariConfig.class);
			return;
		} catch (NoSuchMethodException ignored) {
		}

		try {
			configCopyMethod = HikariConfig.class.getMethod("copyStateTo", HikariConfig.class);
			return;
		} catch (NoSuchMethodException ignored) {
		}
		throw new RuntimeException("HikariConfig does not has 'copyState' or 'copyStateTo' method!");
	}

	@Override
	public DataSource createDataSource(DataSourceProperty dataSourceProperty) {
		HikariConfig config = dataSourceProperty.getHikari().toHikariConfig(hikariCpConfig);
		config.setUsername(dataSourceProperty.getUsername());
		config.setPassword(dataSourceProperty.getPassword());
		config.setJdbcUrl(dataSourceProperty.getUrl());
		config.setPoolName(dataSourceProperty.getPoolName());
		String driverClassName = dataSourceProperty.getDriverClassName();
		if (!StringUtils.isEmpty(driverClassName)) {
			config.setDriverClassName(driverClassName);
		}
		if (!dataSourceProperty.getLazy()) {
			return new HikariDataSource(config);
		}
		config.validate();
		HikariDataSource dataSource = new HikariDataSource();
		try {
			configCopyMethod.invoke(config, dataSource);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException("HikariConfig failed to copy to HikariDataSource", e);
		}
		return dataSource;
	}

	@Override
	public boolean support(DataSourceProperty dataSourceProperty) {
		Class<? extends DataSource> type = dataSourceProperty.getType();
		return (type == null && hikariExists) || (type != null && HIKARI_DATASOURCE.equals(type.getName()));
	}
}
