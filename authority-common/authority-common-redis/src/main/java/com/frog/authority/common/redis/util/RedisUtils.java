package com.frog.authority.common.redis.util;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * redis操作工具类
 *
 * @author frog
 */
@Component
@AllArgsConstructor
public class RedisUtils {

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置过期时间
     * @param key 缓存的键值
     * @param timeout 超时时间
     * @return 设置成功返回true,否则返回false
     */
    public boolean expire(final String key, final long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置过期时间
     * @param key 缓存的键值
     * @param timeout 超时时间
     * @param unit 时间单位
     * @return 设置成功返回true,否则返回false
     */
    public Boolean expire(final String key, final long timeout, final TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 获取过期时间
     * @param key Redis键
     * @return 有效时间
     */
    public Long getExpire(final String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 判断key是否存在
     * @param key 键
     * @return 存在返回true,否则返回false
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 缓存基本的对象
     * @param key 缓存的键值
     * @param value 缓存的值
     */
    public <T> void set(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存基本的对象
     * @param key 缓存的键值
     * @param value 缓存的值
     * @param timeout 时间
     * @param timeUnit 时间颗粒度
     */
    public <T> void set(final String key, final T value, final Long timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 获得缓存的值
     * @param key 缓存的键值
     * @return 缓存的值
     */
    public Object get(final String key) {
        ValueOperations<String, Object> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 删除单个对象
     * @param key 删除键
     * @return 删除成功返回true,否则返回false
     */
    public Boolean delete(final String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除单个对象
     * @param keys 删除键集合
     * @return 删除成功返回true,否则返回false
     */
    public Long delete(final Collection<String> keys) {
        return redisTemplate.delete(keys);
    }

    /**
     * 批量删除
     * @param keys 可以传一个值 或多个
     */
    public void delete(String... keys) {
        if (keys != null && keys.length > 0) {
            if (keys.length == 1) {
                redisTemplate.delete(keys[0]);
            } else {
                redisTemplate.delete(Arrays.asList(keys));
            }
        }
    }

}
