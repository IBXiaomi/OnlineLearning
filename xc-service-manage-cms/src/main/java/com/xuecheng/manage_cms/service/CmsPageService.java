package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * cms页面管理
 *
 * @author 吧嘻小米
 * @date 2020/04/19
 */
@Slf4j
@Service
public class CmsPageService {

    @Autowired
    CmsPageRepository cmsPageRepository;

    /**
     * 分页查询
     *
     * @param page             当前页，mongodb查询是从第0页开始
     * @param size             每页展示的数据
     * @param queryPageRequest 查询条件
     * @return 查询结果
     */
    public QueryResponseResult findPage(int page, int size, QueryPageRequest queryPageRequest) {
        log.info("start to service findPage");
        if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            size = 10;
        }
        page = page - 1;
        Pageable pageable = PageRequest.of(page, size);
        Page<CmsPage> cmsPagePage = cmsPageRepository.findAll(pageable);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(cmsPagePage.getContent());
        queryResult.setTotal(cmsPagePage.getTotalElements());
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }
}

