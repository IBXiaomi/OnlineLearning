package com.xuecheng.framework.model.response;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * cms页面管理查询条件
 *
 * @param <T> 泛型
 * @author wjw
 * @since JDK1.9
 */
@Data
@ToString
public class QueryResult<T> {
    //数据列表
    private List<T> list;
    //数据总数
    private long total;
}
