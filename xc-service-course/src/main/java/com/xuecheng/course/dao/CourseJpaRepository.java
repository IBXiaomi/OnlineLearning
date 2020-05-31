package com.xuecheng.course.dao;

import com.xuecheng.framework.domain.course.CourseBase;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * jpa相关操作接口
 *
 * @author 吧嘻小米
 * @date 2020/05/31
 */
public interface CourseJpaRepository extends JpaRepository<CourseBase, String> {
}
