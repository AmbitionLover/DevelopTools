package work.ambitlu.core.controller;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.ambitlu.bean.dto.DataSourceDTO;
import work.ambitlu.bean.vo.DataSourcePropertyVo;
import work.ambitlu.core.domian.AjaxResult;
import work.ambitlu.core.service.SalveDataBaseService;
import work.ambitlu.dynamic.datasource.DynamicRoutingDataSource;
import work.ambitlu.dynamic.datasource.autoconfigure.DataSourceProperty;
import work.ambitlu.dynamic.datasource.creator.DefaultDataSourceCreator;
import work.ambitlu.dynamic.datasource.ds.ItemDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 */
@RestController
@RequestMapping("/core/datasource")
public class DynamicDataSourceController {

	@Autowired
	private  DefaultDataSourceCreator dataSourceCreator; //3.3.2及以上版本使用這個
	@Autowired
	private MapperFactory mapperFactory;
	@Autowired
	private SalveDataBaseService salveDataBaseService;
	@Autowired
	private DataSource dataSource;


	@PostMapping("/add")
	public AjaxResult add(DataSourceDTO dto){

		DataSourceProperty dataSourceProperty = new DataSourceProperty();
		BeanUtils.copyProperties(dto, dataSourceProperty);
		DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
		DataSource dataSource = dataSourceCreator.createDataSource(dataSourceProperty);
		ds.addDataSource(dto.getPollName(), dataSource);

		return AjaxResult.success(ds.getCurrentDataSources().keySet());
	}

	@GetMapping("/qys/add")
	public AjaxResult qysAdd(String name){

		DataSourceProperty dataSourceProperty = salveDataBaseService.changeDataBase(name);

		return AjaxResult.success(dataSourceProperty);
	}

	@GetMapping("/get")
	public AjaxResult getDataSourcePropertyByPool(DataSourcePropertyVo vo){
		return AjaxResult.success(get(vo.getPoolName()));
	}

	private DataSourceProperty get(String poolName){
		DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
		ItemDataSource source = (ItemDataSource) ds.getDataSource(poolName);
		return source.getDataSourceProperty();
	}



	@GetMapping
	public AjaxResult now(){
		DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;

		Map<String, DataSource> currentDataSources = ds.getCurrentDataSources();

		Collection<DataSource> values = currentDataSources.values();

		List<DataSourceProperty> dataSourcePropertyList = new ArrayList<>(values.size());

		for (DataSource value : values) {
			ItemDataSource itemDataSource = (ItemDataSource)value;
			dataSourcePropertyList.add(itemDataSource.getDataSourceProperty());
		}

		List<DataSourcePropertyVo> dataSourcePropertyVos = mapperFactory.getMapperFacade().mapAsList(dataSourcePropertyList, DataSourcePropertyVo.class);


		return AjaxResult.success(dataSourcePropertyVos);
	}

	@GetMapping("/databases")
	public AjaxResult dataBases(){
		List<String> stringList = salveDataBaseService.dataBase();
		return AjaxResult.success(stringList);
	}

}
