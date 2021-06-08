package work.ambitlu.dynamic.datasource;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import work.ambitlu.dynamic.datasource.ds.AbstractRoutingDataSource;
import work.ambitlu.dynamic.datasource.ds.DynamicDataSourceContextHolder;
import work.ambitlu.dynamic.datasource.ds.GroupDataSource;
import work.ambitlu.dynamic.datasource.ds.ItemDataSource;
import work.ambitlu.dynamic.datasource.exception.CannotFindDataSourceException;
import work.ambitlu.dynamic.datasource.provider.DynamicDataSourceProvider;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 核心动态数据源组件
 *
 * @author Ambi 赵帅
 * @date 2021-04-28 14:36
 */
@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource implements InitializingBean, DisposableBean {

	private static final String UNDERLINE = "_";

	/**
	 * 所有数据库
	 */
	private final Map<String, DataSource> dataSourceMap = new ConcurrentHashMap<>();
	/**
	 * 分组数据库
	 */
	private final Map<String, GroupDataSource> groupDataSources = new ConcurrentHashMap<>();
	@Setter
	private DynamicDataSourceProvider provider;
	@Setter
	private String primary = "master";
	@Setter
	private Boolean strict = false;


	@Override
	public void destroy() throws Exception {
		log.info("dynamic-datasource start closing ....");
		for (Map.Entry<String, DataSource> item : dataSourceMap.entrySet()) {
			closeDataSource(item.getValue());
		}
		log.info("dynamic-datasource all closed success,bye");
	}

	/**
	 * 关闭数据源
	 */
	private void closeDataSource(DataSource dataSource) throws Exception {
		((ItemDataSource) dataSource).close();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// 添加数据源
		Map<String, DataSource> dataSources = provider.loadDataSources();
		for (Map.Entry<String, DataSource> dsItem : dataSources.entrySet()) {
			addDataSource(dsItem.getKey(), dsItem.getValue());
		}
		// 检测默认数据源是否设置
		if (dataSourceMap.containsKey(primary)) {
			log.info("dynamic-datasource initial loaded [{}] datasource,primary datasource named [{}]", dataSources.size(), primary);
		} else {
			log.warn("dynamic-datasource initial loaded [{}] datasource,Please add your primary datasource or check your configuration", dataSources.size());
		}
	}

	/**
	 * 添加数据源
	 *
	 * @param ds         数据源名称
	 * @param dataSource 数据源
	 */
	public synchronized void addDataSource(String ds, DataSource dataSource) {
		DataSource oldDataSource = dataSourceMap.put(ds, dataSource);
		// 关闭老的数据源
		if (oldDataSource != null) {
			try {
				closeDataSource(oldDataSource);
			} catch (Exception e) {
				log.error("dynamic-datasource - remove the database named [{}]  failed", ds, e);
			}
		}
		log.info("dynamic-datasource - add a datasource named [{}] success", ds);
	}

	/**
	 * 删除数据源
	 *
	 * @param ds 数据源名称
	 */
	public synchronized void removeDataSource(String ds) {
		if (StringUtils.isBlank(ds)) {
			throw new RuntimeException("remove parameter could not be empty");
		}
		if (primary.equals(ds)) {
			throw new RuntimeException("could not remove primary datasource");
		}
		if (dataSourceMap.containsKey(ds)) {
			DataSource dataSource = dataSourceMap.remove(ds);
			try {
				closeDataSource(dataSource);
			} catch (Exception e) {
				log.error("dynamic-datasource - remove the database named [{}]  failed", ds, e);
			}

			if (ds.contains(UNDERLINE)) {
				String group = ds.split(UNDERLINE)[0];
				if (groupDataSources.containsKey(group)) {
					DataSource oldDataSource = groupDataSources.get(group).removeDatasource(ds);
					if (oldDataSource == null) {
						if (log.isWarnEnabled()) {
							log.warn("fail for remove datasource from group. dataSource: {} ,group: {}", ds, group);
						}
					}
				}
			}
			log.info("dynamic-datasource - remove the database named [{}] success", ds);
		} else {
			log.warn("dynamic-datasource - could not find a database named [{}]", ds);
		}
	}

	@Override
	protected DataSource determineDataSource() {
		String dsKey = DynamicDataSourceContextHolder.peek();
		return getDataSource(dsKey);
	}

	/**
	 * 获取当前所有的数据源
	 *
	 * @return 当前所有数据源
	 */
	public Map<String, DataSource> getCurrentDataSources() {
		return dataSourceMap;
	}

	/**
	 * 获取数据源
	 *
	 * @param ds 数据源名称
	 * @return 数据源
	 */
	public DataSource getDataSource(String ds) {
		if (StringUtils.isEmpty(ds)) {
			return determinePrimaryDataSource();
		} else if (dataSourceMap.containsKey(ds)) {
			log.debug("dynamic-datasource switch to the datasource named [{}]", ds);
			return dataSourceMap.get(ds);
		}
		if (strict) {
			throw new CannotFindDataSourceException("dynamic-datasource could not find a datasource named" + ds);
		}
		return determinePrimaryDataSource();
	}

	private DataSource determinePrimaryDataSource() {
		log.debug("dynamic-datasource switch to the primary datasource");
		DataSource dataSource = dataSourceMap.get(primary);
		if (dataSource != null) {
			return dataSource;
		}
		throw new CannotFindDataSourceException("dynamic-datasource can not find primary datasource");
	}
}
