package com.cjj.controller;

import com.cjj.beans.BaseResponse;
import com.cjj.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @author cjj
 * @Date 2022/9/22 11:09
 **/
@RestController
@Api(tags = "redis测试")
public class RedisController {

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/getValue")
    @ApiOperation(value = "get", notes = "根据key获取redis value")
    public BaseResponse getValue(String key) {
        Object o = redisUtil.get(key);
        return new BaseResponse(o);
    }
}
