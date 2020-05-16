package com.xuecheng.producer;

import com.rabbitmq.client.Channel;
import com.xuecheng.framework.rabbitMQConnection.CreateMQConnection;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * rabbitMQ测试入门
 *
 * @author 吧嘻小米
 * @date 2020/05/14
 */
@Slf4j
public class RabbitMQProducer {
    private static final String QUEUE = "baxixiaomi";

    private static final String MESSAGE = "hello-baxixiaomi";

    public static void main(String[] args) {

        try {
            // 设置连接，创建通道
            Channel channel = CreateMQConnection.createConnection();
            /**
             * String queue 队列名称, boolean durable 是否持久化, boolean exclusive 是否独占, boolean autoDelete, Map<String, Object> arguments
             */
            channel.queueDeclare(QUEUE, true, false, false, null);
            /**
             * String exchange 交换机, String routingKey, BasicProperties props, byte[] body
             */
            //channel.exchangeDeclare("log", "direct");
            channel.basicPublish("", QUEUE, null, MESSAGE.getBytes());
            //log.info("start to producer message:" + MESSAGE);
            System.out.println("start to producer message:" + MESSAGE);
        } catch (IOException e) {
            // log.error("create connection failed {}", e.getMessage());
        } catch (TimeoutException e) {
            //log.error("create channel failed {}", e.getMessage());
        }
    }
}
