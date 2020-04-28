package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.api.cms.CmsSiteControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.manage_cms.service.CmsPageService;
import com.xuecheng.manage_cms.service.CmsSiteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * cms页面查询controller
 *
 * @author 吧嘻小米
 * @date 2020/04/19
 */
@RestController
@Slf4j
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi, CmsSiteControllerApi {

    @Autowired
    CmsPageService cmsPageService;

    @Autowired
    CmsSiteService cmsSiteService;

    /**
     * 分页查询，自定义条件查询
     *
     * @param page             当前页
     * @param size             每页显示的数据
     * @param queryPageRequest 查询条件
     * @return 查询结果
     */
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findPage(@PathVariable("page") int page, @PathVariable("size") int size, QueryPageRequest queryPageRequest) {
        log.info("start to findPage");
        // 此处的cmsPageService需要保证与dao中的对象同步加载,否则可能会有空指针异常，不能采用new对象的方式
        return cmsPageService.findPage(page, size, queryPageRequest);
    }

    /**
     * 根据id查询页面信息
     *
     * @param id 页面id
     * @return 返回查询结果
     */
    @Override
    @GetMapping("/get/{id}")
    public QueryResponseResult findPageById(@PathVariable("id") String id) {
        return null;
    }

    /**
     * 新增页面
     *
     * @param cmsPage 新增页面
     * @return 返回插入结果
     * @RequestBody 注解保证前端传递的json数据转换为对象
     */
    @Override
    @PostMapping("/add")
    public CmsPageResult addCmsPage(@RequestBody CmsPage cmsPage) {

        return cmsPageService.addCmsPage(cmsPage);
    }


    @Override
    @GetMapping("/getSiteById/{id}")
    public QueryResponseResult findById(@PathVariable("id") String id) {
        return cmsSiteService.findById(id);
    }

    @Override
    @GetMapping("/getAllSite")
    public QueryResponseResult findAll() {
        return cmsSiteService.findAll();
    }
}
