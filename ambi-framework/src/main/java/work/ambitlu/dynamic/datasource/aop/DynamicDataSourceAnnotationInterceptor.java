package work.ambitlu.dynamic.datasource.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import work.ambitlu.dynamic.datasource.ds.DynamicDataSourceContextHolder;
import work.ambitlu.dynamic.datasource.processor.DsProcessor;
import work.ambitlu.dynamic.datasource.support.DataSourceClassResolver;

/**
 * Core Interceptor of Dynamic Datasource
 *
 * @author Ambi 赵帅
 */
public class DynamicDataSourceAnnotationInterceptor implements MethodInterceptor {

	/**
	 * The identification of SPEL.
	 */
	private static final String DYNAMIC_PREFIX = "#";

	private final DataSourceClassResolver dataSourceClassResolver;
	private final DsProcessor dsProcessor;

	public DynamicDataSourceAnnotationInterceptor(Boolean allowedPublicOnly, DsProcessor dsProcessor) {
		dataSourceClassResolver = new DataSourceClassResolver(allowedPublicOnly);
		this.dsProcessor = dsProcessor;
	}

	/**
	 * 多数据源之间的相互调用执行
	 */
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		String dsKey = determineDatasourceKey(invocation);
		DynamicDataSourceContextHolder.push(dsKey);
		try {
			return invocation.proceed();
		} finally {
			DynamicDataSourceContextHolder.poll();
		}
	}


	private String determineDatasourceKey(MethodInvocation invocation) {
		String key = dataSourceClassResolver.findDSKey(invocation.getMethod(), invocation.getThis());
		return (!key.isEmpty() && key.startsWith(DYNAMIC_PREFIX)) ? dsProcessor.determineDatasource(invocation, key) : key;
	}

}
