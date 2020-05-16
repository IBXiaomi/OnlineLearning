package com.xuecheng.framework.rabbitMQConnection;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.xuecheng.framework.baseConstant.RabbitMQConstant;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 设置mq连接信息
 *
 * @author 吧嘻小米
 * @date 2020/05/16
 */
@Slf4j
public class CreateMQConnection {

    private static Channel channel;

    public static Channel getChannel() {
        try {
            if (null == channel) {
                channel = CreateMQConnection.createConnection();
                return channel;
            }
        } catch (IOException e) {
            //log.error("create MQ connection failed {}", e.getMessage());
        } catch (TimeoutException e) {
            //log.error("create MQ connection failed {}", e.getMessage());
        }
        return null;
    }

    /**
     * 创建连接，生成通道
     */
    private static Channel createConnection() throws IOException, TimeoutException {
        //log.info("start to createMQConnection");
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RabbitMQConstant.RABBITMQ_ADDRESS);
        connectionFactory.setPort(RabbitMQConstant.PORT);
        connectionFactory.setUsername(RabbitMQConstant.USERNAME);
        connectionFactory.setPassword(RabbitMQConstant.PASSWORD);
        connectionFactory.setVirtualHost(RabbitMQConstant.Delimiter);
        Connection connection = connectionFactory.newConnection();
        return connection.createChannel();
    }
}
