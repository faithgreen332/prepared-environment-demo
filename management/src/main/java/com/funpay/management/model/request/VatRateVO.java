package com.funpay.management.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Leeko
 * @date 2022/2/26
 **/
@Data
public class VatRateVO {

    private String description;

    @NotNull
    private BigDecimal value;
}
