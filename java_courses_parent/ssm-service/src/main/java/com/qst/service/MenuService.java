package com.qst.service;

import com.github.pagehelper.PageInfo;
import com.qst.domain.Menu;
import com.qst.domain.MenuVo;

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

    public PageInfo<Menu> findAllMenu(MenuVo menuVo);

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
