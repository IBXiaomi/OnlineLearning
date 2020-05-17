package com.xuecheng.consumer.impl;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.xuecheng.consumer.RabbitMQConsumer;
import com.xuecheng.framework.rabbitMQConnection.CreateMQConnection;

import java.io.IOException;

/**
 * 发布订阅的consumer
 *
 * @author 吧嘻小米
 * @date 2020/05/16
 */
public class RabbitMQFanoutConsumer implements RabbitMQConsumer {
    private static final String MESSAGE = "hello-baxixiaomi";

    private static final String EXCHANGE_NAME = "LOG";

    private static final String EXCHANGE_QUEUE_SMS = "SMS";

    private static final String EXCHANGE_QUEUE_EMAIL = "EMAIL";

    /**
     * fanout发布/订阅模式
     * 需要将队列和交换机绑定，不需要指定队列名称,重点区别与默认方式为，不需要特殊指定队列名称，但是需要在消费方将交换机和队列绑定
     */
    @Override
    public void createConsumer(String type) {
        try {
            Channel channel = CreateMQConnection.getChannel();
            channel.exchangeDeclare(EXCHANGE_NAME,type);
            // 声明队列
            channel.queueDeclare(EXCHANGE_QUEUE_SMS, true, false, false, null);
            channel.queueDeclare(EXCHANGE_QUEUE_EMAIL, true, false, false, null);
            // 绑定队列和交换机
            channel.queueBind(EXCHANGE_QUEUE_SMS, EXCHANGE_NAME, "");
            channel.queueBind(EXCHANGE_QUEUE_EMAIL, EXCHANGE_NAME, "");
            channel.basicConsume(EXCHANGE_QUEUE_SMS, true, fanoutConsumer(channel));
            channel.basicConsume(EXCHANGE_QUEUE_EMAIL, true, fanoutConsumer(channel));
        } catch (IOException e) {

        }
    }

    private static DefaultConsumer fanoutConsumer(Channel channel) {
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String exchange = envelope.getExchange();
                System.out.println(exchange);
                // 该参数在此模式下是没甚么用的
                String routingKey = envelope.getRoutingKey();
                String message = new String(body, "utf-8");
                System.out.println(message);
            }
        };
        return consumer;
    }
}
