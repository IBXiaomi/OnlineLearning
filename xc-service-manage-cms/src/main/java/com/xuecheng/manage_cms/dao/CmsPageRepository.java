package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

/**
 * cms页面管理，查询数据库接口
 * 需要在MongoRepository指定collection中的主键类型
 *
 * @author 吧嘻小米
 * @date 2020/04/19
 */
@Component
public interface CmsPageRepository extends MongoRepository<CmsPage, String> {

//    /**
//     * 分页查询
//     */
//
//    Page<CmsPage> findByPageName(Pageable pageable);
//
//    /**
//     * 条件查询
//     */
//    List<CmsPage> findAll(Example example);

    CmsPage findByPageNameAndSiteIdAndPageWebPath(String pageName, String siteId, String pageWebPath);
}
