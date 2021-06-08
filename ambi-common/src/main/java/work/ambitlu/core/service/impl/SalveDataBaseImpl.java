package work.ambitlu.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.ambitlu.core.domian.QysDataSource;
import work.ambitlu.core.mapper.SalveDataBaseMapper;
import work.ambitlu.core.service.SalveDataBaseService;
import work.ambitlu.dynamic.datasource.DynamicRoutingDataSource;
import work.ambitlu.dynamic.datasource.annotation.DS;
import work.ambitlu.dynamic.datasource.autoconfigure.DataSourceProperty;
import work.ambitlu.dynamic.datasource.creator.DefaultDataSourceCreator;
import work.ambitlu.dynamic.datasource.ds.ItemDataSource;
import work.ambitlu.utils.StringUtils;

import javax.sql.DataSource;
import java.util.List;

/**
 * 副数据库操作
 *
 * @author Ambi 赵帅
 */
@Service
public class SalveDataBaseImpl extends ServiceImpl<SalveDataBaseMapper, QysDataSource> implements SalveDataBaseService {

	@Autowired
	private SalveDataBaseMapper salveDataBaseMapper;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private DefaultDataSourceCreator dataSourceCreator; //3.3.2及以上版本使用這個


	@DS("qys")
	@Override
	public List<String> dataBase() {
		return salveDataBaseMapper.databases();
	}

	@Override
	public DataSourceProperty changeDataBase(String dataBaseName) {
		DataSourceProperty dataSourceProperty = get("qys");

		String baseUrl = "jdbc:mysql://192.168.1.140:3306/{}?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
		String url = StringUtils.format(baseUrl,dataBaseName);
		dataSourceProperty.setUrl(url);

		return changeDataBase(dataSourceProperty);
	}

	@Override
	public DataSourceProperty changeDataBase(DataSourceProperty dataSourceProperty) {
		DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
		DataSource dataSource = dataSourceCreator.createDataSource(dataSourceProperty);
		ds.addDataSource("qys", dataSource);
		QueryWrapper<QysDataSource> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("name", "qys");
		QysDataSource one = this.getOne(queryWrapper);
		one.setUrl(dataSourceProperty.getUrl());
		this.updateById(one);
		return dataSourceProperty;
	}

	@Override
	public DataSourceProperty get(String poolName){
		DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
		ItemDataSource source = (ItemDataSource) ds.getDataSource(poolName);
		return source.getDataSourceProperty();
	}
}
