package work.ambitlu.domain;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 */
public enum TenantStatus {
	UNREGISTERED("未注册"),
	REGISTERED("已注册"),
	CERTIFYING("认证中"),
	AUTH_SUCCESS("认证完成"),
	AUTH_FAILURE("认证失败");

	private String description;

	private TenantStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
