package com.qst.dao;

import com.qst.domain.Role;
import com.qst.domain.Test;

import java.util.List;

public interface TestMapper {

    /*
    * 对test表进行查询所有操作
    * */

    public List<Test> findAllTest();

   // public List<Integer> findAllById(List<Role> roleid);
}
