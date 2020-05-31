package com.xuecheng.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * 课程管理
 *
 * @author 吧嘻小米
 * @date 2020/05/31
 */
@SpringBootApplication
@EntityScan("com.xuecheng.framework.domain.course")
@ComponentScan(basePackages = {"com.xuecheng.api"})
@ComponentScan(basePackages = {"com.xuecheng.course"})
@ComponentScan(basePackages = {"com.xuecheng.framework"})
public class CourseApplication {
    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class);
    }
}
