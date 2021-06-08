package work.ambitlu.dynamic.datasource.exception;

/**
 * exception when dataSource cannot select
 *
 * @author Ambi 赵帅
 * @date 2021-04-28 14:58
 */
public class CannotFindDataSourceException extends RuntimeException {

	public CannotFindDataSourceException(String message) {
		super(message);
	}

	public CannotFindDataSourceException(String message, Throwable cause) {
		super(message, cause);
	}
}
