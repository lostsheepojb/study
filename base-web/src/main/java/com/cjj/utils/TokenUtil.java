package com.cjj.utils;

import java.util.UUID;

/**
 * @description 把token放到服务器内存中，不可用作分布式和集群部署的项目
 * @author cjj
 * @Date 2022/9/23 15:36
 **/
public class TokenUtil {

    public static String REQUEST_TOKEN_PREFIX = "REQUEST_TOKEN_";

    public static String generalRandomRequestToken() {
        String token = REQUEST_TOKEN_PREFIX + UUID.randomUUID().toString().replace("-", "").substring(15);
        return token;
    }

}
