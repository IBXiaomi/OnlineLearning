package com.xuecheng.producer.impl.producer;

import com.rabbitmq.client.Channel;
import com.xuecheng.framework.rabbitMQConnection.CreateMQConnection;
import com.xuecheng.producer.RabbitMQProducer;

import java.io.IOException;

public class RabbitMQTypicProducer implements RabbitMQProducer {
    private static final String MESSAGE = "hello-baxixiaomi";

    private static final String TOPIC_EXCHANGE_NAME = "TOPIC_LOG";

    /**
     * topic,模糊匹配模式
     * 关键在与定义routekey，大小不能超过255字节，使用'.'作为分隔符，使用'*'和'#'作为匹配符,'#'代表匹配0和多个字符，'*'代表匹配一个分段的内容
     */
    @Override
    public void createProducer(String type) {
        try {
            String routeKey = "com.baxixiaomi.test.rabbitmq";
            Channel channel = CreateMQConnection.getChannel();
            channel.exchangeDeclare(TOPIC_EXCHANGE_NAME, type);
            channel.basicPublish(TOPIC_EXCHANGE_NAME, routeKey, null, MESSAGE.getBytes());
        } catch (IOException e) {

        }
    }
}
