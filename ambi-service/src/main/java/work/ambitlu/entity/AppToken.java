package work.ambitlu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import work.ambitlu.domain.BaseEntity;

import java.util.Date;

/**
 * <p>
 * 企业接入令牌表
 * </p>
 *
 * @author Ambi
 * @since 2021-06-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("APP_TOKEN")
@ApiModel(value="AppToken对象", description="企业接入令牌表")
public class AppToken extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "令牌")
    private String accessToken;

    @ApiModelProperty(value = "密钥")
    private String accessSecret;

    @ApiModelProperty(value = "默认发起主体id")
    private Long defaultSignatory;

    @ApiModelProperty(value = "接入权限")
    private Integer privilege;

    @ApiModelProperty(value = "接入应用名称")
    private String name;

    @ApiModelProperty(value = "回调地址")
    private String callbackUrl;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "是否已经接入")
    private Boolean used;

    @ApiModelProperty(value = "第三方用户认证配置")
    private String thirdUserAuth;

    @ApiModelProperty(value = "第三方企业认证配置")
    private String thirdCompanyAuth;

    @ApiModelProperty(value = "印章范围类型")
    private String availableSealType;

    @ApiModelProperty(value = "ip 名单类型")
    private String ipListType;

    @ApiModelProperty(value = "ip 名单类型")
    private String ipList;

    @ApiModelProperty(value = "有效期")
    private String validTime;

    @ApiModelProperty(value = "事件类型回调配置")
    private String callback;

    @ApiModelProperty(value = "回调配置类型")
    private String callbackConfig;


}
