package com.funpay.exception.pojo.response;

import lombok.Data;

import java.util.List;

/**
 * 查询结果数据集对象包装
 *
 * @author Leeko
 * @date 2022/3/9
 **/
@Data
public class QueryData<T> {

    /**
     * 数据列表
     */
    private List<T> records;

    /**
     * 总记录数
     */
    private long totalCount;

    /**
     * 当前页，从 1 开始
     */
    private long pageNo;

    /**
     * 分页大小
     */
    private long pageSize;

    public QueryData() {
    }

    public QueryData(List<T> records, long totalCount, int pageNo, int pageSize) {
        this.records = records;
        this.totalCount = totalCount;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

}
