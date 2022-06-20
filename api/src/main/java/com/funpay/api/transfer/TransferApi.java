package com.funpay.api.transfer;

import com.funpay.model.request.TransferReq;
import com.funpay.model.response.FunpayResult;
import io.swagger.annotations.*;

/**
 * @author Leeko
 * @date 2022/2/14
 **/
@Api(value = "transferApi", tags = "打款相关的接口")
public interface TransferApi {

    /**
     * 打款
     *
     * @param transferReq
     * @return
     */
    @ApiOperation(value = "打款")
    @ApiResponses({@ApiResponse(code = 10000, message = "success", response = Object.class, reference = "不知道干嘛用", responseHeaders = @ResponseHeader(name = "cors-token", description = "密钥", response = String.class))
            , @ApiResponse(code = 40001, message = "加密错误", response = Object.class, reference = "不知道干嘛用", responseHeaders = @ResponseHeader(name = "cors-token", description = "密钥", response = String.class))})
    FunpayResult apiTransferMoney(TransferReq transferReq);
}
