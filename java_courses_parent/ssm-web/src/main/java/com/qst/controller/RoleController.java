package com.qst.controller;

import com.qst.domain.Menu;
import com.qst.domain.ResponseResult;
import com.qst.domain.Role;
import com.qst.domain.RoleMenuVO;
import com.qst.service.MenuService;
import com.qst.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

/*
* 添加&修改角色
* */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> roles = roleService.findAllRole(role);
        ResponseResult result = new ResponseResult(true,200,"查询所有用户成功",roles);

        return result;
    }

    /*
    * 查询所有的父子级菜单信息（分配菜单的第一个接口）
    * */
    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findSubMenuListByPid(){

        //-1 表示查询所有的父子级菜单
        List<Menu> menuList = menuService.findSubMenuListByPid(-1);

        //响应数据
        Map<String,Object> map = new HashMap<>();
        map.put("parentMenuList",menuList);

        ResponseResult result = new ResponseResult(true,200,"查询所有父子级菜单成功",map);
        return result;

    }


    /*
     * 根据角色id查询该角色关联的菜单信息id（1，2，3，4）
     *
     *
     * */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){

        List<Integer> roleList = roleService.findMenuByRoleId(roleId);
        ResponseResult result = new ResponseResult(true,200,"查询角色关联的菜单信息成功",roleList);
        return result;
    }

    /*
    * 为角色分配菜单
    * */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVO roleMenuVO){
        roleService.RoleContextMenu(roleMenuVO);

        ResponseResult result = new ResponseResult(true,200,"为角色分配菜单成功",null);
        return result;
    }

    /*
    * 删除角色（如果有关联关系，必须先删除关联关系）
    * */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){

        roleService.deleteRole(id);

        ResponseResult result = new ResponseResult(true,200,"删除角色成功",null);
        return result;

    }
}
