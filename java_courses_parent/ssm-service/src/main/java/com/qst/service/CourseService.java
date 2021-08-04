package com.qst.service;

import com.qst.domain.Course;
import com.qst.domain.CourseVO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {
    /*
    *
    * 多条件课程列表查询
    * */

    public List<Course> findCourseByCondition(CourseVO courseVO);

    /*
    * 添加课程和讲师信息
    * */
    public void savaCourseOrTeacher(CourseVO courseVO);

    /*
     * 回显课程信息（根据ID查询对应的课程信息及关联的讲师信息）
     * */
    public CourseVO findCourseById(Integer id);

    /*
    * 修改课程和讲师信息
    * */
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    /*
    * 课程状态管理修改
    * */
    public void updateCourseStatus(int courseId,int status);
}
