package com.xuecheng.framework.domain.cms.response.code;

import com.xuecheng.framework.model.response.ResultCode;

/**
 * cms_template相关返回值
 *
 * @author 吧嘻小米
 * @date 2020/05/06
 */
public enum CmsTemplateCode implements ResultCode {

    CMS_TEMPLATE_EXIST(false, 12121, "当前模板已存在"),
    CMS_TEMPLATE_PARAMTERS(false, 12122, "当前模板参数异常"),
    CMS_TEMPLATE_ADD_SUCCESS(true,12123,"模板新增成功");

    //操作代码
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    CmsTemplateCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }


    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }


}
