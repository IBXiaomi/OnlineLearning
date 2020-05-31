package com.xuecheng.course.dao;

import com.xuecheng.framework.domain.course.CourseBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * mybatis相关测试
 *
 * @author 吧嘻小米
 * @date 2020/05/31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCourseMapperDao {
    @Autowired
    CourseMapper courseMapper;

    @Test
    public void testFindById() {
        CourseBase courseBase = courseMapper.findCourseBaseById("402885816240d276016240f7e5000002");

    }
}
