package work.ambitlu.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import work.ambitlu.core.domian.Version;
import work.ambitlu.core.mapper.VersionMapper;
import work.ambitlu.core.service.VersionService;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021-04-27 16:03
 */
@Service
public class VersionServiceImpl extends ServiceImpl<VersionMapper, Version> implements VersionService {
}
