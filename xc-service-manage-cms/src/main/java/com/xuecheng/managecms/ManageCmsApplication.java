package com.xuecheng.managecms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * cms页面管理启动类
 *
 * @author wjw
 * @since JDK1.9
 */
@SpringBootApplication
@EntityScan("com.xuecheng.framework.domain.cms")
@ComponentScan({"com.xuecheng.api","com.xuecheng.managecms"})
public class ManageCmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageCmsApplication.class, args);
    }
}
