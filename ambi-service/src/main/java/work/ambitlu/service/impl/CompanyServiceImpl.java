package work.ambitlu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.ambitlu.dynamic.datasource.annotation.DS;
import work.ambitlu.entity.Company;
import work.ambitlu.mapper.CompanyMapper;
import work.ambitlu.service.ICompanyService;

/**
 * <p>
 * 公司信息表 服务实现类
 * </p>
 *
 * @author Ambi
 * @since 2021-05-17
 */
@Service
@DS("qys")
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {

	@Autowired
	private CompanyMapper companyMapper;


	@Override
	public Company platform() {
		return companyMapper.platform();
	}
}
