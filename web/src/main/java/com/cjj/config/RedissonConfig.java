package com.cjj.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author cjj
 * @Date 2022/9/24 14:19
 **/
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient getRedisson(){
        Config config = new Config();
        //单机模式
        config.useSingleServer().
                setAddress("redis://127.0.0.1:6379").
                setPassword("redis2022");
        config.setCodec(new StringCodec());
        return Redisson.create(config);
    }
}