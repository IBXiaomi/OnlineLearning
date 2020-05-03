package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmsConfigCollectorApi;
import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.manage_cms.service.CmsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * cms_config的controller
 *
 * @author 吧嘻小米
 * @date 2020/05/03
 */
@RestController
@RequestMapping("/cms/config")
public class CmsConfigController implements CmsConfigCollectorApi {

    @Autowired
    CmsConfigService cmsConfigService;

    @Override
    @GetMapping("/getModel/{id}")
    public CmsConfig findCmsConfigById(@PathVariable("id") String id) {
        return cmsConfigService.findCmsConfigById(id);
    }
}
