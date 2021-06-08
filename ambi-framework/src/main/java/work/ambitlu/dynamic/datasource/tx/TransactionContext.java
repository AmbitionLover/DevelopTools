package work.ambitlu.dynamic.datasource.tx;


import org.apache.commons.lang3.StringUtils;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021-04-28 14:25
 */
public class TransactionContext {

	private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

	/**
	 * Gets xid.
	 *
	 * @return the xid
	 */
	public static String getXID() {
		String xid = CONTEXT_HOLDER.get();
		if (!StringUtils.isEmpty(xid)) {
			return xid;
		}
		return null;
	}

	/**
	 * Unbind string.
	 *
	 * @return the string
	 */
	public static String unbind(String xid) {
		CONTEXT_HOLDER.remove();
		return xid;
	}

	/**
	 * bind string.
	 *
	 * @return the string
	 */
	public static String bind(String xid) {
		CONTEXT_HOLDER.set(xid);
		return xid;
	}

	/**
	 * remove
	 */
	public static void remove() {
		CONTEXT_HOLDER.remove();
	}

}
