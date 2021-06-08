package work.ambitlu.core.domian;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021-04-27 16:01
 */
@Data
public class Version {

	@TableId
	private Long id;

	private String name;

}
