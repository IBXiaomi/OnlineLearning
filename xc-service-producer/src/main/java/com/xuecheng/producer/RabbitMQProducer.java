package com.xuecheng.producer;

/**
 * 构造不同的producer
 *
 * @author 吧嘻小米
 * @date 2020/05/16
 */
public interface RabbitMQProducer {

    void createProducer(String type);

}
