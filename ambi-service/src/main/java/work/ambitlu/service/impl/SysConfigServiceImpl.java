package work.ambitlu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements ISysConfigService {

	@Autowired
	private QysSysConfigServiceImpl qysSysConfigService;

	@Override
	public Object getConfigValue(String key) {
		QueryWrapper<SysConfig> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("configKey", key);
		SysConfig one = this.getOne(queryWrapper);
		return one.getConfigValue();
	}

	@Override
	public Integer getVersion() {
		String version = null;
		try {
			version = (String) getConfigValue("VERSION");
		} catch (Exception e) {
			log.error("SysConfig系统配置获取失败,采用QysSysConfig");
			version = (String) qysSysConfigService.getConfigValue("VERSION");
		}
		return Integer.parseInt(version.replace(".", "").substring(0,3));
	}
}
