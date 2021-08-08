package com.qst.service;

import com.qst.domain.Course;
import com.qst.domain.CourseLesson;
import com.qst.domain.CourseSection;

import java.util.List;

public interface CourseContentService {
    /*
    * 根据课程id查询对应的课程内容（章节+课时）
    * */
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    /*
     * 回显章节对应的课程信息
     * */
    public Course findCourseByCourseId(int courseId);
    /*
     *
     * 新增章节信息
     * */
    public void saveSection(CourseSection courseSection);

    /*
    * 更新章节信息
    * */
    public void updateSection(CourseSection courseSection);

    /*
     * 修改章节状态
     * */
    public void updateSectionStatus(int id,int status);

    /*
    * 新增课时信息
    * */
    public void saveLesson(CourseLesson courseLesson);
    /*
     * 更新课时信息
     * */
    public void updateLesson(CourseLesson courseLesson);
}
