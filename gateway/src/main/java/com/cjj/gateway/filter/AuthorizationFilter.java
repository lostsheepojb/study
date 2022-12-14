package com.cjj.gateway.filter;

import com.cjj.beans.constant.CloudConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

/**
 * @author cjj
 * @date 2022/9/29 10:20
 * @description 全局过滤器，鉴权过滤
 **/
@Order(-1)  // 多个过滤器，这个值越小，优先级越高
@Component
@Slf4j
public class AuthorizationFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1、获取请求头
        ServerHttpRequest request = exchange.getRequest();
        // 在请求头添加网关token
        Consumer<HttpHeaders> httpHeaders = httpHeader -> {
            httpHeader.set(CloudConstant.GATEWAY_TOKEN_KEY, CloudConstant.GATEWAY_TOKEN_VALUE);
        };
        ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().headers(httpHeaders).build();
        exchange.mutate().request(serverHttpRequest).build();

        MultiValueMap<String, String> params = request.getQueryParams();
        // 2、获取参数中的authorization 参数
        String auth = params.getFirst("authorization");
        // 3、判断参数值是否等于admin
        if ("admin".equals(auth)) {
            // 4、是，放行
            return chain.filter(exchange);
        } // 5、否，拦截
        // 设置状态码
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
