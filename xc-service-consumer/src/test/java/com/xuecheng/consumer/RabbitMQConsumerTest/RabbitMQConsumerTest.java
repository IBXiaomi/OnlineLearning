package com.xuecheng.consumer.RabbitMQConsumerTest;

import com.xuecheng.RabbitMQConsumerType;
import com.xuecheng.framework.baseConstant.RabbitMQConstant;

public class RabbitMQConsumerTest {
    public static void main(String[] args) {
        RabbitMQConsumerType.getRabbitMQConsumer(RabbitMQConstant.DEFAULT_TYPE).createConsumer(RabbitMQConstant.DEFAULT_TYPE);
    }
}
