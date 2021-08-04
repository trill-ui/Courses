package com.qst.controller;

import com.qst.domain.Course;
import com.qst.domain.CourseVO;
import com.qst.domain.ResponseResult;
import com.qst.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO){
        //调用service
        List<Course> list = courseService.findCourseByCondition(courseVO);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", list);
        return responseResult;

    }

    /*
    * 课程图片上传
    * */
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request)  {
        try {
            //1.判断接收到的上传文件是否为空
            if(file.isEmpty()){
                throw new RuntimeException();
            }

            //2.获取项目部署路径
            //1.获取项目的运行目录 D:\eclipse02\apache-tomcat-9.0.22\webapps\ssm-web\
            String realPath = request.getServletContext().getRealPath("/");
            System.out.println("realPath="+realPath);

            //使用tomcat作为图片服务器，当然是有专门的图片服务器
            //截取到 webapps目录路径  D:\eclipse02\apache-tomcat-9.0.22\webapps
            String subString = realPath.substring(0,realPath.indexOf("ssm-web"));

            //3.获取原文件名   //zjh.jpg
            String originalFilename = file.getOriginalFilename();

            //4.生成新文件名  //24324243.jpg
            String newFileName = System.currentTimeMillis()+originalFilename.substring(originalFilename.lastIndexOf("."));


            //5.文件上传 D:\\eclipse02\\apache-tomcat-9.0.22\\webapps\\upload
            String uploadPath = subString+"upload\\";
            File filePath = new File(uploadPath,newFileName);

            //如果目录不存在就创建目录
            if(!filePath.getParentFile().exists()){
                filePath.getParentFile().mkdirs();
                System.out.println("创建目录"+filePath);
            }

            //图片就进行了真正的上传
            file.transferTo(filePath);

            //6.将文件名和文件路径返回，进行响应，以便回显
            Map<String,String> map  = new HashMap<>();
            map.put("fileName",newFileName);
            //响应数据："filePath":"http://localhost:8080/upload/24324243.jpg"
            map.put("filePath","http://localhost:8080/upload/"+newFileName);

            //响应数据返回
            ResponseResult responseResult = new ResponseResult(true,200,"图片上传成功",map);

            return responseResult;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    * 新增课程信息及讲师信息
    * 新增课程信息和修改信息要写在同一个方法中  页面是一样的
    * */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {

        if(courseVO.getId() == null){
            //调用service
            courseService.savaCourseOrTeacher(courseVO);

            ResponseResult result = new ResponseResult(true,200,"新增成功",null);

            return result;
        }else{
            courseService.updateCourseOrTeacher(courseVO);
            ResponseResult result = new ResponseResult(true,200,"修改成功",null);

            return result;
        }

    }

    /*
     * 回显课程信息（根据ID查询对应的课程信息及关联的讲师信息）
     * */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){
        CourseVO courseVO = courseService.findCourseById(id);
        ResponseResult result = new ResponseResult(true,200,"根据ID查询课程信息成功",courseVO);
        return result;
    }

    /*
    * 课程状态更改
    * */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(Integer id,Integer status){

        //调用service层，传递参数，完成课程状态的变更
        courseService.updateCourseStatus(id,status);

        //响应数据
        Map<String,Object> map = new HashMap<>();
        map.put("status",status);

        ResponseResult result = new ResponseResult(true,200,"课程状态变更成功",map);
        return result;
    }
}
