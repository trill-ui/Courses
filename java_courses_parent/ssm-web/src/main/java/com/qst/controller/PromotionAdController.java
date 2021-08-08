package com.qst.controller;

import com.github.pagehelper.PageInfo;
import com.qst.domain.PromotionAd;
import com.qst.domain.PromotionAdVO;
import com.qst.domain.ResponseResult;
import com.qst.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;
    public static final String LOCAL_URL  = "http://localhost:8080";

    /*
    * 广告分页查询
    * */
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVO promotionAdVO){
        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllPromotionAdByPage(promotionAdVO);

        ResponseResult result = new ResponseResult(true,200,"分页查询成功",pageInfo);
        return result;
    }


    /*
        图片上传
     */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

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
    * 新增&更新广告信息
    * */

    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){
        if(promotionAd.getId()==null){
            promotionAdService.savePromotionAd(promotionAd);
            ResponseResult result = new ResponseResult(true,200,"新增广告信息成功",null);
            return result;
        }else{
            promotionAdService.updatePromotionAd(promotionAd);
            ResponseResult result = new ResponseResult(true,200,"修改广告信息成功",null);
            return result;
        }

    }

    /*
    * 根据id查询广告信息
    * */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(int id){
        PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);

        ResponseResult result = new ResponseResult(true,200,"查询具体广告信息",promotionAd);
        return result;

    }

    /*
     * 广告动态上下线
     * */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(Integer id,Integer status){
        promotionAdService.updatePromotionAdStatus(id,status);
        ResponseResult result = new ResponseResult(true,200,"广告动态上下线成功",null);
        return result;
    }
}
