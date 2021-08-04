package com.qst.service;

import com.qst.domain.Menu;

import java.util.List;

public interface MenuService {


    /*
     *
     * 查询所有的父子级菜单信息
     * */
    public List<Menu> findSubMenuListByPid(int pid);

    /*
     * 查询所有菜单列表
     * */

    public List<Menu> findAllMenu();

    public Menu findMenuById(Integer id);
}
