package com.cjj.interceptor;

import com.cjj.beans.BaseResponse;
import com.cjj.beans.constant.CloudConstant;
import com.cjj.beans.enums.ResponseCodeEnum;
import com.google.gson.Gson;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author cjj
 * @date 2022/9/30 14:24
 * @description 微服务保护拦截器，禁止直接访问微服接口，通过token拦截
 **/

public class ServiceProtectInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String gatewayToken = request.getHeader(CloudConstant.GATEWAY_TOKEN_KEY);
        if (CloudConstant.GATEWAY_TOKEN_VALUE.equals(gatewayToken)) {
            return true;
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        BaseResponse baseResponse = new BaseResponse(ResponseCodeEnum.ARGUMENT_ERROR.getCode(),"不可绕过网关直接访问服务");
        String msg = new Gson().toJson(baseResponse);
        response.getWriter().write(msg);
        return false;
    }
}
