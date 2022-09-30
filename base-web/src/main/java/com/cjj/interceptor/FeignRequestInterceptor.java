package com.cjj.interceptor;

import com.cjj.beans.constant.CloudConstant;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

/**
 * @author cjj
 * @date 2022/9/30 16:26
 * @description feign拦截器，添加请求头
 **/
@Component
public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        template.header(CloudConstant.GATEWAY_TOKEN_KEY, CloudConstant.GATEWAY_TOKEN_VALUE);
    }

}