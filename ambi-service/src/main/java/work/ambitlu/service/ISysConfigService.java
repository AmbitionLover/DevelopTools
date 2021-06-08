package work.ambitlu.service;

import work.ambitlu.entity.SysConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统配置表 服务类
 * </p>
 *
 * @author Ambi
 * @since 2021-06-03
 */
public interface ISysConfigService extends IService<SysConfig> {

	Object getConfigValue(String key);

	Integer getVersion();

}
