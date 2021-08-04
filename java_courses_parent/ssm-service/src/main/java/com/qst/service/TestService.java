package com.qst.service;

import com.qst.domain.Test;

import java.util.List;

public interface TestService {

    /*
    * 对test表进行查询所有
    * */

    public List<Test> findAllTest();
}
