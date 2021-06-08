package work.ambitlu.dynamic.datasource.ds;

import lombok.Data;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021-04-28 14:37
 */
@Data
public class GroupDataSource {

	private String groupName;


	private Map<String, DataSource> dataSourceMap = new ConcurrentHashMap<>();

	public GroupDataSource(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * add a new datasource to this group
	 *
	 * @param ds         the name of the datasource
	 * @param dataSource datasource
	 */
	public DataSource addDatasource(String ds, DataSource dataSource) {
		return dataSourceMap.put(ds, dataSource);
	}

	/**
	 * @param ds the name of the datasource
	 */
	public DataSource removeDatasource(String ds) {
		return dataSourceMap.remove(ds);
	}


	public int size() {
		return dataSourceMap.size();
	}
}