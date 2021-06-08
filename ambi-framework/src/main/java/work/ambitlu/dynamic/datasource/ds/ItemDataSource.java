package work.ambitlu.dynamic.datasource.ds;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.AbstractDataSource;
import work.ambitlu.dynamic.datasource.autoconfigure.DataSourceProperty;

import javax.sql.DataSource;
import java.io.Closeable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021-04-27 22:19
 */
@Slf4j
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ItemDataSource extends AbstractDataSource implements Closeable {

	private String name;

	private DataSource realDataSource;

	private DataSourceProperty dataSourceProperty;


	@Override
	public Connection getConnection() throws SQLException {
		return realDataSource.getConnection();
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		return realDataSource.getConnection(username, password);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return super.isWrapperFor(iface) || iface.isInstance(realDataSource) || iface.isInstance(dataSourceProperty);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T unwrap(Class<T> iface) {
		if (iface.isInstance(this)) {
			return (T) this;
		}
		if (iface.isInstance(realDataSource)) {
			return (T) realDataSource;
		}
		if (iface.isInstance(dataSourceProperty)) {
			return (T) dataSourceProperty;
		}
		return null;
	}

	@Override
	public void close() {
		Class<? extends DataSource> clazz = realDataSource.getClass();
		try {
			Method closeMethod = clazz.getDeclaredMethod("close");
			closeMethod.invoke(realDataSource);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			log.warn("dynamic-datasource close the datasource named [{}] failed,", name, e);
		}
	}
}
