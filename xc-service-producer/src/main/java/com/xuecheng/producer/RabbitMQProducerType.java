package com.xuecheng.producer;

import com.xuecheng.framework.baseConstant.RabbitMQConstant;
import com.xuecheng.producer.demo.impl.RabbitMQDefaultProducer;
import com.xuecheng.producer.demo.impl.RabbitMQFanoutProducer;
import com.xuecheng.producer.demo.impl.RabbitMQHeaderProducer;
import com.xuecheng.producer.demo.impl.RabbitMQTypicProducer;

/**
 * rabbitmq的不同producer
 *
 * @author 吧嘻小米
 * @date 2020/05/16
 */
public class RabbitMQProducerType {

    public static RabbitMQProducer getRabbitMQProducer(String type) {
        switch (type) {
            case RabbitMQConstant
                    .DEFAULT_TYPE:
                return new RabbitMQDefaultProducer();
            case RabbitMQConstant
                    .FANOUT_TYPE:
                return new RabbitMQFanoutProducer();
            case RabbitMQConstant
                    .TOPIC_TYPE:
                return new RabbitMQTypicProducer();
            case RabbitMQConstant.HEADER_TYPE:
                return new RabbitMQHeaderProducer();
            default:
                return new RabbitMQDefaultProducer();
        }
    }
}
