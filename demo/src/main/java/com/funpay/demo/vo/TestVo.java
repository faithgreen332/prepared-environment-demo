package com.funpay.demo.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Leeko
 * @date 2022/3/9
 **/
@Data
public class TestVo {

    @NotNull
    private Integer id;

    @Pattern(regexp = "faith")
    private String name;

    @NotEmpty
    private String[] sts;

}
