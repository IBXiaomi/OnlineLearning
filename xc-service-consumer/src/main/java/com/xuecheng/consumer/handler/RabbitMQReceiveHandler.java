package com.xuecheng.consumer.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.xuecheng.consumer.service.PageService;
import com.xuecheng.framework.baseConstant.RabbitMQConstant;
import com.xuecheng.framework.exception.CustomExceptionFactory;
import com.xuecheng.framework.model.response.CommonCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * consumer的接收消息处理
 *
 * @author 吧嘻小米
 * @date 2020/05/16
 */
@Component
@Slf4j
public class RabbitMQReceiveHandler {

    @Autowired
    PageService pageService;

    /**
     * 获取email队列的消息
     *
     * @param message 消息
     * @param channel 通道
     */
    @RabbitListener(queues = {RabbitMQConstant.TopicConstant.TOPIC_QUEUE_EMAIL})
    public void getEmailMessage(Message message, Channel channel) {
        try {
            byte[] getByteMessage = message.getBody();
            String getMessage = new String(getByteMessage, "utf-8");
            log.debug("get Message is :{}", getMessage);
        } catch (UnsupportedEncodingException e) {
            log.error("fail get email message {}", e.getMessage());
        }
    }

    /**
     * 获取sms队列的消息
     *
     * @param message
     * @param channel
     */
    @RabbitListener(queues = {RabbitMQConstant.TopicConstant.TOPIC_QUEUE_SMS})
    public void getSmsMessage(Message message, Channel channel) {
        try {
            byte[] getByteMessage = message.getBody();
            String getMessage = new String(getByteMessage, "utf-8");
            log.debug("get Message is :{}", getMessage);
        } catch (UnsupportedEncodingException e) {
            log.error("fail get email sms {}", e.getMessage());
        }
    }

    @RabbitListener(queues = {RabbitMQConstant.directConstant.CMS_PAGE_DIRECT_QUEUE})
    public boolean getCmsPageMessage(Message message) {
        try {
            String byteMessage = new String(message.getBody(), "utf-8");
            JSONObject messageObject = JSON.parseObject(byteMessage);
            String pageId = (String) messageObject.get("pageId");
            log.debug("get page id is {}", pageId);
            if (StringUtils.isEmpty(pageId)) {
                throw CustomExceptionFactory.getCustomException(CommonCode.CMS_ID_PARAMS);
            }
            return pageService.saveCmsPageToServer(pageId);
        } catch (Exception e) {
            log.error("fail get pageId {}", e.getMessage());
        }
        return false;
    }

}
