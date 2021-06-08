package work.ambitlu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import work.ambitlu.entity.Company;

/**
 * <p>
 * 公司信息表 Mapper 接口
 * </p>
 *
 * @author Ambi
 * @since 2021-05-17
 */
public interface CompanyMapper extends BaseMapper<Company> {

	Company platform();

}
