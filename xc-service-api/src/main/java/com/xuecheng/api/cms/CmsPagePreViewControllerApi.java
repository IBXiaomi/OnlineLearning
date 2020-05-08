package com.xuecheng.api.cms;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 页面预览的api
 *
 * @author 吧嘻小米
 * @date 2020/05/08
 */
@Api(value = "页面预览")
public interface CmsPagePreViewControllerApi {

    @ApiOperation(value = "页面预览")
    @ApiImplicitParam(name = "pageId", value = "页面id", paramType = "path", required = true, dataType = "String")
    void preView(String pageId);
}
