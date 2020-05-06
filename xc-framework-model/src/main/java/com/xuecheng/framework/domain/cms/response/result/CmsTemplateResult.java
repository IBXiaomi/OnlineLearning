package com.xuecheng.framework.domain.cms.response.result;

import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;

/**
 * 页面模板修改响应结果
 *
 * @author 吧嘻小米
 * @date 2020/05/05
 */
public class CmsTemplateResult extends ResponseResult {

    CmsTemplate cmsTemplate;

    public CmsTemplateResult(ResultCode resultCode, CmsTemplate cmsTemplate) {
        super(resultCode);
        this.cmsTemplate = cmsTemplate;
    }
}
