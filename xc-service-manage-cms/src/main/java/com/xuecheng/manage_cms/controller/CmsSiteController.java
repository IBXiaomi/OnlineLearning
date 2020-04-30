package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmsSiteControllerApi;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.manage_cms.service.CmsSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cms/page/site")
public class CmsSiteController implements CmsSiteControllerApi {
    @Autowired
    CmsSiteService cmsSiteService;

    @Override
    @GetMapping("/getSiteById/{id}")
    public QueryResponseResult findById(@PathVariable("id") String id) {
        return cmsSiteService.findById(id);
    }

    @Override
    @GetMapping("/getAllSite")
    public QueryResponseResult findAll() {
        return cmsSiteService.findAll();
    }
}
