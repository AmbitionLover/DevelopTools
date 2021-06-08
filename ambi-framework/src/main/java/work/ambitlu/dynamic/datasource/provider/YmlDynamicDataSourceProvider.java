package work.ambitlu.dynamic.datasource.provider;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import work.ambitlu.dynamic.datasource.autoconfigure.DataSourceProperty;

import javax.sql.DataSource;
import java.util.Map;

/**
 * YML数据源提供者
 *
 * @author Ambi 赵帅
 * @date 2021-04-28 14:07
 */
@Slf4j
@AllArgsConstructor
public class YmlDynamicDataSourceProvider extends AbstractDataSourceProvider {

	/**
	 * 所有数据源
	 */
	private final Map<String, DataSourceProperty> dataSourcePropertiesMap;

	@Override
	public Map<String, DataSource> loadDataSources() {
		return createDataSourceMap(dataSourcePropertiesMap);
	}
}
