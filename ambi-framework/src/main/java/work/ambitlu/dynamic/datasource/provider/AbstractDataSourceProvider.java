package work.ambitlu.dynamic.datasource.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import work.ambitlu.dynamic.datasource.autoconfigure.DataSourceProperty;
import work.ambitlu.dynamic.datasource.creator.DefaultDataSourceCreator;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021-04-28 14:08
 */
@Slf4j
public abstract class AbstractDataSourceProvider implements DynamicDataSourceProvider {

	@Autowired
	private DefaultDataSourceCreator defaultDataSourceCreator;

	protected Map<String, DataSource> createDataSourceMap(Map<String, DataSourceProperty> dataSourcePropertiesMap) {
		Map<String, DataSource> dataSourceMap = new HashMap<>(dataSourcePropertiesMap.size() * 2);
		for (Map.Entry<String, DataSourceProperty> item : dataSourcePropertiesMap.entrySet()) {
			DataSourceProperty dataSourceProperty = item.getValue();
			String poolName = dataSourceProperty.getPoolName();
			if (poolName == null || "".equals(poolName)) {
				poolName = item.getKey();
			}
			dataSourceProperty.setPoolName(poolName);
			dataSourceMap.put(poolName, defaultDataSourceCreator.createDataSource(dataSourceProperty));
		}
		return dataSourceMap;
	}
}
