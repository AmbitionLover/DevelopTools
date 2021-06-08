package work.ambitlu.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.ambitlu.core.domian.AjaxResult;

import java.util.HashMap;
import java.util.Map;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 */
@RestController
@RequestMapping("/login")
public class Logcontroller {


	@PostMapping()
	public AjaxResult login(){
		String accessToken = "token";
		Map<String, String> map = new HashMap<>();
		map.put("accessToken",accessToken);
		return AjaxResult.success(map);
	}




}
