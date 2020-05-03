package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.framework.domain.cms.CmsConfigModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * CmsConfigRepository的相关测试
 *
 * @author 吧嘻小米
 * @date 2020/05/03
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsConfigRepositoryTest {

    @Autowired
    CmsConfigRepository cmsConfigRepository;

    @Test
    public void getCmsConfigById() {
        Optional<CmsConfig> optional = cmsConfigRepository.findById("5a791725dd573c3574ee333f");
        if (optional.isPresent()) {
            CmsConfig cmsConfig = optional.get();
            System.out.println(cmsConfig);
        }
    }

    @Test
    public void setCmsConfig() {
        CmsConfig cmsConfig = new CmsConfig();
        List<CmsConfigModel> list = new ArrayList<>();
        CmsConfigModel cmsConfigModel1 = new CmsConfigModel();
        CmsConfigModel cmsConfigModel2 = new CmsConfigModel();
        cmsConfigModel1.setKey("banner1");
        cmsConfigModel1.setName("轮播图1地址");
        cmsConfigModel1.setValue("http://192.168.200.129/img/widget-bannerB.jpg");
        cmsConfigModel2.setKey("banner2");
        cmsConfigModel2.setName("轮播图2地址");
        cmsConfigModel2.setValue("http://192.168.200.129/img/widget-bannerA.jpg");
        cmsConfig.setName("轮播图test");
        list.add(cmsConfigModel1);
        list.add(cmsConfigModel2);
        cmsConfig.setModel(list);
        cmsConfig.setId(null);
        cmsConfigRepository.save(cmsConfig);
    }
}
