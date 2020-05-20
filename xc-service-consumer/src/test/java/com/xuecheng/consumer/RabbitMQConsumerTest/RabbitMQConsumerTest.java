package com.xuecheng.consumer.RabbitMQConsumerTest;

import com.xuecheng.consumer.demo.RabbitMQConsumerType;
import com.xuecheng.framework.baseConstant.RabbitMQConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * consumer的测试
 *
 * @author 吧嘻小米
 * @date 2020/05/16
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitMQConsumerTest {

    /**
     * 测试mqheader模式
     */
    @Test
    public void testRabbitmq() {
        RabbitMQConsumerType.getRabbitMQConsumer(RabbitMQConstant.HEADER_TYPE).createConsumer(RabbitMQConstant.HEADER_TYPE);
    }


}
