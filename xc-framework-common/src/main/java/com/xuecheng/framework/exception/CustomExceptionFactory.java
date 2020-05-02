package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;

/**
 * 获取自定义异常类对象的工厂
 *
 * @author 吧嘻小米
 * @date 2020/05/02
 */
public class CustomExceptionFactory {

    public static CustomException getCustomException(ResultCode resultCode) {
        return new CustomException(resultCode);
    }
}
