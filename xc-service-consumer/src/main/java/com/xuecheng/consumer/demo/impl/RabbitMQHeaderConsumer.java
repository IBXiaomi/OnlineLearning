package com.xuecheng.consumer.demo.impl;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.xuecheng.consumer.demo.RabbitMQConsumer;
import com.xuecheng.framework.rabbitMQConnection.CreateMQConnection;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

/**
 * header 模式
 *
 * @author 吧嘻小米
 * @date 2020/05/16
 */
@Slf4j
public class RabbitMQHeaderConsumer implements RabbitMQConsumer {
    private static final String MESSAGE = "hello-baxixiaomi";

    private static final String HEADER_EXCHANGE_NAME = "HEADER_LOG";

    private static final String HEADER_QUEUE_EMAIL = "HEADER_QUEUE_EMAIL";


    /**
     * header模式，主要在设置键值对
     * @param type 工作模式
     */
    @Override
    public void createConsumer(String type) {
        try {
            Channel channel = CreateMQConnection.getChannel();
            channel.queueDeclare(HEADER_QUEUE_EMAIL, true, false, false, null);
            Map<String, Object> header = new Hashtable<String, Object>();
            header.put("inform", "email");
            AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties.Builder();
            properties.headers(header);
            channel.queueBind(HEADER_QUEUE_EMAIL, HEADER_EXCHANGE_NAME, "", header);
            channel.basicConsume(HEADER_QUEUE_EMAIL, true, headerConsumer(channel));
        } catch (IOException e) {
            log.error("Declare queue failed {}",e.getMessage());
        }
    }
    private static DefaultConsumer headerConsumer(Channel channel) {
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "utf-8");
                System.out.println(message);
            }
        };
        return consumer;
    }
}
