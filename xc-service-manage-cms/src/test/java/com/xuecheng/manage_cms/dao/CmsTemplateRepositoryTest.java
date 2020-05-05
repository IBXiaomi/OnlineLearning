package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 模板页面查询测试类
 *
 * @author 吧嘻小米
 * @date 2020/05/05
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsTemplateRepositoryTest {

    @Autowired
    CmsTemplateRepository cmsTemplateRepository;

    @Test
    public void testFindTemplate() {
        CmsTemplate cmsTemplate = new CmsTemplate();
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("templateName", ExampleMatcher.GenericPropertyMatchers.contains());


        cmsTemplate.setTemplateFileId("5a962c16b00ffc514038fafb");

        cmsTemplate.setTemplateName("分类导航");
        cmsTemplate.setTemplateId(null);
        cmsTemplate.setSiteId(null);
        cmsTemplate.setTemplateParameter(null);

        Example<CmsTemplate> example = Example.of(cmsTemplate, exampleMatcher);
        // 当page从1开始时，查询到的数据集合是个空值
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        Page<CmsTemplate> cmsTemplatePage = cmsTemplateRepository.findAll(example, pageable);
        List<CmsTemplate> content = cmsTemplatePage.getContent();
        long totalElements = cmsTemplatePage.getTotalElements();
        System.out.println(content);
        System.out.println(totalElements);
    }
}
