package com.qst.controller;

import com.github.pagehelper.PageInfo;
import com.qst.domain.Resource;
import com.qst.domain.ResourceVO;
import com.qst.domain.ResponseResult;
import com.qst.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
* 添加&更新资源信息  删除资源
* */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/findAllResource")
    public ResponseResult findAllResourceByPage(@RequestBody ResourceVO resourceVO){

        PageInfo<Resource> pageInfo = resourceService.findAllResourceByPage(resourceVO);

        ResponseResult result = new ResponseResult(true,200,"多条件查询和分页成功",pageInfo);
        return result;
    }
}
