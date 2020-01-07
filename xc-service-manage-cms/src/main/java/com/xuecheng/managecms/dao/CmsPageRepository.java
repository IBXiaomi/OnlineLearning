package com.xuecheng.managecms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 分页查询的dao接口
 *
 * @author wjw
 * @since JDK1.9
 */
public interface CmsPageRepository extends MongoRepository<CmsPage, String> {

    /**
     * 分页查询方法
     * @return
     */
    //CmsPageResult findList();
}
