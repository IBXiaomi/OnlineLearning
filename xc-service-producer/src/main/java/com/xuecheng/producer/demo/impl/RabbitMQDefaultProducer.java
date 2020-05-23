package com.xuecheng.producer.demo.impl;

import com.rabbitmq.client.Channel;
import com.xuecheng.framework.rabbitMQConnection.CreateMQConnection;
import com.xuecheng.producer.RabbitMQProducer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 默认模式的producer
 *
 * @author 吧嘻小米
 * @date 2020/05/16
 */
@Slf4j
public class RabbitMQDefaultProducer implements RabbitMQProducer {
    private static final String QUEUE = "baxixiaomi";

    private static final String MESSAGE = "hello-baxixiaomi";

    @Override
    public void createProducer(String type) {
        try {
            Channel channel = CreateMQConnection.getChannel();
            channel.queueDeclare(QUEUE, true, false, false, null);
            channel.basicPublish("", QUEUE, null, MESSAGE.getBytes());
        } catch (IOException e) {
            log.error("declare queue failed {}", e.getMessage());
        }
    }
}
