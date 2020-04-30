package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmsTemplateControllerApi;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.manage_cms.service.CmsTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cms/page/template")
@Slf4j
public class CmsTemplateController implements CmsTemplateControllerApi {


    @Autowired
    CmsTemplateService cmsTemplateService;

    @Override
    @GetMapping("/getById/{id}")
    public QueryResponseResult findById(@PathVariable("id") String id) {
        log.info("start to find template by id");
        return cmsTemplateService.findById(id);
    }

    @Override
    @GetMapping("/getAllTemplate")
    public QueryResponseResult findAll() {
        log.info("start to find all template ");
        return cmsTemplateService.findAll();
    }
}
