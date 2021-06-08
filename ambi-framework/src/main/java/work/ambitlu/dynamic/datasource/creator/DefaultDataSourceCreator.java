package work.ambitlu.dynamic.datasource.creator;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import work.ambitlu.dynamic.datasource.autoconfigure.DataSourceProperty;
import work.ambitlu.dynamic.datasource.autoconfigure.DynamicDataSourceProperties;
import work.ambitlu.dynamic.datasource.ds.ItemDataSource;

import javax.sql.DataSource;
import java.util.List;

/**
 * 默认数据源创建器
 *
 * @author Ambi 赵帅
 * @date 2021-04-27 21:25
 */
@Slf4j
@Setter
public class DefaultDataSourceCreator {

	private DynamicDataSourceProperties properties;
	private List<DataSourceCreator> creators;

	public DataSource createDataSource(DataSourceProperty dataSourceProperty){
		DataSourceCreator dataSourceCreator = getDadaSourceCreator(dataSourceProperty);

		if (dataSourceCreator == null) {
			throw new IllegalStateException("creator must not be null,please check the DataSourceCreator");
		}
		String publicKey = dataSourceProperty.getPublicKey();
		if (StringUtils.isEmpty(publicKey)) {
			dataSourceProperty.setPublicKey(properties.getPublicKey());
		}

		Boolean lazy = dataSourceProperty.getLazy();
		if (lazy == null) {
			dataSourceProperty.setLazy(properties.getLazy());
		}

		DataSource dataSource = dataSourceCreator.createDataSource(dataSourceProperty);
		return wrapDataSource(dataSource, dataSourceProperty);
	}

	private DataSourceCreator getDadaSourceCreator(DataSourceProperty dataSourceProperty) {
		for (DataSourceCreator creator : this.creators) {
			if (creator.support(dataSourceProperty)) {
				return creator;
			}
		}
		return null;
	}

	private DataSource wrapDataSource(DataSource dataSource, DataSourceProperty dataSourceProperty) {
		String name = dataSourceProperty.getPoolName();
		return new ItemDataSource(name, dataSource,dataSourceProperty);
	}

}
