package com.funpay.exception.pojo.response;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询数据集返回结果
 *
 * @author Leeko
 * @date 2022/3/9
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class QueryDataResponse<T> extends CommonResponse<PageInfo<T>> {
    public QueryDataResponse() {
    }

    public QueryDataResponse(PageInfo<T> data) {
        super(data);
    }
}
