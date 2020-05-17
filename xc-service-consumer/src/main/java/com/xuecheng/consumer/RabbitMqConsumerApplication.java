package com.xuecheng.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot集成rabbitmq
 *
 * @author 吧嘻小米
 * @date 2020/05/16
 */
@SpringBootApplication
public class RabbitMqConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMqConsumerApplication.class, args);
    }
}
