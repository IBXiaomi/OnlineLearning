package com.xuecheng.manage_cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * cms页面管理启动类
 *
 * @author 吧嘻小米
 * @date 2020/04/19
 */
@SpringBootApplication
@EntityScan(" com.xuecheng.framework.domain.cms")
@ComponentScan(basePackages = {"com.xuecheng.api"})
@ComponentScan(basePackages = {"com.xuecheng.manage_cms"})
@ComponentScan(basePackages = {"com.xuecheng.framework"})
public class MyApplicationCmsManage {
    public static void main(String[] args) {
        SpringApplication.run(MyApplicationCmsManage.class, args);
    }
}
