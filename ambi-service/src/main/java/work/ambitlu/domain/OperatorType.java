package work.ambitlu.domain;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 */
public enum OperatorType {

	APPTOKEN("app_token"),
	OTHERS("app_token");

	private final String deps;


	OperatorType(String deps) {
		this.deps = deps;
	}
}
