package com.qst.dao;

import com.qst.domain.Menu;

import java.util.List;

public interface MenuMapper {

    /*
    *
    * 查询所有的父子级菜单信息
    * */
    public List<Menu> findSubMenuListByPid(int pid);

    /*
    * 查询所有菜单列表
    * */

    public List<Menu> findAllMenu();

    /*
    * 根据id查询菜单信息
    * */
    public Menu findMenuById(Integer id);
    /*
    * 添加菜单
    * */
    public void saveMenu(Menu menu);

    /*
    * 修改菜单
    * */
    public void updateMenu(Menu menu);

    /*
    * 删除菜单
    * */
    public void deleteMenu(int id);
}
