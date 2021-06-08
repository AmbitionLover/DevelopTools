package work.ambitlu.dynamic.datasource.ds;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.AbstractDataSource;
import work.ambitlu.dynamic.datasource.tx.ConnectionFactory;
import work.ambitlu.dynamic.datasource.tx.ConnectionProxy;
import work.ambitlu.dynamic.datasource.tx.TransactionContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 抽象动态获取数据源
 *
 * @author Ambi 赵帅
 * @date 2021-04-28 14:22
 */
public abstract class AbstractRoutingDataSource extends AbstractDataSource {

	protected abstract DataSource determineDataSource();

	@Override
	public Connection getConnection() throws SQLException {
		String xid = TransactionContext.getXID();
		if (StringUtils.isEmpty(xid)) {
			return determineDataSource().getConnection();
		} else {
			String ds = DynamicDataSourceContextHolder.peek();
			ConnectionProxy connection = ConnectionFactory.getConnection(ds);
			return connection == null ? getConnectionProxy(ds, determineDataSource().getConnection()) : connection;
		}
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		String xid = TransactionContext.getXID();
		if (StringUtils.isEmpty(xid)) {
			return determineDataSource().getConnection(username, password);
		} else {
			String ds = DynamicDataSourceContextHolder.peek();
			ConnectionProxy connection = ConnectionFactory.getConnection(ds);
			return connection == null ? getConnectionProxy(ds, determineDataSource().getConnection(username, password))
					: connection;
		}
	}

	private Connection getConnectionProxy(String ds, Connection connection) {
		ConnectionProxy connectionProxy = new ConnectionProxy(connection, ds);
		ConnectionFactory.putConnection(ds, connectionProxy);
		return connectionProxy;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T unwrap(Class<T> iface) throws SQLException {
		if (iface.isInstance(this)) {
			return (T) this;
		}
		return determineDataSource().unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return (iface.isInstance(this) || determineDataSource().isWrapperFor(iface));
	}
}
