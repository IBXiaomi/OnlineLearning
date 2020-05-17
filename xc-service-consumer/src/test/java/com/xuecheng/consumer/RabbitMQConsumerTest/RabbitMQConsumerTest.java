package com.xuecheng.consumer.RabbitMQConsumerTest;

import com.xuecheng.RabbitMQConsumerType;
import com.xuecheng.framework.baseConstant.RabbitMQConstant;

/**
 * consumer的测试
 *
 * @author 吧嘻小米
 * @date 2020/05/16
 */
public class RabbitMQConsumerTest {
    public static void main(String[] args) {
        RabbitMQConsumerType.getRabbitMQConsumer(RabbitMQConstant.HEADER_TYPE).createConsumer(RabbitMQConstant.HEADER_TYPE);
    }
}
