package work.ambitlu.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.ambitlu.core.domian.AjaxResult;
import work.ambitlu.task.AppTokenFactory;

/**
 * 任务执行器
 *
 * @author Ambi 赵帅
 */
@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private AppTokenFactory appTokenFactory;

	@GetMapping("/apptoken")
	public AjaxResult appTokenTask(){
		return AjaxResult.success(appTokenFactory.executeTask());
	}


}
