package com.cjj.controller;

import com.cjj.annotation.RepeatRequest;
import com.cjj.beans.BaseResponse;
import com.cjj.beans.TokenArgument;
import com.cjj.controller.model.request.HelloArgument;
import com.cjj.service.TestFeignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Description
 * @Author cjj
 * @Date 2022/9/22 11:09
 **/
@RestController
@Api(tags = "你好，世界")
@Slf4j
public class HelloController {

    @Autowired
    private TestFeignService testFeignService;

    @GetMapping("/hello")
    @ApiOperation("hello")
    public BaseResponse hello(@Valid HelloArgument argument, HttpServletRequest request, HttpServletResponse response) {
        log.info("网关路由过滤器测试（请求头添加）：route-id=" + request.getHeader("route-id"));
        log.info("网关默认过滤器测试（请求头添加）：my-header=" + request.getHeader("my-header"));
        BaseResponse baseResponse = new BaseResponse(argument.getName());
        return baseResponse;
    }

    @GetMapping("/feign/test")
    @ApiOperation("测试feign")
    public BaseResponse feignTest(@Valid HelloArgument argument) {
        String test = testFeignService.test(argument.getName());
        BaseResponse response = new BaseResponse(test);
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
