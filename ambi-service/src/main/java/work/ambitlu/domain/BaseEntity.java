package work.ambitlu.domain;

import com.baomidou.mybatisplus.annotation.TableId;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 */
public class BaseEntity {

	@TableId
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
