package com.xuecheng.api.cms;

import com.xuecheng.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 页面模板接口
 *
 * @author 吧嘻小米
 * @date 2020/04/28
 */
@Api(value = "模板查询")
public interface CmsTemplateControllerApi {

    /**
     * 根据模板id查询站点
     *
     * @param id 模板id
     * @return 返回查询的对应的模板信息
     */

    @ApiOperation(value = "根据模板id查询站点")
    @ApiImplicitParam(name = "id", value = "模板id", paramType = "path", required = true, dataType = "String")
    QueryResponseResult findById(String id);

    /**
     * 查询所有的模板
     */

    @ApiOperation(value = "查询所有的模板信息")
    QueryResponseResult findAll();

}
