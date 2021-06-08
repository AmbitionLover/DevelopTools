package work.ambitlu.dynamic.datasource.annotation;

import java.lang.annotation.*;

/**
 * multi data source transaction
 *
 * @author Ambi 赵帅
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DSTransactional {
}

