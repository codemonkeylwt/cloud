package com.cloud.user.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author lwt
 * @date 2018/7/23 17:05
 */
@Component
@Scope(scopeName = "singleton")
public class RedisUtil {

    private final JedisPool jedisPool;

    @Autowired
    public RedisUtil(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    public void set(String key, Object value) {
        Jedis jedis = getJedis();
        jedis.set(key, JSONObject.toJSONString(value));
        jedis.close();
    }

    public void set(String key, String value) {
        Jedis jedis = getJedis();
        jedis.set(key, value);
        jedis.close();
    }

    public void set(Object key, Object value) {
        Jedis jedis = getJedis();
        jedis.set(JSONObject.toJSONString(key), JSONObject.toJSONString(value));
        jedis.close();
    }

    public void set(String key, Object value, int ttl) {
        Jedis jedis = getJedis();
        jedis.setex(key, ttl, JSONObject.toJSONString(value));
    }

    public void set(String key, String value, int ttl) {
        Jedis jedis = getJedis();
        jedis.setex(key, ttl, value);
    }

    public String get(String key) {
        Jedis jedis = getJedis();
        String value = jedis.get(key);
        jedis.close();
        return value;
    }

    public void del(String key){
        getJedis().del(key);
    }
}
