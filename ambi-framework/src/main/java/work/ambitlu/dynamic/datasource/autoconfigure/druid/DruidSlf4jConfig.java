package work.ambitlu.dynamic.datasource.autoconfigure.druid;

import lombok.Data;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021-04-27 21:07
 */
@Data
public class DruidSlf4jConfig {

	private Boolean enable = true;

	private Boolean statementExecutableSqlLogEnable = false;
}
