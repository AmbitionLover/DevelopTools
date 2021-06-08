package work.ambitlu.dynamic.datasource.creator;

import lombok.AllArgsConstructor;
import lombok.Data;
import work.ambitlu.dynamic.datasource.autoconfigure.DataSourceProperty;
import work.ambitlu.dynamic.datasource.autoconfigure.druid.DruidConfig;

import javax.sql.DataSource;

import static work.ambitlu.dynamic.datasource.support.DdConstants.DRUID_DATASOURCE;

/**
 * Druid数据源创建器
 *
 * @author Ambi 赵帅
 * @date 2021-04-27 21:33
 */
@Data
@AllArgsConstructor
public class DruidDataSourceCreator implements DataSourceCreator{

	private static Boolean druidExists = false;

	static {
		try {
			Class.forName(DRUID_DATASOURCE.getValue());
			druidExists = true;
		} catch (ClassNotFoundException ignored) {
		}
	}

	private DruidConfig gConfig;

	@Override
	public DataSource createDataSource(DataSourceProperty dataSourceProperty) {
		return null;
	}

	@Override
	public boolean support(DataSourceProperty dataSourceProperty) {
		return false;
	}
}
