package com.xuecheng.producer.impl.producer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.xuecheng.framework.rabbitMQConnection.CreateMQConnection;
import com.xuecheng.producer.RabbitMQProducer;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

/**
 * header 模式
 *
 * @author 吧嘻小米
 * @date 2020/05/16
 */
public class RabbitMQHeaderProducer implements RabbitMQProducer {
    private static final String MESSAGE = "hello-baxixiaomi";

    private static final String HEADER_EXCHANGE_NAME = "HEADER_LOG";

    private static final String HEADER_QUEUE_EMAIL = "HEADER_QUEUE_EMAIL";


    /**
     * header模式，主要在设置键值对
     *
     * @param type 工作模式
     */
    @Override
    public void createProducer(String type) {
        try {
            Channel channel = CreateMQConnection.getChannel();
            channel.queueDeclare(HEADER_QUEUE_EMAIL, true, false, false, null);
            Map<String, Object> header = new Hashtable<String, Object>();
            header.put("inform", "email");
            AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties.Builder();
            properties.headers(header);
            channel.queueBind(HEADER_QUEUE_EMAIL, HEADER_EXCHANGE_NAME, "", header);
            channel.basicPublish(HEADER_EXCHANGE_NAME, "", properties.build(), MESSAGE.getBytes());
        } catch (IOException e) {

        }
    }
}
