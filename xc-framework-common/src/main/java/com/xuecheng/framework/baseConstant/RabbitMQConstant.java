package com.xuecheng.framework.baseConstant;

/**
 * RabbitMQ相关的常量
 *
 * @author 吧嘻小米
 * @date 2020/05/16
 */
public interface RabbitMQConstant extends Constant {
    /**
     * MQ的地址
     */
    String RABBITMQ_ADDRESS = "192.168.200.131";

    /**
     * MQ的端口
     */
    int PORT = 5672;

    /**
     * MQ的登陆用户名
     */
    String USERNAME = "wjw";

    /**
     * MQ的登陆密码
     */
    String PASSWORD = "wjw";

    /**
     * 默认模式
     */
    String DEFAULT_TYPE = "";

    /**
     * 发布订阅模式
     */
    String FANOUT_TYPE = "fanout";

    /**
     * topic模式
     */
    String TOPIC_TYPE = "topic";
}
