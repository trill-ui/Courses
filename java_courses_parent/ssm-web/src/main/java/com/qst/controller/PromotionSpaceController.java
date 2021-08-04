package com.qst.controller;

import com.qst.domain.PromotionSpace;
import com.qst.domain.ResponseResult;
import com.qst.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService promotionSpaceService;

    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){
        List<PromotionSpace> promotionSpaceList = promotionSpaceService.findAllPromotionSpace();

        ResponseResult result = new ResponseResult(true,200,"查询所有广告位成功",promotionSpaceList);
        return result;
    }

    /*
    * 添加&更新广告位
    * */
    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult savePromotionSpace(@RequestBody PromotionSpace promotionSpace){
        if(promotionSpace.getId()==null){
            promotionSpaceService.savePromotionSpace(promotionSpace);

            ResponseResult result = new ResponseResult(true,200,"新增广告位成功",null);
            return result;
        } else{
            promotionSpaceService.updatePromotionSpace(promotionSpace);

            ResponseResult result = new ResponseResult(true,200,"更新广告位成功",null);
            return result;
        }


    }

    /*
    * 根据id查询广告位信息(回显操作)
    * */
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(int id){
        PromotionSpace promotionSpace = promotionSpaceService.findPromotionSpaceById(id);
        ResponseResult result  = new ResponseResult(true,200,"查询具体广告位成功",promotionSpace);
        return result;
    }
}
