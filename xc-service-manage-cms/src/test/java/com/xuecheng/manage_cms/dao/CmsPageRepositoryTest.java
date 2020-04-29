package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

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

    /**
     * 测试条件查询
     */
    @Test
    public void testFindByCon() {
//        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
//        int page = 1;
//        int size = 10;
//        CmsPage cmsPage = new CmsPage();
//        cmsPage.setSiteId(null);
//        cmsPage.setPageAliase(null);
//        Pageable pageable = PageRequest.of(page, size);
//        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);
//        Page<CmsPage> cmsPagePage = cmsPageRepository.findAll(example, pageable);
//        System.out.println(cmsPagePage.getContent());
//        System.out.println(cmsPagePage.getTotalElements());
        System.out.println("".hashCode());
        String str = null;
        System.out.println(str);
    }

    /**
     * 查询所有的site
     */
    @Test
    public void testFindAllSiteId() {
        Set<String> siteset = new HashSet<>();
        Set<String> tempset = new HashSet<>();
        List<CmsPage> allCmsPage = cmsPageRepository.findAll();
        for (CmsPage cmsPage : allCmsPage) {
            siteset.add(cmsPage.getSiteId());
            tempset.add(cmsPage.getPageTemplate());
        }
        System.out.println(siteset);
        System.out.println(tempset);
    }

    /**
     * 修改页面
     */
    @Test
    public void testEditCmsPage() {
        CmsPage saveCmsPage = null;
        Optional<CmsPage> cmsPage = cmsPageRepository.findById("5ea97e96c821bf34c016887f");
        if (cmsPage.isPresent()) {
            CmsPage cmsPage1 = cmsPage.get();
            cmsPage1.setPageName("wjw2222");
            cmsPage1.setPageCreateTime(new Date());
            cmsPage1.setPageAliase("wjw3333");
            cmsPageRepository.save(cmsPage1);
        }
    }

    @Test
    public void testDeleteCmsPage() {
        CmsPage saveCmsPage = null;
        Optional<CmsPage> cmsPage = cmsPageRepository.findById("5ea98104c821bf34c0168880");
        if (cmsPage.isPresent()) {
            CmsPage cmsPage1 = cmsPage.get();
            cmsPageRepository.delete(cmsPage1);
        }
    }

    @Test
    public void testFindCmsPageById(){
        Optional<CmsPage> cmsPage = cmsPageRepository.findById("5ea97e96c821bf34c016887f");
        if(cmsPage.isPresent()){
            CmsPage cmsPage1 = cmsPage.get();
            System.out.println(cmsPage1);
        }
    }
}
