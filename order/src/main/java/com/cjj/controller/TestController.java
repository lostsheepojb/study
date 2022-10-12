package com.cjj.controller;

import com.cjj.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author cjj
 * @date 2022/9/27 9:31
 * @description
 **/
@RestController
@RequestMapping("/hello")
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public String test(@RequestParam("name") String name, HttpServletRequest request, HttpServletResponse response) {
        log.info("网关路由过滤器测试（请求头添加）：route-id=" + request.getHeader("route-id"));
        log.info("网关默认过滤器测试（请求头添加）：my-header=" + request.getHeader("my-header"));
        return "hello " + name;
    }

    @GetMapping("/testDatasource")
    public String testDatasource() {
        log.info("testDatasource");
        return testService.testDatasource();
    }
}