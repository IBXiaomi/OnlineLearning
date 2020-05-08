package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.domain.cms.request.QueryTemplateRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.domain.cms.response.result.CmsTemplateResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

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
     *
     * @return 查询结果
     */
    @ApiOperation(value = "查询所有的模板信息")
    QueryResponseResult findAll();

    /**
     * 分页查询，条件查询
     *
     * @return 查询结果
     */
    @ApiOperation(value = "条件查询模板信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "path", required = true, dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页显示的数量", paramType = "path", required = true, dataType = "int"),
            @ApiImplicitParam(name = "queryTemplateRequest", value = "查询条件", paramType = "path", required = false, dataType = "object")
    })
    QueryResponseResult findCmsTemplate(int page, int size, QueryTemplateRequest queryTemplateRequest);

    /**
     * 新增页面模板
     *
     * @param cmsTemplate 新增页面模板
     * @return 返回插入页面结果
     */
    @ApiOperation(value = "新增页面")
    @ApiImplicitParam(name = "cmsTemplate", value = "新增页面模板信息", paramType = "path", required = true, dataType = "object")
    CmsPageResult addCmsTemplatePage(CmsTemplate cmsTemplate);

    /**
     * 页面模板预览接口
     */
    @ApiOperation(value = "页面模板预览接口")
    void getPreViewHtml();

    /**
     * 上传模板文件接口
     *
     * @return 上传结果
     */
    CmsTemplateResult uploadTemplateFile(MultipartFile multipartFile);
}
