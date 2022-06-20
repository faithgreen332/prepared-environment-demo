package com.funpay.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Leeko
 * @date 2022/1/26
 **/
@ApiModel
@Data
public class FunpayResult {

    @ApiModelProperty(name = "返回码", dataType = "int", example = "1000")
    public int code;
    @ApiModelProperty(name = "返回信息", dataType = "String", example = "success")
    private String msg;
    @ApiModelProperty(name = "返回的数据", dataType = "object", example = "1")
    private Object data;

    public FunpayResult() {
    }

    public FunpayResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static FunpayResult result(int code, String msg, Object data) {
        return new FunpayResult(code, msg, data);
    }

    public static FunpayResult resultNullData(int code, String msg) {
        return new FunpayResult(code, msg, null);
    }
}
