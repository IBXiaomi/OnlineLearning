package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmsTemplateControllerApi;
import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.domain.cms.request.QueryTemplateRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.domain.cms.response.result.CmsTemplateResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.manage_cms.service.CmsTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findCmsTemplate(@PathVariable("page") int page, @PathVariable("size") int size, QueryTemplateRequest queryTemplateRequest) {
        return cmsTemplateService.findCmsTemplate(page, size, queryTemplateRequest);
    }

    @Override
    @PostMapping("/add")
    public CmsPageResult addCmsTemplatePage(CmsTemplate cmsTemplate) {
        return null;
    }

    @Override
    @GetMapping("/getHtmlPreView")
    public void getPreViewHtml() {
        cmsTemplateService.getHtmlPreView();
    }

    @Override
    @PostMapping("/upload")
    public CmsTemplateResult uploadTemplateFile(@RequestParam("file") MultipartFile multipartFile) {
        return cmsTemplateService.uploadTemplateFile(multipartFile);
    }


}
