package com.funpay.management.model.request;

import com.funpay.model.dto.StepPolicy;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Leeko
 * @date 2022/2/26
 **/
@Data
public class StepFeeVO {

    /**
     * 描述
     */
    private String description;

    /**
     * 值
     */
    @NotNull
    private List<StepPolicy> value;
}
