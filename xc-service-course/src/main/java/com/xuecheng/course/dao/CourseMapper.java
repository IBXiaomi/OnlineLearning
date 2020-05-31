package com.xuecheng.course.dao;

import com.xuecheng.framework.domain.course.CourseBase;
import org.apache.ibatis.annotations.Mapper;

/**
 * mybatis的接口
 *
 * @author 吧嘻小米
 * @date 2020/05/31
 */
@Mapper
public interface CourseMapper {
    CourseBase findCourseBaseById(String id);
}
