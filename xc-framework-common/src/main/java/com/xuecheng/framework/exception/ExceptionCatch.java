package com.xuecheng.framework.exception;

import com.google.common.collect.ImmutableMap;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 对于异常类捕获的处理
 *
 * @author 吧嘻小米
 * @date 2020/05/02
 */
@RestControllerAdvice
@Slf4j
public class ExceptionCatch {

    // 线程安全，且初始化后不可修改的map
    private static ImmutableMap<Class<? extends Throwable>, ResultCode> EXCEPTION;

    private static ImmutableMap.Builder<Class<? extends Throwable>, ResultCode> builder = ImmutableMap.builder();


    static {
        builder.put(HttpMessageNotReadableException.class, CommonCode.CMS_PAGE_PARAMS);
        EXCEPTION = builder.build();
    }

    /**
     * 自定义异常类的处理
     *
     * @param customException 自定义异常类
     * @return 响应结果
     */
    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    public ResponseResult getCustomException(CustomException customException) {
        log.error("catch customException {}", customException.getMessage());
        ResultCode resultCode = customException.getResultCode();
        return new ResponseResult(resultCode);
    }

    /**
     * 对于未知异常的处理
     *
     * @param exception 异常类
     * @return 响应结果
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseResult getException(Exception exception) {
        log.error("catch exception {}", exception.getMessage());
        ResultCode resultCode = EXCEPTION.get(exception.getClass());
        if (null != resultCode) {
            return new ResponseResult(resultCode);
        } else {
            return new ResponseResult(CommonCode.SERVER_ERROR);
        }

    }
}
