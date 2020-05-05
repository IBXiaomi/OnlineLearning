package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.domain.cms.request.QueryTemplateRequest;
import com.xuecheng.framework.exception.CustomException;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsTemplateRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CmsTemplateService {
    @Autowired
    private CmsTemplateRepository cmsTemplateRepository;

    /**
     * 查询所有的cms_template
     *
     * @return 返回查询结果
     */
    public QueryResponseResult findAll() {
        QueryResult queryResult = new QueryResult();
        try {
            List<CmsTemplate> cmsTemplates = cmsTemplateRepository.findAll();
            queryResult.setList(cmsTemplates);
            queryResult.setTotal(cmsTemplates.size());
        } catch (Exception e) {
            log.error("find all template is failed {}", e.getMessage());
        }
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    /**
     * 根据id查询cms_template表
     *
     * @param id id
     * @return 返回查询结果
     */
    public QueryResponseResult findById(String id) {
        CmsTemplate cmsTemplate = null;
        QueryResult queryResult = null;
        if (StringUtils.isEmpty(id)) {
            log.error("template id is null");
            throw new CustomException(CommonCode.CMS_PAGE_PARAMS);
        }
        try {
            Optional<CmsTemplate> cmsTemplateOptional = cmsTemplateRepository.findById(id);
            if (cmsTemplateOptional.isPresent()) {
                cmsTemplate = cmsTemplateOptional.get();
                List<CmsTemplate> cmsTemplateList = new ArrayList<>();
                cmsTemplateList.add(cmsTemplate);
                queryResult.setList(cmsTemplateList);
                return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
            } else {
                log.error("template id is invalid , please check the template id");
            }
        } catch (Exception e) {
            log.error("find template by id is failed {}", e.toString());
        }
        return new QueryResponseResult(CommonCode.FAIL, null);
    }

    /**
     * 页面模板的查询
     *
     * @param page                 当前页
     * @param size                 每页显示的数量
     * @param queryTemplateRequest 条件查询的类
     * @return 返回查询结果
     */
    public QueryResponseResult findCmsTemplate(int page, int size, QueryTemplateRequest queryTemplateRequest) {
        log.info("start to findCmsTemplate");
        CmsTemplate cmsTemplate = new CmsTemplate();
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("templateName", ExampleMatcher.GenericPropertyMatchers.contains());
        if (null == queryTemplateRequest) {
            queryTemplateRequest = new QueryTemplateRequest();
        }
        if (StringUtils.isNotEmpty(queryTemplateRequest.getTemplateFileId())) {
            cmsTemplate.setTemplateId(queryTemplateRequest.getTemplateId());
        }
        if (StringUtils.isNotEmpty(queryTemplateRequest.getTemplateName())) {
            cmsTemplate.setTemplateName(queryTemplateRequest.getTemplateName());
        }
        // 默认从第一页开始查询
        if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            size = 10;
        }
        page = page - 1;
        Pageable pageable = PageRequest.of(page, size);
        Example<CmsTemplate> example = Example.of(cmsTemplate, exampleMatcher);
        Page<CmsTemplate> cmsTemplatePage = cmsTemplateRepository.findAll(example, pageable);
        List<CmsTemplate> cmsTemplateList = cmsTemplatePage.getContent();
        long totalElements = cmsTemplatePage.getTotalElements();
        QueryResult queryResult = new QueryResult();
        queryResult.setList(cmsTemplateList);
        queryResult.setTotal(totalElements);
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);

    }
}
