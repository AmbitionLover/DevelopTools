package work.ambitlu.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.ambitlu.core.domian.AjaxResult;
import work.ambitlu.core.service.VersionService;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021-04-25 13:55
 */
@RequestMapping("/version")
@RestController
public class VersionController {

	@Autowired
	private VersionService versionService;


	@GetMapping
	public AjaxResult version(){
		return AjaxResult.success(versionService.list().get(0).getName());
	}


}
