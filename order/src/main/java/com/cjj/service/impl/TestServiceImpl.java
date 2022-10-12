package com.cjj.service.impl;

import com.cjj.mapper.TestMapper;
import com.cjj.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public String testDatasource() {
        return testMapper.test();
    }
}
