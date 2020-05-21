package com.xuecheng.consumer.RabbitMQConsumerTest;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.xuecheng.consumer.dao.CmsPageRepository;
import com.xuecheng.consumer.dao.CmsSiteRepository;
import com.xuecheng.consumer.demo.RabbitMQConsumerType;
import com.xuecheng.framework.baseConstant.RabbitMQConstant;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsSite;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * consumer的测试
 *
 * @author 吧嘻小米
 * @date 2020/05/16
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitMQConsumerTest {

    @Autowired
    CmsPageRepository cmsPageRepository;

    @Autowired
    CmsSiteRepository cmsSiteRepository;

    @Autowired
    GridFSBucket gridFSBucket;

    @Autowired
    GridFsTemplate gridFsTemplate;

    /**
     * 测试mqheader模式
     */
    @Test
    public void testRabbitmq() {
        RabbitMQConsumerType.getRabbitMQConsumer(RabbitMQConstant.HEADER_TYPE).createConsumer(RabbitMQConstant.HEADER_TYPE);
    }


    @Test
    public void testSaveFile() throws IOException {
        CmsPage cmsPage = getCmsPageById("5a754adf6abb500ad05688d9");
        CmsSite cmsSite = findCmsSitePathById(cmsPage.getSiteId());
        System.out.println(cmsSite);
        InputStream htmlFileById = findHtmlFileById(cmsPage.getHtmlFileId());
        String pageName = cmsPage.getPageName();
        File file = new File("F:/TEST/"+pageName);
        // 关于window环境下，文件权限不足的问题，需要指定到具体的文件
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        IOUtils.copy(htmlFileById, fileOutputStream);
    }

    private CmsSite findCmsSitePathById(String siteId) {
        Optional<CmsSite> cmsSiteOptional = cmsSiteRepository.findById(siteId);
        if (cmsSiteOptional.isPresent()) {
            return cmsSiteOptional.get();
        }
        return null;
    }


    private InputStream findHtmlFileById(String htmlFileId) throws IOException {
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(htmlFileId)));
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        // 获取流对象
        GridFsResource gridFsResource = new GridFsResource(gridFSFile, gridFSDownloadStream);
        return gridFsResource.getInputStream();
    }


    private CmsPage getCmsPageById(String pageId) {
        Optional<CmsPage> cmsPageOptional = cmsPageRepository.findById(pageId);
        if (cmsPageOptional.isPresent()) {
            return cmsPageOptional.get();
        }
        return null;
    }

}
