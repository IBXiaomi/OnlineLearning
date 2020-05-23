package com.xuecheng.consumer.demo.impl;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.xuecheng.consumer.demo.RabbitMQConsumer;
import com.xuecheng.framework.rabbitMQConnection.CreateMQConnection;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 默认模式的consumer
 *
 * @author 吧嘻小米
 * @date 2020/05/16
 */
@Slf4j
public class RabbitMQDefaultConsumer implements RabbitMQConsumer {
    private static final String QUEUE = "baxixiaomi";

    private static final String MESSAGE = "hello-baxixiaomi";


    @Override
    public void createConsumer(String type) {
        try {
            Channel channel = CreateMQConnection.getChannel();
            channel.queueDeclare(QUEUE, true, false, false, null);
            channel.basicConsume(QUEUE, true, directConsumer(channel));
        } catch (IOException e) {
            log.error("Declare queue failed {}",e.getMessage());
        }
    }

    private static DefaultConsumer directConsumer(Channel channel) {
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "utf-8");
                System.out.println(message);
            }
        };
        return consumer;
    }
}
