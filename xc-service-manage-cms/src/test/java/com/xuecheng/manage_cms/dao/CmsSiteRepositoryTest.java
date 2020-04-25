package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsSite;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@SpringBootTest
// 需要执行running注解，否则会有空指针异常
@RunWith(SpringRunner.class)
public class CmsSiteRepositoryTest {

    @Autowired
    private CmsSiteRepository cmsSiteRepository;

    @Test
    public void findAll() {
        List<CmsSite> all = cmsSiteRepository.findAll();
        System.out.println(all);
    }

    @Test
    public void findById() {
        Optional<CmsSite> cmsSite = cmsSiteRepository.findById("5a751fab6abb5044e0d19ea1");
        if (cmsSite.isPresent()) {
            System.out.println(cmsSite);
        }

    }
}
