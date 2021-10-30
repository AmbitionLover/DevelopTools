package work.ambitlu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import work.ambitlu.entity.QysSysConfig;
import work.ambitlu.mapper.QysSysConfigMapper;
import work.ambitlu.service.IQysSysConfigService;

/**
 * <p>
 * 系统配置表 服务实现类
 * </p>
 *
 * @author Ambi
 * @since 2021-10-12
 */
@Service
public class QysSysConfigServiceImpl extends ServiceImpl<QysSysConfigMapper, QysSysConfig> implements IQysSysConfigService {

	@Override
	public Object getConfigValue(String key) {
		QueryWrapper<QysSysConfig> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("configKey", key);
		QysSysConfig one = this.getOne(queryWrapper);
		return one.getConfigValue();
	}

}
