package com.cjj.controller;

import com.cjj.beans.BaseResponse;
import com.cjj.beans.enums.ResponseEnum;
import com.cjj.controller.model.request.HelloArgument;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
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
        BaseResponse response = new BaseResponse(ResponseEnum.OK.getCode(),argument.getName());
        return response;
    }
}
