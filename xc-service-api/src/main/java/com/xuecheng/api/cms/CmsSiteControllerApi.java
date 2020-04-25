package com.xuecheng.api.cms;

import com.xuecheng.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 查询站点
 *
 * @author 吧嘻小米
 * @date 2020/04/25
 */
@Api(value = "查询站点api")
public interface CmsSiteControllerApi {

    /**
     * 根据站点id查询站点
     *
     * @param id 站点id
     * @return 返回查询的对应的站点信息
     */

    @ApiOperation(value = "根据站点id查询站点")
    @ApiImplicitParam(name = "id", value = "站点id", paramType = "path", required = true, dataType = "String")
    QueryResponseResult findById(String id);

    /**
     * 查询所有的站点
     */

    @ApiOperation(value = "查询所有的站点信息")
    QueryResponseResult findAll();
}
