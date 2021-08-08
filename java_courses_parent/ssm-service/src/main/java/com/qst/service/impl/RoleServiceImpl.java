package com.qst.service.impl;

import com.qst.dao.RoleMapper;
import com.qst.domain.Role;
import com.qst.domain.RoleMenuVO;
import com.qst.domain.Role_menu_relation;
import com.qst.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> roleList = roleMapper.findAllRole(role);
        return roleList;
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {
        List<Integer> roleIds = roleMapper.findMenuByRoleId(roleId);
        return roleIds;
    }

    @Override
    public void RoleContextMenu(RoleMenuVO roleMenuVO) {

        //1.清空中间表的关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVO.getRoleId());
        //2.为角色分配菜单 需要遍历
        for(Integer mid : roleMenuVO.getMenuIdList()){
            //封装数据
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleMenuVO.getRoleId());
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");

            //调用dao层
            roleMapper.roleContextMenu(role_menu_relation);
        }
    }

    @Override
    public void deleteRole(Integer roleId) {
        //调用根据roleid清空中间表关联关系
        roleMapper.deleteRoleContextMenu(roleId);

        roleMapper.deleteRole(roleId);
    }

    @Override
    public void saveRole(Role role) {
        //补全信息
        Date date = new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);
        role.setCreatedBy("system");
        role.setUpdatedBy("system");
        roleMapper.saveRole(role);
    }

    @Override
    public void updateRole(Role role) {
        //补全信息
        role.setUpdatedTime(new Date());
        roleMapper.updateRole(role);
    }


}
