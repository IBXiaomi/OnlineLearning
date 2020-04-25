package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * dao测试
 *
 * @author 吧嘻小米
 * @date 2020/04/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CmsPageRepositoryTest {
    @Autowired
    CmsPageRepository cmsPageRepository;

    /**
     * 测试分页查询
     */
    @Test
    public void testFindPage() {
        int page = 1;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        Page<CmsPage> cmsPagePage = cmsPageRepository.findAll(pageable);
        long totalElements = cmsPagePage.getTotalElements();
        System.out.println(totalElements);
    }

    /**
     * 测试条件查询
     */
    @Test
    public void testFindAll() {
        CmsPage cmsPage = new CmsPage();
        // 首页
        cmsPage.setSiteId("5a751fab6abb5044e0d19ea1");
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);
        List<CmsPage> all = cmsPageRepository.findAll(example);
        System.out.println(all);
    }
}
