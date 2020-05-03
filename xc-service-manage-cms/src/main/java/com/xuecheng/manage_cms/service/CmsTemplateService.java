package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.exception.CustomException;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsTemplateRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
}
