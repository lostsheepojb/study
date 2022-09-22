package com.cjj.controller;

import com.cjj.beans.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author cjj
 * @Date 2022/9/22 11:09
 **/
@RestController
@Api(tags = "你好，世界")
public class HelloController {

    @GetMapping("/hello")
    @ApiOperation("hello")
    public BaseResponse hello() {
        BaseResponse response = new BaseResponse();
        response.setCode("200");
        response.setMsg("OK");
        return response;
    }
}
