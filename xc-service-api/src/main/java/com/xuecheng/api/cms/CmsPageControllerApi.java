package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * CMS页面管理的接口
 *
 * @author wjw
 * @since JDK1.8
 */
@Api(value = "分页查询")
public interface CmsPageControllerApi {
    /**
     * 分页查询数据
     *
     * @param page             当前页
     * @param size             每页显示的数据
     * @param queryPageRequest 查询条件
     * @return 返回查询结果
     */
    @ApiOperation(value = "分页查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "path", required = true, dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页显示的数量", paramType = "path", required = true, dataType = "int"),
            @ApiImplicitParam(name = "queryPageRequest", value = "查询条件", paramType = "path", required = false, dataType = "object")
    })
    QueryResponseResult findPage(int page, int size, QueryPageRequest queryPageRequest);

    /**
     * 根据页面id查询页面
     *
     * @param id 页面id
     * @return 返回查询结果
     */
    @ApiOperation(value = "根据页面id查询页面")
    CmsPage findPageById(String id);

    /**
     * 新增页面
     *
     * @param cmsPage 新增页面
     * @return 返回插入页面结果
     */
    @ApiOperation(value = "新增页面")
    CmsPageResult addCmsPage(CmsPage cmsPage);

    /**
     * 根据页面id删除页面
     *
     * @param id 页面id
     * @return 返回执行结果
     */
    ResponseResult deleteCmsPage(String id);

    /**
     * 修改页面
     *
     * @param id      页面id
     * @param cmsPage 旧页面信息
     * @return 返回修改结果
     */
    CmsPageResult update(String id, CmsPage cmsPage);
}
