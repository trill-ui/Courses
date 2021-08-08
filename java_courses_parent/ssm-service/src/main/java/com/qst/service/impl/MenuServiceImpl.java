package com.qst.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qst.dao.MenuMapper;
import com.qst.domain.Menu;
import com.qst.domain.MenuVo;
import com.qst.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findSubMenuListByPid(int pid) {
        List<Menu> menuList = menuMapper.findSubMenuListByPid(pid);
        return menuList;
    }

    @Override
    public PageInfo<Menu> findAllMenu(MenuVo menuVo) {
        PageHelper.startPage(menuVo.getCurrentPage(),menuVo.getPageSize());
        List<Menu> allMenu = menuMapper.findAllMenu();
        PageInfo<Menu> pageInfo = new PageInfo<>(allMenu);
        return pageInfo;
    }

    @Override
    public Menu findMenuById(Integer id) {
        Menu menu = menuMapper.findMenuById(id);
        return menu;
    }

    @Override
    public void saveMenu(Menu menu) {
        //补全信息
        Date date = new Date();
        menu.setCreatedTime(date);
        menu.setUpdatedTime(date);
        menu.setUpdatedBy("system");
        menu.setCreatedBy("system");

        menuMapper.saveMenu(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        menu.setUpdatedTime(new Date());
        menuMapper.updateMenu(menu);
    }

    @Override
    public void deleteMenu(int id) {
        menuMapper.deleteMenu(id);
    }
}
