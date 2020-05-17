package com.xuecheng.producre.RabbitMQProducer;

import com.xuecheng.framework.baseConstant.RabbitMQConstant;
import com.xuecheng.producer.RabbitMQProducerType;

public class RabbitMQProducerTest {

    public static void main(String[] args) {
        RabbitMQProducerType.getRabbitMQProducer(RabbitMQConstant.HEADER_TYPE).createProducer(RabbitMQConstant.HEADER_TYPE);
    }
}
