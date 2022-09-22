package com.cjj.utils;

import com.cjj.beans.BaseResponse;

/**
 * @Description
 * @Author cjj
 * @Date 2022/9/22 16:42
 **/
public class ResponseUtils {

    public BaseResponse ok() {
        BaseResponse response = new BaseResponse();
        response.setCode("200");
        response.setMsg("OK");
        return response;
    }
}
