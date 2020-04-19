package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void testFindPage() {
        int page = 1;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        Page<CmsPage> cmsPagePage = cmsPageRepository.findAll(pageable);
        long totalElements = cmsPagePage.getTotalElements();
        System.out.println(totalElements);
    }
}
