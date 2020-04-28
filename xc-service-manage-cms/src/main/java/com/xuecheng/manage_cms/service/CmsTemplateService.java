package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsTemplateRepository;
import lombok.extern.slf4j.Slf4j;
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
     * 查询所有的cms_site
     *
     * @return 返回查询结果
     */
    public QueryResponseResult findAll() {
        log.info("strt to find all Template");
        List<CmsTemplate> cmsTemplates = cmsTemplateRepository.findAll();
        QueryResult queryResult = new QueryResult();
        queryResult.setList(cmsTemplates);
        queryResult.setTotal(cmsTemplates.size());
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    /**
     * 根据id查询cms_site表
     *
     * @param id id
     * @return 返回查询结果
     */
    public QueryResponseResult findById(String id) {
        Optional<CmsTemplate> cmsTemplateOptional = cmsTemplateRepository.findById(id);
        CmsTemplate cmsTemplate = new CmsTemplate();
        if (cmsTemplateOptional.isPresent()) {
            cmsTemplate = cmsTemplateOptional.get();
        }
        QueryResult queryResult = new QueryResult();
        List<CmsTemplate> cmsTemplateList = new ArrayList<>();
        cmsTemplateList.add(cmsTemplate);
        queryResult.setList(cmsTemplateList);
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }
}
