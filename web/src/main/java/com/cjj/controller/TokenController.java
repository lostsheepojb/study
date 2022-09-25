package com.cjj.controller;

import com.cjj.beans.BaseResponse;
import com.cjj.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @author cjj
 * @date 2022/9/22 11:09
 **/
@RestController
@Api(tags = "token")
public class TokenController {

    @Autowired
    TokenService tokenService;

    @GetMapping("/getRequestToken")
    @ApiOperation("获取请求token")
    public BaseResponse getRequestToken() {
        //String token = TokenUtil.generalRandomRequestToken();
        String token = tokenService.generalRandomRequestToken();
        return new BaseResponse(token);
    }
}
