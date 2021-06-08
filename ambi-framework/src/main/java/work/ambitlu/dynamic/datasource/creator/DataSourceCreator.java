package work.ambitlu.dynamic.datasource.creator;

import work.ambitlu.dynamic.datasource.autoconfigure.DataSourceProperty;

import javax.sql.DataSource;

/**
 * 默认按照以下顺序创建数据源:
 * <pre>
 * 	DRUID(10) &gt;  BASIC(50)
 * </pre>
 *
 * @author Ambi 赵帅
 * @date 2021-04-27 21:11
 */
public interface DataSourceCreator {

	/**
	 * 通过属性创建数据源
	 *
	 * @param dataSourceProperty 数据源属性
	 * @return 被创建的数据源
	 */
	DataSource createDataSource(DataSourceProperty dataSourceProperty);

	/**
	 * 当前创建器是否支持根据此属性创建
	 *
	 * @param dataSourceProperty 数据源属性
	 * @return 是否支持
	 */
	boolean support(DataSourceProperty dataSourceProperty);
}
