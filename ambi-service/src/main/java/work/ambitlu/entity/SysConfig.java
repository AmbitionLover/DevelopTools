package work.ambitlu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import work.ambitlu.domain.BaseEntity;

/**
 * <p>
 * 系统配置表
 * </p>
 *
 * @author Ambi
 * @since 2021-06-03
 */
@TableName("SYS_CONFIG")
@ApiModel(value="SysConfig对象", description="系统配置表")
public class SysConfig extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String configKey;

    private String configValue;

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }
    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
