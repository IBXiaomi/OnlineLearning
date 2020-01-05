package com.xuecheng.managecms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.utils.traverse.TraverseCollections;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

/**
 * 分页查询dao接口的测试类 @Runwith注解的含义是让测试基于spring的环境
 *
 * @author wjw
 * @since JDK 1.9
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class CmsPageRepositoryTest {

    @Autowired
    CmsPageRepository cmsPageRepository;


    /**
     * 查询所有
     */
    @Test
    public void testFindAll() {
        List<CmsPage> pageList = cmsPageRepository.findAll();
        TraverseCollections.traverseCollection(pageList);
    }

    /**
     * 分页查询
     */
    @Test
    public void testFindPage() {
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        Page<CmsPage> cmsPagePage = cmsPageRepository.findAll(pageable);
        long totalElements = cmsPagePage.getTotalElements();
        int totalPages = cmsPagePage.getTotalPages();
        System.out.println(totalElements);
        System.out.println(totalPages);
    }

    /**
     * 修改功能
     * findOne方法有问题，和spring boot2.X版本不兼容
     */
    @Test
    public void testUpdate() {
        Optional<CmsPage> cmsPage = cmsPageRepository.findById("5a754adf6abb500ad05688d9");
        if (cmsPage.isPresent()) {
            log.info("there is the cmsPage");
            CmsPage cmsPageResult = cmsPage.get();
            cmsPageResult.setPageAliase("首页");
            cmsPageRepository.save(cmsPageResult);
        } else {
            log.info("there is not have cmsPage");
        }
    }
}
