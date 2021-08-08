package com.qst.controller;

import com.github.pagehelper.PageInfo;
import com.qst.domain.Menu;
import com.qst.domain.MenuVo;
import com.qst.domain.ResponseResult;
import com.qst.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /*
    * 查询所有菜单列表   get请求可以不用@RequestBody把json格式转对象
    * */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(MenuVo menuVo){
        PageInfo<Menu> pageInfo = menuService.findAllMenu(menuVo);
        ResponseResult result = new ResponseResult(true,200,"查询所有菜单列表成功",pageInfo);
        return result;

    }
    /*
    * 根据id值进行判断（判断id值是否为-1）
    * 如果为-1，查询菜单信息（只需要父级菜单信息，之前编写过返回父子级菜单信息，前台可以根据需要选择数据）
    * 如果不为-1，查询父级菜单信息及根据当前id查询具体的菜单信息
    * */

    /*
    * 回显菜单信息
    * */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){
        //根据id的值判断当前是更新还是添加操作，判断id的值是否为-1
        if(id == -1){
            //添加  回显信息中不需要查询menu信息
            //前台根据需要选择数据
            List<Menu> parentMenu = menuService.findSubMenuListByPid(-1);

            //封装数据
            Map<String,Object> map = new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",parentMenu);

            ResponseResult result = new ResponseResult(true,200,"添加回显成功",map);
            return result;
        }else{
            //修改操作  回显所有menu信息
            Menu menu = menuService.findMenuById(id);
            //添加  回显信息中不需要查询menu信息
            //前台根据需要选择数据
            List<Menu> parentMenu = menuService.findSubMenuListByPid(-1);

            //封装数据
            Map<String,Object> map = new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList",parentMenu);

            ResponseResult result = new ResponseResult(true,200,"修改回显成功",map);
            return result;
        }
    }

    /*
    * 添加或修改菜单 post
    * */
    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu(@RequestBody Menu menu){

        System.out.println(menu.getLevel());
        if(menu.getId() == null){
            menuService.saveMenu(menu);
            return new ResponseResult(true,200,"添加菜单成功",null);
        }else{
            menuService.updateMenu(menu);
            return new ResponseResult(true,200,"修改菜单成功",null);
        }


    }

    /*
    * 删除菜单
    * */
    @RequestMapping("/deleteMenu")
    public ResponseResult deleteMenu(int id){
        menuService.deleteMenu(id);
        return new ResponseResult(true,200,"删除菜单成功",null);
    }
}
