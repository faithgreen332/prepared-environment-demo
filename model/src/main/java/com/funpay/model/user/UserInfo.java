package com.funpay.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Leeko
 * @date 2022/1/26
 **/
@Data
@ApiModel
public class UserInfo {

    @JsonIgnore
    private int id;
    @JsonIgnore
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private int userType;
    @ApiModelProperty(name = "用户所在的部门号", required = false)
    private int departmentId;
    @ApiModelProperty(name = "手机号，11位以1开口的数字串", required = true)
    private String phoneNumber;
    private String officeNumber;

}
