package com.qst.controller;

import com.qst.domain.Course;
import com.qst.domain.CourseLesson;
import com.qst.domain.CourseSection;
import com.qst.domain.ResponseResult;
import com.qst.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(Integer courseId){
        //调用service层
        List<CourseSection> sectionList = courseContentService.findSectionAndLessonByCourseId(courseId);

        ResponseResult result = new ResponseResult(true,200,"响应成功",sectionList);
        return result;
    }

    /*
    * 回显章节对应的课程信息
    * */
    @RequestMapping("findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){
        Course course = courseContentService.findCourseByCourseId(courseId);

        ResponseResult result = new ResponseResult(true,200,"查询课程信息成功",course);

        return result;
    }
    /*
    * 新增章节信息
    * 前台进行回显操作
    * 修改章节信息
    * */

    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        //判断是否携带了章节ID
        if(courseSection.getId() == null){
            //新增
            courseContentService.saveSection(courseSection);
            ResponseResult result = new ResponseResult(true,200,"新增章节成功",null);
            return result;
        }else{
            //修改
            courseContentService.updateSection(courseSection);
            ResponseResult result = new ResponseResult(true,200,"修改章节成功",null);
            return result;
        }


    }

    /*
    * 修改章节状态
    * */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int id,int status){
        courseContentService.updateSectionStatus(id,status);

        //数据响应
        Map<String,Object> map = new HashMap<>();
        map.put("status",status);

        ResponseResult result = new ResponseResult(true,200,"修改章节状态成功",map);
        return result;
    }

    /*
    * 新增课时信息
    * */
    @RequestMapping("/saveLesson")
    public ResponseResult saveLesson(@RequestBody CourseLesson courseLesson){

        courseContentService.saveLesson(courseLesson);

        ResponseResult result = new ResponseResult(true,200,"新增课时信息成功",null);
        return result;
    }
}
