package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * cms页面查询controller
 *
 * @author 吧嘻小米
 * @date 2020/04/19
 */
@RestController
@Slf4j
public class CmsPageController implements CmsPageControllerApi {

    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findPage(@PathVariable("page") int page, @PathVariable("size") int size, QueryPageRequest queryPageRequest) {
        log.info("start to findPage");
        CmsPage cmsPage = new CmsPage();
        cmsPage.setPageName("测试页面");
        List<CmsPage> list = new ArrayList<CmsPage>(1);
        list.add(cmsPage);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(list);
        queryResult.setTotal(1);
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }
}
