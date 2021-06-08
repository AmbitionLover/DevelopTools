package work.ambitlu.dynamic.datasource.provider;

import lombok.extern.slf4j.Slf4j;
import work.ambitlu.dynamic.datasource.autoconfigure.DataSourceProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * 从数据库中获取数据配置
 *
 * @author Ambi 赵帅
 */
@Slf4j
public class DbDynamicDataSourceProvider extends AbstractJdbcDataSourceProvider {

	private final String SQL = "select * from qys_db";


	public DbDynamicDataSourceProvider(DataSourceProperty master) {
		super(null,master.getUrl(), master.getUsername(), master.getPassword());
	}

	@Override
	protected Map<String, DataSourceProperty> executeStmt(Statement statement) throws SQLException {
		Map<String, DataSourceProperty> map = new HashMap<>();
		ResultSet rs = statement.executeQuery(SQL);
		while (rs.next()) {
			String name = rs.getString("name");
			String username = rs.getString("username");
			String password = rs.getString("password");
			String url = rs.getString("url");
			String driver = rs.getString("driver");
			DataSourceProperty property = new DataSourceProperty();
			property.setUsername(username);
			property.setPassword(password);
			property.setUrl(url);
			property.setDriverClassName(driver);
			map.put(name, property);
		}
		return map;
	}
}
