package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.domain.cms.request.QueryTemplateRequest;
import com.xuecheng.framework.domain.cms.response.code.CmsTemplateCode;
import com.xuecheng.framework.domain.cms.response.result.CmsTemplateResult;
import com.xuecheng.framework.exception.CustomException;
import com.xuecheng.framework.exception.CustomExceptionFactory;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.web.BaseController;
import com.xuecheng.manage_cms.dao.CmsTemplateRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
@Slf4j
public class CmsTemplateService extends BaseController {
    private static Map<ObjectId, String> objectIdMap = new HashMap<>();


    @Autowired
    private CmsTemplateRepository cmsTemplateRepository;

    @Autowired
    GridFsTemplate gridFsTemplate;

    /**
     * 查询所有的cms_template
     *
     * @return 返回查询结果
     */
    public QueryResponseResult findAll() {
        QueryResult queryResult = new QueryResult();
        try {
            List<CmsTemplate> cmsTemplates = cmsTemplateRepository.findAll();
            queryResult.setList(cmsTemplates);
            queryResult.setTotal(cmsTemplates.size());
        } catch (Exception e) {
            log.error("find all template is failed {}", e.getMessage());
        }
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    /**
     * 根据id查询cms_template表
     *
     * @param id id
     * @return 返回查询结果
     */
    public QueryResponseResult findById(String id) {
        CmsTemplate cmsTemplate = null;
        QueryResult queryResult = null;
        if (StringUtils.isEmpty(id)) {
            log.error("template id is null");
            throw new CustomException(CommonCode.CMS_PAGE_PARAMS);
        }
        try {
            Optional<CmsTemplate> cmsTemplateOptional = cmsTemplateRepository.findById(id);
            if (cmsTemplateOptional.isPresent()) {
                cmsTemplate = cmsTemplateOptional.get();
                List<CmsTemplate> cmsTemplateList = new ArrayList<>();
                cmsTemplateList.add(cmsTemplate);
                queryResult.setList(cmsTemplateList);
                return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
            } else {
                log.error("template id is invalid , please check the template id");
            }
        } catch (Exception e) {
            log.error("find template by id is failed {}", e.toString());
        }
        return new QueryResponseResult(CommonCode.FAIL, null);
    }

    /**
     * 页面模板的查询
     *
     * @param page                 当前页
     * @param size                 每页显示的数量
     * @param queryTemplateRequest 条件查询的类
     * @return 返回查询结果
     */
    public QueryResponseResult findCmsTemplate(int page, int size, QueryTemplateRequest queryTemplateRequest) {
        log.info("start to findCmsTemplate");
        CmsTemplate cmsTemplate = new CmsTemplate();
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("templateName", ExampleMatcher.GenericPropertyMatchers.contains());
        if (null == queryTemplateRequest) {
            queryTemplateRequest = new QueryTemplateRequest();
        }
        if (StringUtils.isNotEmpty(queryTemplateRequest.getTemplateFileId())) {
            cmsTemplate.setTemplateId(queryTemplateRequest.getTemplateId());
        }
        if (StringUtils.isNotEmpty(queryTemplateRequest.getTemplateName())) {
            cmsTemplate.setTemplateName(queryTemplateRequest.getTemplateName());
        }
        // 默认从第一页开始查询
        if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            size = 10;
        }
        page = page - 1;
        Pageable pageable = PageRequest.of(page, size);
        Example<CmsTemplate> example = Example.of(cmsTemplate, exampleMatcher);
        Page<CmsTemplate> cmsTemplatePage = cmsTemplateRepository.findAll(example, pageable);
        List<CmsTemplate> cmsTemplateList = cmsTemplatePage.getContent();
        long totalElements = cmsTemplatePage.getTotalElements();
        QueryResult queryResult = new QueryResult();
        queryResult.setList(cmsTemplateList);
        queryResult.setTotal(totalElements);
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    /**
     * 新增模板
     *
     * @param cmsTemplate 模板参数
     * @return 新增结果
     */
    public CmsTemplateResult addCmsTemplate(MultipartFile multipartFile) {
        log.error("start to add cmsTemplate");
//        if (null == cmsTemplate) {
//            throw CustomExceptionFactory.getCustomException(CommonCode.CMS_PAGE_PARAMS);
//        }
//        if (StringUtils.isEmpty(cmsTemplate.getTemplateFileId()) || StringUtils.isEmpty(cmsTemplate.getTemplateName())) {
//            throw CustomExceptionFactory.getCustomException(CmsTemplateCode.CMS_TEMPLATE_PARAMTERS);
//        }
//        // 根据templateFileId和templateName判断当前模板是否存在
//        CmsTemplate findCmsTemplate = cmsTemplateRepository.findCmsTemplateByTemplateFileIdAndTemplateName(cmsTemplate.getTemplateFileId(), cmsTemplate.getTemplateName());
//        if (null != findCmsTemplate) {
//            log.error("this template is exist , please check parameters");
//            throw CustomExceptionFactory.getCustomException(CmsTemplateCode.CMS_TEMPLATE_EXIST);
//        }
////        if (null == multipartFile) {
////            throw CustomExceptionFactory.getCustomException(CmsTemplateCode.CMS_TEMPLATE_FILE_NULL);
////        }
////        ObjectId objectId = saveCmsTemplateToMongoDB(multipartFile);
//        // cmsTemplate.setTemplateFileId(objectId.toString());
//        //cmsTemplate.setTemplateId(null);
//        CmsTemplate newCmsTemplate = cmsTemplateRepository.save(cmsTemplate);
//        // 需要将模板存储到mongoDB自带的文件存储中
//        return new CmsTemplateResult(CmsTemplateCode.CMS_TEMPLATE_ADD_SUCCESS, newCmsTemplate);
        return null;
    }

    /**
     * 将模板文件保存在mongodb中
     *
     * @return 返回保存结果
     */
    private ObjectId saveCmsTemplateToMongoDB(MultipartFile multipartFile) {
        log.info("begin to save file to mongoDB");
        File file = handleMultipartFile(multipartFile);
        // 创建文件
        try {
            if (null != multipartFile) {
                ObjectId objectId = gridFsTemplate.store(new FileInputStream(file), multipartFile.getOriginalFilename());
                objectIdMap.put(objectId, multipartFile.getOriginalFilename());
                log.error("create objectId is " + objectId);
                return objectId;
            }
        } catch (FileNotFoundException e) {
            log.error("file is not exist {}", e.getMessage());
            throw CustomExceptionFactory.getCustomException(CmsTemplateCode.CMS_TEMPLATE_FILE_STORE_FAILED);
        }
        return null;
    }


    /**
     * 上传模板文件
     *
     * @param multipartFile 模板文件
     * @return 返回上传结果
     */
    public CmsTemplateResult uploadTemplateFile(MultipartFile multipartFile, CmsTemplate cmsTemplate) {
        log.info("multipartFile***" + multipartFile + "cmsTemplate****" + cmsTemplate);
        log.info("start to upload template file");
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            if (null == multipartFile) {
                throw CustomExceptionFactory.getCustomException(CmsTemplateCode.CMS_TEMPLATE_FILE_NULL);
            }
            File file = handleMultipartFile(multipartFile);
            log.error("multipartFile size " + multipartFile.getSize());
            inputStream = multipartFile.getInputStream();
            outputStream = new FileOutputStream(file);
            byte[] data = new byte[1024];
            int len = 0;
            while ((len = (inputStream.read())) != -1) {
                log.info("begin write file to target dest");
                outputStream.write(data);
            }
            return new CmsTemplateResult(CmsTemplateCode.CMS_TEMPLATE_FILE_UPLOAD_SUCCESS, cmsTemplate);
        } catch (IOException e) {
            log.error("file upload error {}", e.getMessage());
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                log.error("close stream error,file upload error {}", e.getMessage());
            }
        }
        return null;
    }

    /**
     * 处理文件
     *
     * @param multipartFile 传递的文件
     * @return 返回处理的文件
     */
    private File handleMultipartFile(MultipartFile multipartFile) {
        log.info("start to handle file");
        String nullFile = "";
        try {
            String fileName = multipartFile.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf('.'));
            String dest = "E:/TEST/";
            File newFile = new File(dest + new Date().getTime() + suffix);
            File parentFile = newFile.getParentFile();
            if (!parentFile.exists() || parentFile.isDirectory()) {
                parentFile.mkdirs();
            }
            if (!newFile.exists()) {
                boolean newFileResult = newFile.createNewFile();
                if (newFileResult) {
                    log.info("create file success");
                    return newFile;
                } else {
                    log.info("create file failed");
                    return new File(nullFile);
                }
            }
        } catch (IOException e) {
            log.error("handleMultipartFile failed {}", e.getMessage());
        }
        // 需要优化
        return new File(nullFile);
    }

}
