package com.xuecheng.course.dao;

import com.xuecheng.framework.domain.course.CourseBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCourseJpaRepository {

    @Autowired
    CourseJpaRepository courseJpaRepository;

    @Test
    public void testCourseJpaRepository() {
        Optional<CourseBase> courseBaseOptional = courseJpaRepository.findById("402885816240d276016240f7e5000002");
        if (courseBaseOptional.isPresent()) {
            System.out.println(courseBaseOptional.get());
        }
    }
}
