package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.CustomExceptionFactory;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * cms页面管理
 *
 * @author 吧嘻小米
 * @date 2020/04/19
 */
@Slf4j
@Service
public class CmsPageService {

    @Autowired
    CmsPageRepository cmsPageRepository;

    /**
     * 分页查询,条件查询
     *
     * @param page             当前页，mongodb查询是从第0页开始
     * @param size             每页展示的数据
     * @param queryPageRequest 查询条件
     * @return 查询结果
     */
    public QueryResponseResult findPage(int page, int size, QueryPageRequest queryPageRequest) {
        log.info("start to find cmsPage");
        // 初始化查询条件
        CmsPage cmsPage = new CmsPage();
        // withMatcher表示模糊匹配的方式，当前表示以pageAliase为模糊匹配项，匹配方式为包含
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        if (null == queryPageRequest) {
            throw CustomExceptionFactory.getCustomException(CommonCode.CMS_PAGE_PARAMS);
        }
        // 不能使用(null!=queryPageRequest.getSiteId())来判断是否为空，!=判断的是内存地址，不是实际的值
        if (StringUtils.isNotEmpty(queryPageRequest.getSiteId())) {
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        if (StringUtils.isNotEmpty(queryPageRequest.getTemplateId())) {
            cmsPage.setPageTemplate(queryPageRequest.getTemplateId());
        }
        if (StringUtils.isNotEmpty(queryPageRequest.getPageAliase())) {
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }
        if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            size = 10;
        }
        page = page - 1;
        Pageable pageable = PageRequest.of(page, size);
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);
        Page<CmsPage> cmsPagePage = cmsPageRepository.findAll(example, pageable);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(cmsPagePage.getContent());
        queryResult.setTotal(cmsPagePage.getTotalElements());
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    /**
     * 根据页面id查询页面
     *
     * @param id 页面id
     * @return 查询结果
     */
    public CmsPage findPageById(String id) {
        Optional<CmsPage> cmsPage = cmsPageRepository.findById(id);
        if (cmsPage.isPresent()) {
            return cmsPage.get();
        }
        return null;
    }

    /**
     * 根据页面id修改页面
     *
     * @param id      页面id
     * @param cmsPage 页面信息
     * @return 返回修改结果
     */
    public CmsPageResult editCmsPageById(String id, CmsPage cmsPage) {
        CmsPage saveCmsPage = null;
        log.info("start to edit cmsPage");
        if (StringUtils.isNotEmpty(id)) {
            CmsPage pageById = findPageById(id);
            if(null == cmsPage){
                CustomExceptionFactory.getCustomException(CmsCode.CMS_HTML_TEMPLATES_NULL);
            }
            pageById.setSiteId(cmsPage.getSiteId());
            pageById.setPageAliase(cmsPage.getPageAliase());
            pageById.setPageTemplate(cmsPage.getPageTemplate());
            pageById.setPageName(cmsPage.getPageName());
            pageById.setPageCreateTime(cmsPage.getPageCreateTime());
            pageById.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
            pageById.setPageWebPath(cmsPage.getPageWebPath());
            pageById.setPageType(cmsPage.getPageType());
            cmsPageRepository.save(pageById);
            return new CmsPageResult(CommonCode.SUCCESS, cmsPage);
        } else {
            log.error("cmsPage id is null ,please check cmsPage id");
        }
        return new CmsPageResult(CommonCode.FAIL, null);
    }

    /**
     * 根据id删除页面
     *
     * @param id 页面id
     * @return 返回删除结果
     */
    public ResponseResult deleteCmsPage(String id) {
        log.info("start to delete cmspage");
        if (StringUtils.isNotEmpty(id)) {
            CmsPage pageById = findPageById(id);
            if (null != pageById) {
                cmsPageRepository.delete(pageById);
                return new ResponseResult(CommonCode.SUCCESS);
            }
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    /**
     * 新增页面
     *
     * @param cmsPage 新增页面
     * @return 返回插入结果
     */
    public CmsPageResult addCmsPage(CmsPage cmsPage) {
        log.info("start to add cmsPage");
        if (null == cmsPage) {
            log.error("cmsPage params is null");
            CustomExceptionFactory.getCustomException(CmsCode.CMS_ADD_PAGE_EXISTS_NAME);
        }
        // 首先要根据前端传进来的数据查询数据库中是否存在数据
        CmsPage oldCmsPage = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());
        if (null != oldCmsPage) {
            log.error("this cmsPage is exist , please check params");
            CustomExceptionFactory.getCustomException(CmsCode.CMS_ADD_PAGE_EXISTS_NAME);
        }
        // 保证页面id是数据库自己添加,自己增长
        cmsPage.setPageId(null);
        CmsPage newCmsPage = null;
        try {
            newCmsPage = cmsPageRepository.save(cmsPage);
        } catch (Exception e) {
            log.error("fail to save cmsPage {}", e.toString());
        }
        return new CmsPageResult(CommonCode.SUCCESS, newCmsPage);
    }
}

