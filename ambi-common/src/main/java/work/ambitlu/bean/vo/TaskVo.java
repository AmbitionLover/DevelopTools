package work.ambitlu.bean.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 任务执行器 执行结果
 *
 * @author Ambi 赵帅
 */
@Data
public class TaskVo {

	private Integer total;
	private Integer success = 0;
	private Integer error = 0;
	private Map<String, String> errorMessage = new HashMap<>();

	public void addSuccess(){
		this.success++;
	}

	public void addError(){
		this.error++;
	}

	public void addErrorMessage(String key,String value){
		addError();
		this.errorMessage.put(key,value);
	}
}
