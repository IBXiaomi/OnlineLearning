package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * cms_config的dao层，操作数据库
 *
 * @author 吧嘻小米
 * @date 2020/05/03
 */
public interface CmsConfigRepository extends MongoRepository<CmsConfig, String> {
}
