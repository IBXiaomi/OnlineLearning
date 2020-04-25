package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.api.cms.CmsSiteControllerApi;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.manage_cms.service.CmsPageService;
import com.xuecheng.manage_cms.service.CmsSiteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * cms页面查询controller
 *
 * @author 吧嘻小米
 * @date 2020/04/19
 */
@RestController
@Slf4j
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi, CmsSiteControllerApi {

    @Autowired
    CmsPageService cmsPageService;

    @Autowired
    CmsSiteService cmsSiteService;

    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findPage(@PathVariable("page") int page, @PathVariable("size") int size, QueryPageRequest queryPageRequest) {
        log.info("start to findPage");
        // 此处的cmsPageService需要保证与dao中的对象同步加载,否则可能会有空指针异常，不能采用new对象的方式
        return cmsPageService.findPage(page, size, queryPageRequest);
    }

    @Override
    @GetMapping("/get/{id}")
    public QueryResponseResult findPageById(@PathVariable("id") String id) {
        return null;
    }


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
