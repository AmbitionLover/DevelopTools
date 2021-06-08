package work.ambitlu.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.ambitlu.core.domian.AjaxResult;
import work.ambitlu.core.service.SalveDataBaseService;

/**
 * 通用方法类
 *
 * @author Ambi 赵帅
 */
@RestController
@RequestMapping("/common")
public class CommonController {

	@Autowired
	private SalveDataBaseService salveDataBaseService;

	@GetMapping("/datasource")
	public AjaxResult dataSource(){
		return AjaxResult.success(salveDataBaseService.dataBase());
	}

}
