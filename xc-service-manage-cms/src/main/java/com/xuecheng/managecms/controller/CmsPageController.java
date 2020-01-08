package com.xuecheng.managecms.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.*;
import com.xuecheng.managecms.service.PageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * CMS页面管理的controller
 *
 * @author wjw
 * @since JDK1.9
 */
@RestController
@Slf4j
@RequestMapping("/cms/page")
@Api(value = "cms页面管理接口", description = "cms页面管理接口,提供修改")
public class CmsPageController implements CmsPageControllerApi {

    @Autowired
    PageService pageService;

    @Override
    @GetMapping("/list/{page}/{size}")
    @ApiOperation("cms分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页显示的数据", required = true, paramType = "path", dataType = "int")})
    public QueryResponseResult findPage(@PathVariable("page") int page, @PathVariable("size") int size,
                                        QueryPageRequest queryPageRequest) {
        log.info("start to findPage");
        return pageService.findPage(page, size, null);
    }
}
