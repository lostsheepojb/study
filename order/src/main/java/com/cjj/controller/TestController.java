package com.cjj.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cjj
 * @date 2022/9/27 9:31
 * @description
 **/
@RestController
@RequestMapping("/hello")
@Slf4j
public class TestController {

    @GetMapping("/test")
    public String test(@RequestParam("name") String name) {
        log.info("invoked name = " + name);
        return "hello " + name;
    }
}