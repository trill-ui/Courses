package com.qst.dao;

import com.qst.domain.Resource;
import com.qst.domain.ResourceVO;

import java.util.List;

public interface ResourceMapper {
    /*
    * 资源分页&多条件查询
    * */

    public List<Resource> findAllResourceByPage(ResourceVO resourceVO);
}
