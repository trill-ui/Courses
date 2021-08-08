package com.qst.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qst.dao.UserMapper;
import com.qst.domain.*;
import com.qst.service.UserService;
import com.qst.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public PageInfo<User> findAllUserByPage(UserVO userVo) {

        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());
        List<User> allUserByPage = userMapper.findAllUserByPage(userVo);

        PageInfo<User> pageInfo = new PageInfo<>(allUserByPage);
        return pageInfo;
    }

    @Override
    public void updateUserStatus(Integer id, String status) {
        //补全信息
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        user.setUpdateTime(new Date());

        //调用dao层
        userMapper.updateUserStatus(user);
    }

    @Override
    public User login(User user) throws Exception{
        //1.调用mapper方法，包含了密文密码
        User user2 = userMapper.login(user);

        if(user2 !=null && Md5.verify(user.getPassword(),"zjh",user2.getPassword())){
            return user2;
        }else{
            return null;
        }

    }

    @Override
    public List<Role> findUserRelationRoleById(Integer id) {
        return userMapper.findUserRelationRoleById(id);
    }

    @Override
    public void userContextRole(UserVO userVO) {

        //1.根据用户id清空中间表关联关系
        userMapper.deleteUserContextRole(userVO.getUserId());

        //2.再从新建立关联关系
        for(Integer roleId : userVO.getRoleIdList()){

            //封装数据
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVO.getUserId());
            user_role_relation.setRoleId(roleId);

            Date date = new Date();
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedTime(date);
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");

            userMapper.userContextRole(user_role_relation);
        }
    }

    /*
    * 获取用户权限信息
    * */

    @Override
    public ResponseResult getUserPermissions(Integer userId) {
        //1.获取当前用户拥有的角色  里面包含了很多属性，我们只要角色id
        List<Role> roleList = userMapper.findUserRelationRoleById(userId);
        //2.获取角色id，保存到list集合中
        ArrayList<Integer> roleIds = new ArrayList<>();
        for (Role role : roleList) {
            roleIds.add(role.getId());
            System.out.println();
        }

        //3.根据角色id查询父菜单  还需要子菜单   roleIds集合不能为空 深刻教训
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(roleIds);

        //4.查询封装父菜单关联的子菜单

        for (Menu menu : parentMenu) {
            //根据父菜单的id查询子菜单 并保存到Menu实体类中的属性subMenuList中
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenu);
        }


        //5.获取资源信息  根据角色获取
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);

        //6.将这些信息封装并返回
        Map<String,Object> map = new HashMap<>();
        map.put("menuList",parentMenu);
        map.put("resourceList",resourceList);
        return new ResponseResult(true,200,"响应成功",map);
    }


}
