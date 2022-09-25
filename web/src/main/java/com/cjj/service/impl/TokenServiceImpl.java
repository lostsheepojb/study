package com.cjj.service.impl;

import com.cjj.service.TokenService;
import com.cjj.utils.RedisUtil;
import com.cjj.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String generalRandomRequestToken() {
        String requestToken = TokenUtil.generalRandomRequestToken();
        redisUtil.incr(requestToken,0L);
        return requestToken;
    }
}
