package com.xuecheng.consumer.demo;

/**
 * 构造不同的consumer
 *
 * @author 吧嘻小米
 * @date 2020/05/16
 */
public interface RabbitMQConsumer {

    void createConsumer(String type);

}
