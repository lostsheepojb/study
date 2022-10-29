package com.cjj.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * @version 1.0
 * @date 2022/10/28 10:18
 * @ClassIntroduction
 */
//ServerEndpointExporter需要依赖tomcat容器启动
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class RedisTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Test
    public void getNearPointTest(){
        String key = "china:city";
        String beijing = "beijing";
        String shanghai = "shanghai";
        String guangzhou = "guangzhou";
        String shenzhen = "shenzhen";

        log.info("---------添加地理信息数据-------");
        redisTemplate.opsForGeo().add(key, new Point(16.408, 39.904), beijing);
        redisTemplate.opsForGeo().add(key, new Point(121.445, 31.213), shanghai);
        redisTemplate.opsForGeo().add(key, new Point(113.265, 23.108), guangzhou);
        redisTemplate.opsForGeo().add(key, new Point(114.109, 22.544), shenzhen);

        log.info("---------获取经纬度-------");
        List<Point> points = redisTemplate.opsForGeo().position(key, beijing);
        log.info("北京的经纬度：{},{}", points.get(0).getX(), points.get(0).getY());

        log.info("---------两地距离-------");
        Distance distance = redisTemplate.opsForGeo().distance(key, beijing, guangzhou);
        log.info("beijing和guangzhou的距离：{} {}", distance.getValue(), distance.getUnit());

        log.info("---------附近的城市-------");
        RedisGeoCommands.GeoRadiusCommandArgs commandArgs = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs();
        commandArgs.limit(10L);
        commandArgs.sortAscending();
        commandArgs.includeDistance();

        GeoResults<RedisGeoCommands.GeoLocation<Object>> locationGeoResults = redisTemplate.opsForGeo().radius(key, new Circle(114.109, 20.544, 8937684.2716), commandArgs);
        List<GeoResult<RedisGeoCommands.GeoLocation<Object>>> content = locationGeoResults.getContent();
        content.stream().forEach(item ->log.info("{}：{}",item.getContent().getName(),item.getDistance()));
    }

}