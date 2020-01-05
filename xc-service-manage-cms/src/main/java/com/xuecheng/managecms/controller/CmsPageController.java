package com.xuecheng.managecms.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.model.response.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * CMS页面管理的controller
 *
 * @author wjw
 * @since JDK1.9
 */
@RestController
@Slf4j
public class CmsPageController implements CmsPageControllerApi {


    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size,
                                        QueryPageRequest queryPageRequest) {
        log.info("start to findList");
        List<CmsPage> list = new ArrayList<>(3);
        QueryResult queryResult = new QueryResult();
        queryResult.setTotal(1);
        CmsPage cmsPage = new CmsPage();
        cmsPage.setPageName("测试页面");
        queryResult.setList(list);
        QueryResponseResult responseResult = new QueryResponseResult(CmsCode.CMS_ADDPAGE_EXISTSNAME,queryResult);
        return responseResult;
    }
}
