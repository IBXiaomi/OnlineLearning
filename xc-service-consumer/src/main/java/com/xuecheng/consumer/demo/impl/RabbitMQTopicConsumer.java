package com.xuecheng.consumer.demo.impl;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.xuecheng.consumer.demo.RabbitMQConsumer;
import com.xuecheng.framework.rabbitMQConnection.CreateMQConnection;

import java.io.IOException;

/**
 * topic的consumer
 *
 * @author 吧嘻小米
 * @date 2020/05/16
 */
public class RabbitMQTopicConsumer implements RabbitMQConsumer {
    private static final String MESSAGE = "hello-baxixiaomi";

    private static final String TOPIC_EXCHANGE_NAME = "TOPIC_LOG";

    private static final String TOPIC_QUEUE_EMAIL = "TOPIC_QUEUE_EMAIL";

    private static final String TOPIC_QUEUE_SMS = "TOPIC_QUEUE_SMS";

    /**
     * topic,模糊匹配模式
     * 关键在与定义routekey，大小不能超过255字节，使用'.'作为分隔符，使用'*'和'#'作为匹配符,'#'代表匹配0和多个字符，'*'代表匹配一个分段的内容
     */
    @Override
    public void createConsumer(String type) {
        try {
            Channel channel = CreateMQConnection.getChannel();
            channel.exchangeDeclare(TOPIC_EXCHANGE_NAME, type);
            channel.queueDeclare(TOPIC_QUEUE_EMAIL,true,false,false,null);
            String routeKey = "inform.#.email.#";
            channel.queueBind(TOPIC_QUEUE_EMAIL, TOPIC_EXCHANGE_NAME, routeKey);
            channel.basicConsume(TOPIC_QUEUE_EMAIL, true, topicConsumer(channel));
        } catch (IOException e) {

        }
    }

    private static DefaultConsumer topicConsumer(Channel channel) {
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String exchange = envelope.getExchange();
                // 该参数在此模式下是没甚么用的
                String routingKey = envelope.getRoutingKey();
                System.out.println(routingKey);
                String message = new String(body, "utf-8");
                System.out.println(message);
            }
        };
        return consumer;
    }
}
