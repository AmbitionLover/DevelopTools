package work.ambitlu.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021-04-27 15:28
 */
@Controller
public class webController {


	@GetMapping("/")
	public String index(){
		return "index";
	}

}
