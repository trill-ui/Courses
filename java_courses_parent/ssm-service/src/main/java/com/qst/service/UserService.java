package com.qst.service;

import com.github.pagehelper.PageInfo;
import com.qst.domain.ResponseResult;
import com.qst.domain.Role;
import com.qst.domain.User;
import com.qst.domain.UserVO;

import java.util.List;

public interface UserService {

    /*
     * 用户分页&多条件组合查询
     * */
    public PageInfo<User> findAllUserByPage(UserVO userVo);

    /*
     * 更改用户状态
     * */

    public void updateUserStatus(Integer id,String status);

    /*
     * 用户登录（根据用户名查询具体的用户信息）
     * */
    public User login(User user) throws Exception;

    /*
     * 根据用户id查询关联的角色信息(分配角色  回显)
     * */
    public List<Role> findUserRelationRoleById(Integer id);

    /*
    * 用户关联角色
    *
    * */
    public void userContextRole(UserVO userVO);
    /*
    * 获取用户权限，进行菜单动态展示
    * */
    public ResponseResult getUserPermissions(Integer id);
}
