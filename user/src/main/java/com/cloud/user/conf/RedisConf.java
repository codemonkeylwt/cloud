package com.cloud.user.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @author lwt
 * @date 2018/7/24 0:06
 */
@Configuration
public class RedisConf {
    @Value("${spring.redis.host}")
    private String host = "106.15.227.14";

    @Value("${spring.redis.port}")
    private int port = 6379;

    @Bean
    public JedisPool getJedisPool() {
        return new JedisPool(host, port);
    }
}
