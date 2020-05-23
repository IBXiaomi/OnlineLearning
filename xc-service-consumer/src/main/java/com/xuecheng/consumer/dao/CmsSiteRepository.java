package com.xuecheng.consumer.dao;

import com.xuecheng.framework.domain.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * cms_site的dao
 *
 * @author 吧嘻小米
 * @date 2020/04/25
 */
public interface CmsSiteRepository extends MongoRepository<CmsSite, String> {
}
