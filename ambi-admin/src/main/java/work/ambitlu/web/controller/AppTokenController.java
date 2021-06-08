package work.ambitlu.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.ambitlu.core.domian.AjaxResult;
import work.ambitlu.service.IAppTokenService;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021-04-25 17:26
 */
@RestController
@RequestMapping("/apptoken")
public class AppTokenController {

	@Autowired
	private IAppTokenService appTokenService;

	@GetMapping
	public AjaxResult save(){
		return AjaxResult.success(appTokenService.create());
	}

	@DeleteMapping
	public AjaxResult delete(){
		appTokenService.delete();
		return AjaxResult.success();
	}


	@GetMapping("/get")
	public AjaxResult get(){
		return AjaxResult.success(appTokenService.de("ZhaoShuaiA"));
	}


}
