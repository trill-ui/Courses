package com.qst.service;

import com.qst.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryService {

    /*
     * 查询所有资源分类
     * */
    public List<ResourceCategory> findAllResourceCategory();
}
