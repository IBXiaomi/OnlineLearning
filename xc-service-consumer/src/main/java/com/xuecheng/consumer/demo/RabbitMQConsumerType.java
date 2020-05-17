package com.xuecheng.consumer.demo;

import com.xuecheng.consumer.demo.impl.RabbitMQDefaultConsumer;
import com.xuecheng.consumer.demo.impl.RabbitMQFanoutConsumer;
import com.xuecheng.consumer.demo.impl.RabbitMQHeaderConsumer;
import com.xuecheng.consumer.demo.impl.RabbitMQTopicConsumer;
import com.xuecheng.framework.baseConstant.RabbitMQConstant;

/**
 * rabbitmq的不同producer
 *
 * @author 吧嘻小米
 * @date 2020/05/16
 */
public class RabbitMQConsumerType {

    public static RabbitMQConsumer getRabbitMQConsumer(String type) {
        switch (type) {
            case RabbitMQConstant
                    .DEFAULT_TYPE:
                return new RabbitMQDefaultConsumer();
            case RabbitMQConstant
                    .FANOUT_TYPE:
                return new RabbitMQFanoutConsumer();
            case RabbitMQConstant
                    .TOPIC_TYPE:
                return new RabbitMQTopicConsumer();
            case RabbitMQConstant.HEADER_TYPE:
                return new RabbitMQHeaderConsumer();
            default:
                return new RabbitMQDefaultConsumer();
        }
    }
}
