package com.cjj.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author cjj
 * @date 2022/9/27 10:55
 * @description
 **/
@FeignClient(value = "order-service", path = "/order/hello")
public interface TestFeignService {

    @GetMapping("/test")
    String test(@RequestParam("name") String test);

}
