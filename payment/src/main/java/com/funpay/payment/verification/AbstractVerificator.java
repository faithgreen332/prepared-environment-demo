package com.funpay.payment.verification;

import com.funpay.model.response.FunpayResult;

/**
 * @author Leeko
 * @date 2022/2/23
 **/
public abstract class AbstractVerificator {

    private AbstractVerificator next;

    private boolean nextFlag;

    public AbstractVerificator getNext() {
        return next;
    }

    public void setNext(AbstractVerificator next) {
        this.next = next;
    }

    public boolean isNextFlag() {
        return nextFlag;
    }

    public void setNextFlag(boolean nextFlag) {
        this.nextFlag = nextFlag;
    }

    public FunpayResult verificate() {
        FunpayResult result = verify();
        if (next != null && nextFlag) {
            result = next.verificate();
        }
        return result;
    }

    public abstract FunpayResult verify();
}
