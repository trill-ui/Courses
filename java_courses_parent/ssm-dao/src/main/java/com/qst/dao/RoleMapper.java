package com.qst.dao;

import com.qst.domain.Role;
import com.qst.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {

    /*
    * 查询所有角色&条件进行查询
    * */

    public List<Role> findAllRole(Role role);

    /*
    * 根据角色id查询该角色关联的菜单信息id（1，2，3，4）
    *
    *
    * */
    public List<Integer> findMenuByRoleId(Integer roleId);

    /*
    *为角色roleid分配菜单信息：
    * 步骤1：根据roleid清空原来的中间表的关联关系
    * */
    public void deleteRoleContextMenu(Integer rid);

    /*
    * 步骤2：为角色分配菜单信息
    * */

    public void roleContextMenu(Role_menu_relation role_menu_relation);

    /*
    * 删除角色
    * */
    public void deleteRole(Integer id);
}
