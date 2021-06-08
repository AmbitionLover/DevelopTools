package work.ambitlu.core.domian;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 */
@TableName("qys_db")
@Data
public class QysDataSource {

	@TableId
	private Long id;

	private String name;
	private String username;
	private String password;
	private String url;
	private String driver;

}
