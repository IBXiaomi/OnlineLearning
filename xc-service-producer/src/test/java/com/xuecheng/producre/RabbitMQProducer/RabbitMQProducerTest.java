package com.xuecheng.producre.RabbitMQProducer;

import com.xuecheng.framework.baseConstant.RabbitMQConstant;
import com.xuecheng.producer.RabbitMQProducerType;

public class RabbitMQProducerTest {


    public static void main(String[] args) {
        RabbitMQProducerType.getRabbitMQProducer(RabbitMQConstant.DEFAULT_TYPE).createProducer(RabbitMQConstant.DEFAULT_TYPE);
    }
}
