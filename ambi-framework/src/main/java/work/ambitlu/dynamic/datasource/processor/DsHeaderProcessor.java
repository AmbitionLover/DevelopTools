package work.ambitlu.dynamic.datasource.processor;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 从Header中取
 *
 * @author Ambi 赵帅
 */
public class DsHeaderProcessor extends DsProcessor {

	/**
	 * header prefix
	 */
	private static final String HEADER_PREFIX = "#header";

	@Override
	public boolean matches(String key) {
		return key.startsWith(HEADER_PREFIX);
	}

	@Override
	public String doDetermineDatasource(MethodInvocation invocation, String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getHeader(key.substring(8));
	}
}
