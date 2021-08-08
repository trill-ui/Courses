package com.qst.service;

import com.github.pagehelper.PageInfo;
import com.qst.domain.Resource;
import com.qst.domain.ResourceVO;

import java.util.List;

public interface ResourceService {
    /*
     * 资源分页&多条件查询
     * */

    public PageInfo<Resource> findAllResourceByPage(ResourceVO resourceVO);

    /*
     * 添加资源
     * */
    public void saveResource(Resource resource);
    /*
     * 更新资源
     * */

    public void updateResource(Resource resource);
    /*
     * 删除资源
     * */
    public void deleteResource(int id);
}
