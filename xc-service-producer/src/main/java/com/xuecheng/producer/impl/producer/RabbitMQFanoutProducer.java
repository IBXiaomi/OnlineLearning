package com.xuecheng.producer.impl.producer;

import com.rabbitmq.client.Channel;
import com.xuecheng.framework.rabbitMQConnection.CreateMQConnection;
import com.xuecheng.producer.RabbitMQProducer;

import java.io.IOException;

public class RabbitMQFanoutProducer implements RabbitMQProducer {
    private static final String MESSAGE = "hello-baxixiaomi";

    private static final String EXCHANGE_NAME = "LOG";

    private static final String EXCHANGE_QUEUE_SMS = "SMS";

    private static final String EXCHANGE_QUEUE_EMAIL = "EMAIL";


    /**
     * fanout发布/订阅模式
     * 需要将队列和交换机绑定，不需要指定队列名称,重点区别与默认方式为，不需要特殊指定队列名称，但是需要在消费方将交换机和队列绑定
     */
    @Override
    public void createProducer(String type) {
        try {
            Channel channel = CreateMQConnection.getChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, type);
            // 声明队列
            channel.queueDeclare(EXCHANGE_QUEUE_SMS, true, false, false, null);
            channel.queueDeclare(EXCHANGE_QUEUE_EMAIL, true, false, false, null);
            // 绑定队列和交换机
            channel.queueBind(EXCHANGE_QUEUE_SMS, EXCHANGE_NAME, "");
            channel.queueBind(EXCHANGE_QUEUE_EMAIL, EXCHANGE_NAME, "");
            channel.basicPublish(EXCHANGE_NAME, "", null, MESSAGE.getBytes());
        } catch (IOException e) {

        }
    }
}
