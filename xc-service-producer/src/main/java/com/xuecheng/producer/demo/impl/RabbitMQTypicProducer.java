package com.xuecheng.producer.demo.impl;

import com.rabbitmq.client.Channel;
import com.xuecheng.framework.rabbitMQConnection.CreateMQConnection;
import com.xuecheng.producer.RabbitMQProducer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * topic模式的生产者
 *
 * @author 吧嘻小米
 * @date 2020/05/21
 */
@Slf4j
public class RabbitMQTypicProducer implements RabbitMQProducer {
    private static final String MESSAGE = "hello-baxixiaomi";

    private static final String TOPIC_EXCHANGE_NAME = "TOPIC_LOG";

    private static final String TOPIC_QUEUE_EMAIL = "TOPIC_QUEUE_EMAIL";


    /**
     * topic,模糊匹配模式
     * 关键在与定义routekey，大小不能超过255字节，使用'.'作为分隔符，使用'*'和'#'作为匹配符,'#'代表匹配0和多个字符，'*'代表匹配一个分段的内容
     */
    @Override
    public void createProducer(String type) {
        try {
            String routeKey = "inform.#.email.#";
            Channel channel = CreateMQConnection.getChannel();
            channel.exchangeDeclare(TOPIC_EXCHANGE_NAME, type);
            channel.queueDeclare(TOPIC_QUEUE_EMAIL, true, false, false, null);
            channel.queueBind(TOPIC_QUEUE_EMAIL, TOPIC_EXCHANGE_NAME, routeKey);
            channel.basicPublish(TOPIC_EXCHANGE_NAME, "inform.email", null, MESSAGE.getBytes());
        } catch (IOException e) {
            log.error("Declare queue failed {}", e.getMessage());
        }
    }
}
