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
     *
     * @return 返回规划好的实体类
     */
    QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);
}
