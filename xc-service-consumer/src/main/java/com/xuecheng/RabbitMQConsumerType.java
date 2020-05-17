package com.xuecheng;

import com.xuecheng.consumer.RabbitMQConsumer;
import com.xuecheng.consumer.impl.RabbitMQDefaultConsumer;
import com.xuecheng.consumer.impl.RabbitMQFanoutConsumer;
import com.xuecheng.consumer.impl.RabbitMQTopicConsumer;
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
            default:
                return new RabbitMQDefaultConsumer();
        }
    }
}
