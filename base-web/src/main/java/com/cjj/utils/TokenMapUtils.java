package com.cjj.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description 把token放到服务器内存中，不可用作分布式和集群部署的项目
 * @Author cjj
 * @Date 2022/9/23 15:36
 **/
public class TokenMapUtils {

    public static String REQUEST_TOKEN_PRIFIXX = "REQUEST_";

    public static Map<String, Integer> requestToken = new HashMap<>();

    public static String generalRandomRequestToken() {
        String token = REQUEST_TOKEN_PRIFIXX + UUID.randomUUID().toString().replace("-", "").substring(20);
        requestToken.put(token, Integer.valueOf(0));
        return token;
    }

}
