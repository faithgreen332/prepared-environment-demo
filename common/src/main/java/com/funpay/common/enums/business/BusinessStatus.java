package com.funpay.common.enums.business;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务状态 0:正常使用 1:不予结算 2：不予使用 3：永久封禁 4 申请中 5 拒绝
 *
 * @author dave
 */
@Getter
@AllArgsConstructor
public enum BusinessStatus {
    RUN(0), NOT_TO_BE_SETTLED(1), NOT_TO_BE_USED(2), PERMANENTLY_BAN(3), APPLYING(4), REJECT(5),
    ;

    private int status;

    public static BusinessStatus getBusinessStatus(int status) {
        for (BusinessStatus businessStatus : BusinessStatus.values()) {
            if (businessStatus.getStatus() == status) {
                return businessStatus;
            }
        }
        return BusinessStatus.REJECT;
    }
}
