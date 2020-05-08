package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmsPagePreViewControllerApi;
import com.xuecheng.framework.exception.CustomExceptionFactory;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.web.BaseController;
import com.xuecheng.manage_cms.service.CmsPageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

/**
 * 页面预览
 *
 * @author 吧嘻小米
 * @date 2020/05/08
 */
@Controller
@RequestMapping("/cms/preview")
@Slf4j
public class CmsPagePreViewController extends BaseController implements CmsPagePreViewControllerApi {

    @Autowired
    CmsPageService cmsPageService;

    /**
     * 实现页面预览
     *
     * @param pageId 页面id
     */
    @GetMapping("/{pageId}")
    public void preView(@PathVariable("pageId") String pageId) {
        try {
            if (StringUtils.isEmpty(pageId)) {
                throw CustomExceptionFactory.getCustomException(CommonCode.CMS_ID_PARAMS);
            }
            String html = cmsPageService.createHtmlByTemplate(pageId);
            byte[] bytes = html.getBytes();
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes);
        } catch (IOException e) {
            log.error("preView html failed {}", e.getMessage());
        }

    }
}
