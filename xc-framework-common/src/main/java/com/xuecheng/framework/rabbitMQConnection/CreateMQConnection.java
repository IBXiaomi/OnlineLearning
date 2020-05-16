package com.xuecheng.framework.rabbitMQConnection;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.xuecheng.framework.baseConstant.Constant;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 设置mq连接信息
 */
@Slf4j
public class CreateMQConnection {

    /**
     * 创建连接，生成通道
     */
    public static Channel createConnection() throws IOException, TimeoutException {
        log.info("start to createMQConnection");
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(Constant.RABBITMQ_ADDRESS);
        connectionFactory.setPort(Constant.PORT);
        connectionFactory.setUsername(Constant.USERNAME);
        connectionFactory.setPassword(Constant.PASSWORD);
        connectionFactory.setVirtualHost("/");
        Connection connection = connectionFactory.newConnection();
        return connection.createChannel();
    }
}
