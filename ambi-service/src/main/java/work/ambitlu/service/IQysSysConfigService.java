package work.ambitlu.service;

import work.ambitlu.entity.QysSysConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统配置表 服务类
 * </p>
 *
 * @author Ambi
 * @since 2021-10-12
 */
public interface IQysSysConfigService extends IService<QysSysConfig> {

	Object getConfigValue(String key);

}
