package com.qst.dao;

import com.qst.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    /*
     * 用户分页&多条件组合查询
     * */
    public List<User> findAllUserByPage(UserVO userVo);

    /*
     * 更改用户状态
     * */

    public void updateUserStatus(User user);

    /*
     * 用户登录（根据用户名查询具体的用户信息）
     * */
    public User login(User user);

    /*
     * 1.根据用户id查询关联的角色信息  多个角色
     * */
    public List<Role> findUserRelationRoleById(Integer id);

    /*
     * 根据用户id清空中间表
     * */
    public void deleteUserContextRole(Integer userId);

    /*
     * 分配角色
     * */
    public void userContextRole(User_Role_relation user_role_relation);

    /*
     * 2.根据角色id，查询角色所拥有的顶级菜单（-1） 多个角色查询
     * */
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);

    /*
     * 3.根据pid 查询子菜单信息
     * */
    public List<Menu> findSubMenuByPid(Integer pid);

    /*
     * 4.获取用户拥有的资源权限信息 根据角色获取
     * */
    public List<Resource> findResourceByRoleId(List<Integer> ids);
}
