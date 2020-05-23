package com.xuecheng.consumer.handler;

import com.rabbitmq.client.Channel;
import com.xuecheng.framework.baseConstant.RabbitMQConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
            log.error("fail get email message {}",e.getMessage());
        }
    }

    @RabbitListener(queues = {RabbitMQConstant.TopicConstant.TOPIC_QUEUE_SMS})
    public void getSmsMessage(Message message, Channel channel) {
        try {
            byte[] getByteMessage = message.getBody();
            String getMessage = new String(getByteMessage, "utf-8");
            log.debug("get Message is :{}", getMessage);
        } catch (UnsupportedEncodingException e) {
            log.error("fail get email sms {}",e.getMessage());
        }
    }
}
