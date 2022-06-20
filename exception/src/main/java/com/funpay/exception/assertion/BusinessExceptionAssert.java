package com.funpay.exception.assertion;

import com.funpay.exception.BaseException;
import com.funpay.exception.BusinessException;
import com.funpay.exception.constant.IResponseEnum;

import java.text.MessageFormat;

/**
 * 业务异常断言
 *
 * @author Leeko
 * @date 2022/3/8
 **/
public interface BusinessExceptionAssert extends IResponseEnum, Assert {

    @Override
    default BaseException newException(Object... args) {
        String msg = this.getMessage();
        if (args != null && args.length > 0) {
            msg = MessageFormat.format(this.getMessage(), args);
        }
        return new BusinessException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = this.getMessage();
        if (args != null && args.length > 0) {
            msg = MessageFormat.format(this.getMessage(), args);
        }
        return new BusinessException(this, args, msg, t);
    }
}
