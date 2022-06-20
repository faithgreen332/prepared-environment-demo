package com.funpay.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Leeko
 * @date 2022/2/26
 **/
@Data
public class StepPolicy implements Serializable {
    private int id;
    private BigDecimal rate;
    private int amount;
    private int lowLimit;
}
