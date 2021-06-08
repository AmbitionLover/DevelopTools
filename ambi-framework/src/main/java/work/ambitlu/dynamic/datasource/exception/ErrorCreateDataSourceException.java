package work.ambitlu.dynamic.datasource.exception;

/**
 * exception when  druid dataSource init failed
 *
 * @author Ambi 赵帅
 * @date 2021-04-27 21:41
 */
public class ErrorCreateDataSourceException extends RuntimeException {

	public ErrorCreateDataSourceException(String message) {
		super(message);
	}

	public ErrorCreateDataSourceException(String message, Throwable cause) {
		super(message, cause);
	}
}
