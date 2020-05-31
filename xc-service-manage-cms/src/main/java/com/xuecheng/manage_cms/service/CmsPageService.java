package com.xuecheng.manage_cms.service;

import com.alibaba.fastjson.JSON;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.xuecheng.framework.baseConstant.RabbitMQConstant;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.CustomException;
import com.xuecheng.framework.exception.CustomExceptionFactory;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import com.xuecheng.manage_cms.dao.CmsTemplateRepository;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * cms页面管理
 *
 * @author 吧嘻小米
 * @date 2020/04/19
 */
@Slf4j
@Service
public class CmsPageService {

    private static final String RABBITMQ_PROPERTIES = "rabbitmq.properties";

    @Autowired
    CmsPageRepository cmsPageRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private CmsTemplateRepository cmsTemplateRepository;

    @Autowired
    GridFsTemplate gridFsTemplate;

    @Autowired
    GridFSBucket gridFSBucket;

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 分页查询,条件查询
     *
     * @param page             当前页，mongodb查询是从第0页开始
     * @param size             每页展示的数据
     * @param queryPageRequest 查询条件
     * @return 查询结果
     */
    public QueryResponseResult findPage(int page, int size, QueryPageRequest queryPageRequest) {
        log.info("start to find cmsPage");
        // 初始化查询条件
        CmsPage cmsPage = new CmsPage();
        // withMatcher表示模糊匹配的方式，当前表示以pageAliase为模糊匹配项，匹配方式为包含
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        if (null == queryPageRequest) {
            queryPageRequest = new QueryPageRequest();
        }
        // 不能使用(null!=queryPageRequest.getSiteId())来判断是否为空，!=判断的是内存地址，不是实际的值
        if (StringUtils.isNotEmpty(queryPageRequest.getSiteId())) {
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        if (StringUtils.isNotEmpty(queryPageRequest.getTemplateId())) {
            cmsPage.setPageTemplate(queryPageRequest.getTemplateId());
        }
        if (StringUtils.isNotEmpty(queryPageRequest.getPageAliase())) {
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }
        if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            size = 10;
        }
        page = page - 1;
        Pageable pageable = PageRequest.of(page, size);
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);
        Page<CmsPage> cmsPagePage = cmsPageRepository.findAll(example, pageable);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(cmsPagePage.getContent());
        queryResult.setTotal(cmsPagePage.getTotalElements());
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    /**
     * 根据页面id查询页面
     *
     * @param id 页面id
     * @return 查询结果
     */
    public CmsPage findPageById(String id) {
        Optional<CmsPage> Optional = cmsPageRepository.findById(id);
        if (Optional.isPresent()) {
            return Optional.get();
        }
        return null;
    }

    /**
     * 根据页面id修改页面
     *
     * @param id      页面id
     * @param cmsPage 页面信息
     * @return 返回修改结果
     */
    public CmsPageResult editCmsPageById(String id, CmsPage cmsPage) {
        CmsPage saveCmsPage = null;
        log.info("start to edit cmsPage");
        if (StringUtils.isNotEmpty(id)) {
            CmsPage pageById = findPageById(id);
            if (null == cmsPage) {
                throw CustomExceptionFactory.getCustomException(CmsCode.CMS_HTML_TEMPLATES_NULL);
            }
            pageById.setSiteId(cmsPage.getSiteId());
            pageById.setPageAliase(cmsPage.getPageAliase());
            pageById.setPageTemplate(cmsPage.getPageTemplate());
            pageById.setPageName(cmsPage.getPageName());
            pageById.setPageCreateTime(cmsPage.getPageCreateTime());
            pageById.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
            pageById.setPageWebPath(cmsPage.getPageWebPath());
            pageById.setPageType(cmsPage.getPageType());
            pageById.setDataUrl(cmsPage.getDataUrl());
            pageById.setHtmlFileId(cmsPage.getHtmlFileId());
            cmsPageRepository.save(pageById);
            return new CmsPageResult(CommonCode.SUCCESS, cmsPage);
        } else {
            log.error("cmsPage id is null ,please check cmsPage id");
        }
        return new CmsPageResult(CommonCode.FAIL, null);
    }

    /**
     * 根据id删除页面
     *
     * @param id 页面id
     * @return 返回删除结果
     */
    public ResponseResult deleteCmsPage(String id) {
        log.info("start to delete cmspage");
        if (StringUtils.isNotEmpty(id)) {
            CmsPage pageById = findPageById(id);
            if (null != pageById) {
                cmsPageRepository.delete(pageById);
                return new ResponseResult(CommonCode.SUCCESS);
            }
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    /**
     * 新增页面
     *
     * @param cmsPage 新增页面
     * @return 返回插入结果
     */
    public CmsPageResult addCmsPage(CmsPage cmsPage) {
        log.info("start to add cmsPage");
        if (null == cmsPage) {
            log.error("cmsPage params is null");
            throw CustomExceptionFactory.getCustomException(CmsCode.CMS_ADD_PAGE_EXISTS_NAME);
        }
        // 首先要根据前端传进来的数据查询数据库中是否存在数据
        CmsPage oldCmsPage = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());
        if (null != oldCmsPage) {
            log.error("this cmsPage is exist , please check params");
            throw CustomExceptionFactory.getCustomException(CmsCode.CMS_ADD_PAGE_EXISTS_NAME);
        }
        // 保证页面id是数据库自己添加,自己增长
        cmsPage.setPageId(null);
        CmsPage newCmsPage = null;
        try {
            newCmsPage = cmsPageRepository.save(cmsPage);
        } catch (Exception e) {
            log.error("fail to save cmsPage {}", e.toString());
        }
        return new CmsPageResult(CommonCode.SUCCESS, newCmsPage);
    }

    /**
     * 根据页面id，查找模板，生成相应的html文件
     *
     * @param pageId 页面id
     */
    public String createHtmlByTemplate(String pageId) {
        // pageId查找DataURL
        String dataURL = getDataURLByPageId(pageId);
        // 通过DataURL获取数据模型
        Map model = getModelByDataURL(dataURL);
        // pageId查找模板文件id
        String template = getTemplateByPageId(pageId);
        // 生成静态话文件
        return createHtml(model, template);
    }

    /**
     * 生成html文件
     *
     * @param model    数据模型
     * @param template 模板
     * @return html文件
     */
    private String createHtml(Map model, String template) {
        try {
            Configuration configuration = new Configuration(Configuration.getVersion());
            StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
            stringTemplateLoader.putTemplate("template", template);
            configuration.setTemplateLoader(stringTemplateLoader);
            Template templateFinal = configuration.getTemplate("template", "UTF-8");
            return FreeMarkerTemplateUtils.processTemplateIntoString(templateFinal, model);
        } catch (IOException e) {
            log.error("");
        } catch (TemplateException e) {
            log.error("process Template Into String failed {}", e.getMessage());
        }
        return null;
    }

    /**
     * 根据页面id查询模板文件
     *
     * @param pageId 页面id
     * @return 模板文件的字符串
     */
    private String getTemplateByPageId(String pageId) {
        if (StringUtils.isEmpty(pageId)) {
            log.error("template id is null");
            throw new CustomException(CommonCode.CMS_PAGE_PARAMS);
        }
        try {
            Optional<CmsPage> optional = cmsPageRepository.findById(pageId);
            if (optional.isPresent()) {
                CmsPage cmsPage = optional.get();
                if (StringUtils.isNotEmpty(cmsPage.getTemplateId())) {
                    Optional<CmsTemplate> optionalCmsTemplate = cmsTemplateRepository.findById(cmsPage.getTemplateId());
                    if (optionalCmsTemplate.isPresent()) {
                        CmsTemplate cmsTemplate = optionalCmsTemplate.get();
                        String templateFileId = cmsTemplate.getTemplateFileId();
                        // 拼接查询条件
                        GridFSFile gridFsTemplateOne = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(templateFileId)));
                        // 打开下载流
                        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFsTemplateOne.getObjectId());
                        // 获取流对象
                        GridFsResource gridFsResource = new GridFsResource(gridFsTemplateOne, gridFSDownloadStream);
                        // 获取流中的对象
                        return IOUtils.toString(gridFsResource.getInputStream(), "UTF-8");
                    }
                }
            }
        } catch (IOException e) {
            log.error("File is not exist ,please check {}", e.getMessage());
        }

        return null;
    }

    /**
     * 根据dataurl获取数据模型
     *
     * @param dataURL 数据地址
     * @return 返回数据模型
     */
    private Map getModelByDataURL(String dataURL) {
        Map<String, Object> map = new HashMap<>();
        log.info("start to freemarker");
        ResponseEntity<Map> forEntity = restTemplate.getForEntity(dataURL, Map.class);
        Map body = forEntity.getBody();
        if (null == body) {
            throw new CustomException(CommonCode.SERVER_ERROR);
        }
        map.putAll(body);
        return map;
    }

    /**
     * 根据页面id获取dataurl
     *
     * @param pageId 页面id
     * @return 返回数据url
     */
    private String getDataURLByPageId(String pageId) {
        log.info("start to get dataURL by pageId");
        if (StringUtils.isEmpty(pageId)) {
            throw CustomExceptionFactory.getCustomException(CmsCode.CMS_PAGE_PAGE_ID_NULL);
        }
        Optional<CmsPage> optional = cmsPageRepository.findById(pageId);
        if (optional.isPresent()) {
            CmsPage cmsPage = optional.get();
            if (StringUtils.isNotEmpty(cmsPage.getDataUrl())) {
                return cmsPage.getDataUrl();
            }
        } else {
            log.error("cmsPage is not exist");
        }
        return "";
    }

    /**
     * 发布页面
     *
     * @param pageId 页面id
     */
    public ResponseResult publishPage(String pageId) {
        // 根据页面id生成静态化文件
        String html = createHtmlByTemplate(pageId);
        //将静态化页面存储在mongodb中
        CmsPage cmsPage = findPageById(pageId);
        if (null == cmsPage) {
            return new CmsPageResult(CmsCode.CMS_GENERATEHTML_SAVEHTMLERROR, cmsPage);
        }
        byte[] bytes = html.getBytes(StandardCharsets.UTF_8);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        // 获取到html文件的id
        ObjectId objectId = gridFsTemplate.store(byteArrayInputStream, cmsPage.getPageName());
        String htmlFileId = objectId.toString();
        cmsPage.setHtmlFileId(htmlFileId);
        // 获取routingkey
        String siteId = cmsPage.getSiteId();
        //saveRoutingKey(siteId);
        // 更新cmsPage页面
        CmsPageResult cmsPageResult = editCmsPageById(pageId, cmsPage);
        boolean result = sendMessageToConsumer(siteId, pageId);
        if (result) {
            return cmsPageResult;
        } else {
            return new ResponseResult(CmsCode.CMS_GENERATEHTML_SAVEHTMLERROR);
        }
    }

    /**
     * 将routingkey加载配置文件中
     *
     * @param routingKey key
     */
    private void saveRoutingKey(String routingKey) {
        try {
            Properties properties = PropertiesLoaderUtils.loadAllProperties(RABBITMQ_PROPERTIES);
            String fileRoutingKey = properties.getProperty("xuecheng.mq.routingKey");
            if (StringUtils.isEmpty(fileRoutingKey)) {
                properties.setProperty("xuecheng.mq.routingKey", routingKey);
            }
        } catch (IOException e) {
            log.error("load rabbitmq.properties failed {}", e.getMessage());
        }
    }

    /**
     * 向mq发送消息
     *
     * @param siteId routingKey
     * @param pageId 页面id
     */
    private boolean sendMessageToConsumer(String siteId, String pageId) {
        try {
            CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
            // 组织消息体
            Map<String, String> message = new HashMap<>();
            message.put("pageId", pageId);
            String jsonString = JSON.toJSONString(message);
            // 通知mq
            //rabbitTemplate.convertAndSend(RabbitMQConstant.TopicConstant.CMS_PAGE_EXCHANGE, siteId, jsonString);
            boolean result = (boolean) rabbitTemplate.convertSendAndReceive(RabbitMQConstant.directConstant.CMS_PAGE_DIRECT_EXCHANGE,
                    siteId, jsonString, correlationId);
            Thread.sleep(1000);
            log.error("producer send message is {}, consumer result is {}", message, result);
            return result;
        } catch (InterruptedException e) {
            log.error("The current thread is interrupted and will be cleared {}", e.getMessage());
        }
        return false;
    }
}

