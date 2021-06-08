package work.ambitlu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import work.ambitlu.domain.BaseEntity;

import java.time.LocalDateTime;

/**
 * <p>
 * 公司信息表
 * </p>
 *
 * @author Ambi
 * @since 2021-05-18
 */
@ApiModel(value="Company对象", description="公司信息表")
@TableName("COMPANY")
public class Company extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "公司ID")
    private Long id;

    @ApiModelProperty(value = "公司名称")
    private String name;

    private String registerNo;

    @ApiModelProperty(value = "公司地址")
    private String address;

    @ApiModelProperty(value = "认证状态")
    private String status;

    private String mobile;

    private String email;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "企业类型")
    private String tenantType;

    @ApiModelProperty(value = "是否冻结账号")
    private Boolean freeze;

    @ApiModelProperty(value = "负责人姓名")
    private String charger;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "法定代表人姓名")
    private String legalPerson;

    private String legalPersonId;

    @ApiModelProperty(value = "法定代表人证件类型")
    private String paperType;

    @ApiModelProperty(value = "营业执照")
    private String license;

    @ApiModelProperty(value = "授权书")
    private String legalAuthorization;

    @ApiModelProperty(value = "公司LOGO")
    private String logo;

    @ApiModelProperty(value = "公司分类标签")
    private String label;

    @ApiModelProperty(value = "公司来源")
    private String source;

    @ApiModelProperty(value = "公司开放平台id")
    private String openCompanyId;

    @ApiModelProperty(value = "外部企业是否可以发送合同开关")
    private Boolean sendContractSwitch;

    @ApiModelProperty(value = "外部企业发送合同对象开关")
    private Boolean sendObjectControl;

    private Boolean applyIndependent;

    @ApiModelProperty(value = "加密盐值")
    private String salt;

    @ApiModelProperty(value = "手机号摘要")
    private String mobileHash;

    @ApiModelProperty(value = "邮箱摘要")
    private String emailHash;

    @ApiModelProperty(value = "法人证件号摘要")
    private String legalPersonIdHash;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public String getTenantType() {
        return tenantType;
    }

    public void setTenantType(String tenantType) {
        this.tenantType = tenantType;
    }
    public Boolean getFreeze() {
        return freeze;
    }

    public void setFreeze(Boolean freeze) {
        this.freeze = freeze;
    }
    public String getCharger() {
        return charger;
    }

    public void setCharger(String charger) {
        this.charger = charger;
    }
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }
    public String getLegalPersonId() {
        return legalPersonId;
    }

    public void setLegalPersonId(String legalPersonId) {
        this.legalPersonId = legalPersonId;
    }
    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }
    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }
    public String getLegalAuthorization() {
        return legalAuthorization;
    }

    public void setLegalAuthorization(String legalAuthorization) {
        this.legalAuthorization = legalAuthorization;
    }
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    public String getOpenCompanyId() {
        return openCompanyId;
    }

    public void setOpenCompanyId(String openCompanyId) {
        this.openCompanyId = openCompanyId;
    }
    public Boolean getSendContractSwitch() {
        return sendContractSwitch;
    }

    public void setSendContractSwitch(Boolean sendContractSwitch) {
        this.sendContractSwitch = sendContractSwitch;
    }
    public Boolean getSendObjectControl() {
        return sendObjectControl;
    }

    public void setSendObjectControl(Boolean sendObjectControl) {
        this.sendObjectControl = sendObjectControl;
    }
    public Boolean getApplyIndependent() {
        return applyIndependent;
    }

    public void setApplyIndependent(Boolean applyIndependent) {
        this.applyIndependent = applyIndependent;
    }
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    public String getMobileHash() {
        return mobileHash;
    }

    public void setMobileHash(String mobileHash) {
        this.mobileHash = mobileHash;
    }
    public String getEmailHash() {
        return emailHash;
    }

    public void setEmailHash(String emailHash) {
        this.emailHash = emailHash;
    }
    public String getLegalPersonIdHash() {
        return legalPersonIdHash;
    }

    public void setLegalPersonIdHash(String legalPersonIdHash) {
        this.legalPersonIdHash = legalPersonIdHash;
    }

    @Override
    public String toString() {
        return "Company{" +
            "id=" + id +
            ", name=" + name +
            ", registerNo=" + registerNo +
            ", address=" + address +
            ", status=" + status +
            ", mobile=" + mobile +
            ", email=" + email +
            ", createTime=" + createTime +
            ", tenantType=" + tenantType +
            ", freeze=" + freeze +
            ", charger=" + charger +
            ", province=" + province +
            ", city=" + city +
            ", legalPerson=" + legalPerson +
            ", legalPersonId=" + legalPersonId +
            ", paperType=" + paperType +
            ", license=" + license +
            ", legalAuthorization=" + legalAuthorization +
            ", logo=" + logo +
            ", label=" + label +
            ", source=" + source +
            ", openCompanyId=" + openCompanyId +
            ", sendContractSwitch=" + sendContractSwitch +
            ", sendObjectControl=" + sendObjectControl +
            ", applyIndependent=" + applyIndependent +
            ", salt=" + salt +
            ", mobileHash=" + mobileHash +
            ", emailHash=" + emailHash +
            ", legalPersonIdHash=" + legalPersonIdHash +
        "}";
    }
}
