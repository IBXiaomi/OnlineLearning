package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsSiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CmsSiteService {

    @Autowired
    private CmsSiteRepository cmsSiteRepository;

    /**
     * 查询所有的cms_site
     *
     * @return 返回查询结果
     */
    public QueryResponseResult findAll() {
        List<CmsSite> cmsSites = cmsSiteRepository.findAll();
        QueryResult queryResult = new QueryResult();
        queryResult.setList(cmsSites);
        queryResult.setTotal(cmsSites.size());
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    /**
     * 根据id查询cms_site表
     *
     * @param id id
     * @return 返回查询结果
     */
    public QueryResponseResult findById(String id) {
        Optional<CmsSite> cmsSiteOptional = cmsSiteRepository.findById(id);
        CmsSite cmsSite = new CmsSite();
        if (cmsSiteOptional.isPresent()) {
            cmsSite = cmsSiteOptional.get();
        }
        QueryResult queryResult = new QueryResult();
        List<CmsSite> cmsSiteList = new ArrayList<>();
        cmsSiteList.add(cmsSite);
        queryResult.setList(cmsSiteList);
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }
}
