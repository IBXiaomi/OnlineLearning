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

    /**
     * header模式
     */
    String HEADER_TYPE = "headers";

    /**
     * TOPIC模式常量
     */
    interface TopicConstant {

        String MESSAGE = "hello-baxixiaomi";

        String TOPIC_EXCHANGE_NAME = "TOPIC_LOG";

        String TOPIC_QUEUE_EMAIL = "TOPIC_QUEUE_EMAIL";

        String TOPIC_QUEUE_SMS = "TOPIC_QUEUE_SMS";

        String CMS_PAGE_TOPIC_QUEUE = "CMS_PAGE_QUEUE";

        String CMS_PAGE_TOPIC_EXCHANGE = "CMS_PAGE_EXCHANGE";
    }

    /**
     * direct模式
     */
    interface directConstant {

        String CMS_PAGE_DIRECT_QUEUE = "CMS_PAGE_QUEUE";

        String CMS_PAGE_DIRECT_EXCHANGE = "CMS_PAGE_EXCHANGE";
    }
}
