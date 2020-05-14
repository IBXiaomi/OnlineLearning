package com.xuecheng.consumer;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * rabbitmq测试消费者
 *
 * @author 吧嘻小米
 * @date 2020/05/14
 */
@Slf4j
public class RabbitMQConsumer {
    private static final String QUEUE = "baxixiaomi";

    public static void main(String[] args) {
        try {
            // 设置连接，创建通道
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("192.168.200.131");
            connectionFactory.setPort(5672);
            connectionFactory.setUsername("wjw");
            connectionFactory.setPassword("wjw");
            connectionFactory.setVirtualHost("/");
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            /**
             * String queue 队列名称, boolean durable 是否持久化, boolean exclusive 是否独占, boolean autoDelete, Map<String, Object> arguments
             */
            // 获取交换机
            channel.exchangeDeclare("log", "direct");
            String queueName = channel.queueDeclare().getQueue();
            // 绑定交换机和队列
            channel.queueBind(queueName, "log", "");
            // 声明队列
            channel.queueDeclare(QUEUE, false, false, true, null);
            DefaultConsumer defaultConsumer = new DefaultConsumer(channel);
            channel.basicConsume("QUEUE", defaultConsumer);
            //log.info("start to consuming:" + message);

        } catch (IOException e) {
            //log.error("create connection failed {}", e.getMessage());
        } catch (TimeoutException e) {
            //log.error("create channel failed {}", e.getMessage());
        }
    }
}
