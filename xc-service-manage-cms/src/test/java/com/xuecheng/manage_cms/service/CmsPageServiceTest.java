package com.xuecheng.manage_cms.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * cms_page服务相关测试
 *
 * @author 吧嘻小米
 * @date 2020/05/06
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageServiceTest {
    @Autowired
    CmsPageService cmsPageService;

    /**
     * 测试页面静态化
     */
    @Test
    public void testGetHtml() {
        String html = cmsPageService.createHtmlByTemplate("5eb2b39d58ac2c194777bea0");

    }
}
