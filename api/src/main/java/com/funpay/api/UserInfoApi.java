package com.funpay.api;

import com.funpay.model.response.FunpayResult;
import com.funpay.model.user.UserInfo;
import io.swagger.annotations.*;

/**
 * @author Leeko
 * @date 2022/1/26
 **/
@Api(value = "ooxx", tags = "用户的增上改查")
public interface UserInfoApi {

    @ApiOperation(
            value = "新增一个用户，表 t_userinfo", notes = "userId唯一"
    )
    @ApiResponses({@ApiResponse(code = 10000, message = "success", response = Object.class, reference = "不知道干嘛用", responseHeaders = @ResponseHeader(name = "cors-token", description = "密钥", response = String.class))
            , @ApiResponse(code = 40001, message = "加密错误", response = Object.class, reference = "不知道干嘛用", responseHeaders = @ResponseHeader(name = "cors-token", description = "密钥", response = String.class))})
    FunpayResult addUserInfo(UserInfo userInfo);
}
