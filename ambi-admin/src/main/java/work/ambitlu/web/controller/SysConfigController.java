package work.ambitlu.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.ambitlu.core.controller.BaseController;
import work.ambitlu.core.domian.AjaxResult;
import work.ambitlu.mapper.SysConfigMapper;

import javax.xml.ws.Action;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021-04-25 16:33
 */
@RestController
@RequestMapping("/sysconfig")
public class SysConfigController extends BaseController {

	@Autowired
	private SysConfigMapper sysConfigMapper;

	@GetMapping
	public AjaxResult getALl(){
		return AjaxResult.success(sysConfigMapper.selectList(null));
	}

}
