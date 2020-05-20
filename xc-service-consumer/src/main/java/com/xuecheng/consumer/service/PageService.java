package com.xuecheng.consumer.service;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.xuecheng.consumer.dao.CmsPageRepository;
import com.xuecheng.consumer.dao.CmsSiteRepository;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsSite;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * 页面发布，使用mq
 *
 * @author 吧嘻小米
 * @date 2020/05/20
 */
@Service
@Slf4j
public class PageService {

    @Autowired
    CmsPageRepository cmsPageRepository;

    @Autowired
    CmsSiteRepository cmsSiteRepository;

    @Autowired
    GridFSBucket gridFSBucket;

    @Autowired
    GridFsTemplate gridFsTemplate;

    /**
     * 根据将要发布的页面id，在数据库中查找对应的静态化页面，将页面保存到本地
     *
     * @param pageId 页面id
     */
    public void saveCmsPageToGridFs(String pageId) {
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        if (StringUtils.isEmpty(pageId)) {
            log.error("pageId is null ,find cmsPage failed");
            return;
        }
        try {
            CmsPage cmsPage = getCmsPageById(pageId);
            inputStream = findHtmlFileById(cmsPage.getHtmlFileId());
            CmsSite cmsSite = findCmsSitePathById(cmsPage.getSiteId());
            String sitePhysicalPath = cmsSite.getSitePhysicalPath();
            String path = sitePhysicalPath + cmsPage.getPagePhysicalPath() + cmsPage.getPageName();
            fileOutputStream = new FileOutputStream(new File(path));
            IOUtils.copy(inputStream, fileOutputStream);
        } catch (IOException e) {
            log.error("get cmsPage failed {}", e.getMessage());
        } finally {
            try {
                inputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                log.error("close inputStream or outputStream failed {}", e.getMessage());
            }
        }

    }
    /**
     * 根据id获取当前站点的相对路径
     *
     * @param siteId 站点id
     * @return 路径
     */
    private CmsSite findCmsSitePathById(String siteId) {
        if (StringUtils.isEmpty(siteId)) {
            log.error("siteId is null , find cmsSite failed");
        } else {
            Optional<CmsSite> cmsSiteOptional = cmsSiteRepository.findById(siteId);
            if (cmsSiteOptional.isPresent()) {
                return cmsSiteOptional.get();
            }
        }
        return null;
    }


    /**
     * 根据id查询html文件
     *
     * @param htmlFileId html文件的id
     */
    private InputStream findHtmlFileById(String htmlFileId) {
        if (StringUtils.isEmpty(htmlFileId)) {
            log.error("htmlFileId is null , find htmlFile failed");
        } else {
            try {
                GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(htmlFileId)));
                GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
                // 获取流对象
                GridFsResource gridFsResource = new GridFsResource(gridFSFile, gridFSDownloadStream);
                return gridFsResource.getInputStream();
            } catch (IOException e) {
                log.error("get htmlFile stream failed {}", e.getMessage());
            }
        }
        return null;
    }

    /**
     * 根据页面id查询cmsPage
     *
     * @param pageId 页面id
     * @return 页面
     */
    private CmsPage getCmsPageById(String pageId) {
        Optional<CmsPage> cmsPageOptional = cmsPageRepository.findById(pageId);
        if (cmsPageOptional.isPresent()) {
            return cmsPageOptional.get();
        }
        return null;
    }

}
