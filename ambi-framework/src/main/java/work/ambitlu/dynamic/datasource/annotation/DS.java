package work.ambitlu.dynamic.datasource.annotation;

import java.lang.annotation.*;

/**
 * 数据源注解
 * 需要指定数据操作的
 *
 * @author Ambi 赵帅
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DS {

	/**
	 * groupName or specific database name or spring SPEL name.
	 *
	 * @return the database you want to switch
	 */
	String value();
}
