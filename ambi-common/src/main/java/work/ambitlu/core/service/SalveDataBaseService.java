package work.ambitlu.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import work.ambitlu.core.domian.QysDataSource;
import work.ambitlu.dynamic.datasource.annotation.DS;
import work.ambitlu.dynamic.datasource.autoconfigure.DataSourceProperty;

import java.util.List;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 */
public interface SalveDataBaseService extends IService<QysDataSource> {

	@DS("qys")
	List<String> dataBase();

	DataSourceProperty changeDataBase(String dataBaseName);

	DataSourceProperty changeDataBase(DataSourceProperty dataSourceProperty);

	DataSourceProperty get(String poolName);

}
