package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.QueryResponseResult;

/**
 * CMS页面管理的接口
 *
 * @author wjw
 * @since JDK1.8
 */
public interface CmsPageControllerApi {
    /**
     * 分页查询数据
     * @param page 当前页
     * @param size 每页显示的数据
     * @param queryPageRequest 查询条件
     * @return 返回查询结果
     */

    QueryResponseResult findPage(int page, int size, QueryPageRequest queryPageRequest);
}
