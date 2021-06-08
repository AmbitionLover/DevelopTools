package work.ambitlu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import work.ambitlu.entity.SysConfig;
import work.ambitlu.mapper.SysConfigMapper;
import work.ambitlu.service.ISysConfigService;

/**
 * <p>
 * 系统配置表 服务实现类
 * </p>
 *
 * @author Ambi
 * @since 2021-06-03
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements ISysConfigService {

	@Override
	public Object getConfigValue(String key) {
		QueryWrapper<SysConfig> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("configKey", key);
		SysConfig one = this.getOne(queryWrapper);
		return one.getConfigValue();
	}

	@Override
	public Integer getVersion() {
		String version = (String) getConfigValue("VERSION");
		return Integer.parseInt(version.replace(".", "").substring(0,3));
	}
}
