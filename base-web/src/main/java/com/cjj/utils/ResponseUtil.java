package com.cjj.utils;

import com.cjj.beans.BaseResponse;

/**
 * @description
 * @author cjj
 * @date 2022/9/22 16:42
 **/
public class ResponseUtil {

    public BaseResponse ok() {
        BaseResponse response = new BaseResponse();
        response.setCode("200");
        response.setMsg("OK");
        return response;
    }
}
