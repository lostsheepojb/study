package com.cjj.controller;

import com.cjj.annotation.RepeatRequest;
import com.cjj.beans.BaseResponse;
import com.cjj.beans.TokenArgument;
import com.cjj.controller.model.request.HelloArgument;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    public BaseResponse hello(@Valid HelloArgument argument) {
        BaseResponse response = new BaseResponse(argument.getName());
        return response;
    }

    @PostMapping("/fromPost")
    @ApiOperation("表单不可重复提交")
    @RepeatRequest
    public BaseResponse fromPost(@Valid TokenArgument argument) {
        BaseResponse response = new BaseResponse(argument.getToken());
        return response;
    }

}
