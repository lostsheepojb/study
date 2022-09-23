package com.cjj.controller;

import com.cjj.beans.BaseResponse;
import com.cjj.utils.TokenMapUtils;
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
@Api(tags = "token")
public class TokenController {

    @GetMapping("/getRequestToken")
    @ApiOperation("随机获取请求token")
    public BaseResponse getRequestToken() {
        String token = TokenMapUtils.generalRandomRequestToken();
        return new BaseResponse(token);
    }
}
