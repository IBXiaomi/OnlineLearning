package com.xuecheng.manage_cms.dao;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.xuecheng.framework.domain.cms.CmsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * 模板页面查询测试类
 *
 * @author 吧嘻小米
 * @date 2020/05/05
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class CmsTemplateRepositoryTest {



    @Autowired
    CmsTemplateRepository cmsTemplateRepository;

    // 文件存储客户端
    @Autowired
    GridFsTemplate gridFsTemplate;

    @Autowired
    GridFSBucket gridFSBucket;

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

    @Test
    public void findCmsTemplateByTemplateFileIdAndTemplateName() {
        String templateFileId = "5ad8a51f68db5240b42e5fea";
        String templateName = "课程详情页面";
        CmsTemplate cmsTemplate = cmsTemplateRepository.findCmsTemplateByTemplateFileIdAndTemplateName(templateFileId, templateName);
        if (null != cmsTemplate) {
            System.out.println(cmsTemplate);
        }
    }

    /**
     * 测试将文件存储到GridFS
     */
    @Test
    public void saveCmsTemplateToMongoDB() {
        // 创建文件
        try {
            String path = CmsTemplateRepositoryTest.class.getResource("/").getPath();
            File file = new File(path + "/templates/index_banner.ftl");
            if (file.exists()) {
                ObjectId objectId = gridFsTemplate.store(new FileInputStream(file), "index_banner.ftl");
                log.error("create objectId is " + objectId);
            }
        } catch (FileNotFoundException e) {
            log.error("file is not exist {}", e.getMessage());
        }
    }

    /**
     * 将文件从mongodb中取出
     */
    @Test
    public void getCmsTemplateFromMongoDB() throws IOException {

        // 拼接查询条件
        GridFSFile gridFsTemplateOne = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is("5eb2c639aaf33565bcfb05c5")));
        // 打开下载流
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFsTemplateOne.getObjectId());
        // 获取流对象
        GridFsResource gridFsResource = new GridFsResource(gridFsTemplateOne, gridFSDownloadStream);
        // 获取流中的对象
        String string = IOUtils.toString(gridFsResource.getInputStream(), "UTF-8");
        System.out.println(string);
    }


}
