package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * cms_config相关接口
 *
 * @author 吧嘻小米
 * @date 2020/05/03
 */
@Api(value = "cms_config相关接口")
public interface CmsConfigCollectorApi {

    @ApiOperation(value = "根据id查询cms_config")
    @ApiImplicitParam(name = "id", value = "cms_config_id", paramType = "path", required = true, dataType = "String")
    CmsConfig findCmsConfigById(String id);
}
