package work.ambitlu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import work.ambitlu.entity.Company;

/**
 * <p>
 * 公司信息表 服务类
 * </p>
 *
 * @author Ambi
 * @since 2021-05-17
 */
public interface ICompanyService extends IService<Company> {

	Company platform();


}
