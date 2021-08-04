package com.qst.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qst.dao.ResourceMapper;
import com.qst.domain.Resource;
import com.qst.domain.ResourceVO;
import com.qst.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;
    @Override
    public  PageInfo<Resource> findAllResourceByPage(ResourceVO resourceVO) {
        //分页查询
        PageHelper.startPage(resourceVO.getCurrentPage(),resourceVO.getPageSize());
        List<Resource> resourceList = resourceMapper.findAllResourceByPage(resourceVO);

        PageInfo<Resource> pageInfo = new PageInfo<>(resourceList);

        return pageInfo;
    }
}
