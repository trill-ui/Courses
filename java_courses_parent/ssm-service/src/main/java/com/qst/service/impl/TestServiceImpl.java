package com.qst.service.impl;

import com.qst.dao.TestMapper;
import com.qst.domain.Test;
import com.qst.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper testMapper;
    @Override
    public List<Test> findAllTest() {
        List<Test> list = testMapper.findAllTest();
        return list;
    }
}
