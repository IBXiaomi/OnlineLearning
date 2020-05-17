package com.xuecheng.consumer.handler;

import com.rabbitmq.client.Channel;
import com.xuecheng.framework.baseConstant.RabbitMQConstant;
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
public class RabbitMQReceiveHandler {

    /**
     * 获取email队列的消息
     * @param message 消息
     * @param channel 通道
     */
    @RabbitListener(queues = {RabbitMQConstant.TopicConstant.TOPIC_QUEUE_EMAIL})
    public void getEmailMessage(Message message, Channel channel) {
        try {
            byte[] getByteMessage = message.getBody();
            String getMessage = new String(getByteMessage, "utf-8");
            System.out.println(getMessage);
        } catch (UnsupportedEncodingException e) {

        }
    }

    @RabbitListener(queues = {RabbitMQConstant.TopicConstant.TOPIC_QUEUE_SMS})
    public void getSmsMessage(Message message, Channel channel) {
        try {
            byte[] getByteMessage = message.getBody();
            String getMessage = new String(getByteMessage, "utf-8");
            System.out.println(getMessage);
        } catch (UnsupportedEncodingException e) {

        }
    }
}
