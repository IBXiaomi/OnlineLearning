package com.xuecheng.managecms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.managecms.dao.CmsPageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * cms页面服务
 *
 * @author wjw
 * @since JDK1.9
 */
@Service
@Slf4j
public class PageService {

    @Autowired
    CmsPageRepository cmsPageRepository;
    /**
     * 分页查询
     *
     * @param page             当前页
     * @param size             每页显示的数据个数
     * @param queryPageRequest 查询条件
     * @return 查询结果
     */
    public QueryResponseResult findPage(int page, int size,
                                 QueryPageRequest queryPageRequest){
        log.info("start to findPage in pageServiceImpl");
        if (page <= 0) {
            page = 1;
        }
        page = page - 1;
        if (size < 0) {
            size = 10;
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<CmsPage> cmsPages = cmsPageRepository.findAll(pageable);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(cmsPages.getContent());
        queryResult.setTotal(cmsPages.getTotalPages());
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }
}
