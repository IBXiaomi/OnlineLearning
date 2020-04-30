package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 页面模板
 *
 * @author 吧嘻小米
 * @date 2020/04/28
 */
public interface CmsTemplateRepository extends MongoRepository<CmsTemplate, String> {
}
