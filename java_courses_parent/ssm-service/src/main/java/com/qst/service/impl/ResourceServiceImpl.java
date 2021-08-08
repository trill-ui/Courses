package com.qst.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qst.dao.ResourceMapper;
import com.qst.domain.Resource;
import com.qst.domain.ResourceVO;
import com.qst.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public void saveResource(Resource resource) {
        //补全信息
        Date date = new Date();
        resource.setCreatedTime(date);
        resource.setUpdatedTime(date);
        resource.setCreatedBy("system");
        resource.setUpdatedBy("system");
        resourceMapper.saveResource(resource);
    }

    @Override
    public void updateResource(Resource resource) {
        resource.setUpdatedTime(new Date());
        resourceMapper.updateResource(resource);
    }

    @Override
    public void deleteResource(int id) {
        resourceMapper.deleteResource(id);
    }
}
