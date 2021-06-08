package work.ambitlu.dynamic.datasource.aop;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import work.ambitlu.dynamic.datasource.tx.ConnectionFactory;
import work.ambitlu.dynamic.datasource.tx.TransactionContext;

import java.util.UUID;

/**
 * 事务切面
 *
 * @author Ambi 赵帅
 */
@Slf4j
public class DynamicLocalTransactionAdvisor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		if (!StringUtils.isEmpty(TransactionContext.getXID())) {
			return methodInvocation.proceed();
		}
		boolean state = true;
		Object o;
		String xid = UUID.randomUUID().toString();
		TransactionContext.bind(xid);
		try {
			o = methodInvocation.proceed();
		} catch (Exception e) {
			state = false;
			throw e;
		} finally {
			ConnectionFactory.notify(state);
			TransactionContext.remove();
		}
		return o;
	}
}
