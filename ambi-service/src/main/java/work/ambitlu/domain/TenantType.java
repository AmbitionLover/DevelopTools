package work.ambitlu.domain;

/**
 * 租户类型
 *
 * @author Ambi 赵帅
 */
public enum TenantType {

	CORPORATE, // 平台方
	COMPANY,//平台外部企业
	INNER_COMPANY,//平台企业（内部）
	PERSONAL,//个人

	P_BATCH, //个人批量  批量合同草稿阶段使用
	C_BATCH,  //公司批量  批量合同草稿阶段使用
	S_BATCH;  //批量单签 批量合同草稿阶段使用


}
