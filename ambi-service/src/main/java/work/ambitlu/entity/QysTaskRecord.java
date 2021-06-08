package work.ambitlu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import work.ambitlu.domain.BaseEntity;
import work.ambitlu.domain.OperatorType;

/**
 * <p>
 * 
 * </p>
 *
 * @author Ambi
 * @since 2021-06-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("qys_task_record")
@ApiModel(value="QysTaskRecord对象", description="")
public class QysTaskRecord extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String sourceName;

    @ApiModelProperty(value = "记录类型")
    private OperatorType type;


}
