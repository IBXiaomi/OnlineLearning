package com.xuecheng.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * springboot集成rabbitmq
 *
 * @author 吧嘻小米
 * @date 2020/05/16
 */
@SpringBootApplication
@EntityScan(" com.xuecheng.framework.domain.cms")
@ComponentScan(basePackages = {"com.xuecheng.framework"})
@ComponentScan(basePackages = {"com.xuecheng.consumer"})
public class RabbitMqConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMqConsumerApplication.class, args);
    }
}
