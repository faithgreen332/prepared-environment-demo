package com.funpay.exception.pojo.response;

import com.github.pagehelper.PageInfo;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 分页查询相应体，同{@link QueryDataResponse}
 *
 * @author Leeko
 * @date 2022/3/9
 **/
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class QR<T> extends QueryDataResponse<T> {
    public QR() {
        super();
    }

    public QR(PageInfo<T> data) {
        super(data);
    }
}
