package work.ambitlu.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.ambitlu.core.domian.AjaxResult;

import java.util.*;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 */
@RestController
@RequestMapping("/userInfo")
public class UserController {

	@PostMapping
	public AjaxResult userinfo(){
		Map<String, Object> map = new HashMap<>();
		map.put("roles", Collections.singletonList("admin"));
		List<String> ability = new ArrayList<>();
		ability.add("READ");
		ability.add("WRITE");
		ability.add("DELETE");
		map.put("ability", ability);
		map.put("username", "admin");
		return AjaxResult.success(map);
	}


}
