package com.test.roc.core.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisUtil {
    /**
     * 分布式锁超时时间,2s
     */
    private static long timeout = 2000L;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置key-value
     * @param key 键
     * @param value 值
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置带生存时间的key-value
     * @param key 键
     * @param value 值
     * @param timeout 生存时间
     * @param unit 时间单位
     */
    public void set(String key, String value, long timeout, TimeUnit unit) {
        try {
            redisTemplate.opsForValue().set(key, value, timeout, unit);
        } catch (Exception e) {
            log.error("redis异常:",e);
        }
    }

    /**
     * 设置指定数据的生存时间。
     * @param key 键
     * @param time 生存时间（秒）
     */
    public void expire(String key, long time) {
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 根据key，获取值
     * @param key 键
     * @return 获取到的值
     */
    public String get(String key) {
        try {
            Object value = redisTemplate.opsForValue().get(key);
            return value == null ? null :String.valueOf(value);
        } catch (Exception e) {
            log.error("redis异常:",e);
            return null;
        }
    }

    /**
     * 删除指定信息。
     * @param key 键
     * @return 是否删除成功
     */
    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 获取指定的 key 集合
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern){
        return redisTemplate.keys(pattern);
    }

    /**
     * Set集合：将value加入到 Set 集合中
     * @param key
     * @param value
     * @param time 设置key值删除时间，单位秒
     * @return
     */
    public long sadd(String key,String value,long time){
        Long result = redisTemplate.opsForSet().add(key, value);
        expire(key,time);
        return result;
    }

    /**
     * Set集合：移除集合中的指定 key 的一个或多个随机元素，移除后会返回移除的元素
     * @param key
     * @return
     */
    public String spop(String key){
        Object value = redisTemplate.opsForSet().pop(key);
        return value == null ? null :String.valueOf(value);
    }

    /**
     * Set集合：返回集合中的所有的成员
     * @param key
     * @return
     */
    public Set<String> smembers(String key){
        Set<String> set = redisTemplate.opsForSet().members(key);
        return set == null ? null :set;
    }


    /**
     * List操作：将一个或多个值插入到列表头部
     * @param key
     * @return list中元素数量
     */
    public Long lpush(String key,String value){
        Long count = redisTemplate.opsForList().leftPush(key, value);
        return count;
    }

    /**
     * 将 key对应的value中储存的数字值增一，然后返回。
     * @param key
     * @return
     */
    public long incr(String key){
        return redisTemplate.opsForValue().increment(key);
    }

    /**
     * 将 key 对应的value中储存的数字值减一，然后返回。
     * @param key
     * @return
     */
    public long decr(String key){
        return redisTemplate.opsForValue().decrement(key);
    }

    /**
     * 判断 key 是否存在
     * @param key
     * @return
     */
    public boolean exists(String key){
        return redisTemplate.hasKey(key);
    }

    /**
     * Hash结构:hmset 命令设置hash值，永久不过期
     * @param key
     * @param field
     * @param value
     */
    public void hmset(String key,String field,String value){
        redisTemplate.opsForHash().put(key,field,value);
    }
    /**
     * Hash结构:hmset 命令设置hash值
     * @param key
     * @param field
     * @param value
     * @param time 设置key值删除时间，单位秒
     */
    public void hmset(String key,String field,String value,long time){
        redisTemplate.opsForHash().put(key,field,value);
        expire(key,time);
    }

    /**
     * Hash结构:hget命令,获取存储在哈希表中指定字段的值
     * @param key
     * @param field
     * @return
     */
    public String hget(String key,String field){
        Object value = redisTemplate.opsForHash().get(key, field);
        return value == null ? null :String.valueOf(value);
    }

    /**
     * Hash结构:hget命令,获取key对应的所有键值对
     * @param key
     * @return
     */
    public Map<String,String> hgetall(String key){
        Map<String,String> value = redisTemplate.opsForHash().entries(key);
        return value;
    }

    /**
     * Hash结构:hget命令,获取key对应的所有键值对
     * @param key
     * @param map
     * @param time 设置key值删除时间，单位秒
     */
    public void putall(String key,Map<String, String> map,long time){
        redisTemplate.opsForHash().putAll(key,map);
        expire(key,time);
    }

    /**
     * 删除hash表中的值
     * @param key 键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 如果为空就set值，并返回1,否则不进行操作，并返回0（锁定10s）
     * @param key
     * @param value
     * @return
     */
    public boolean setnx(String key,String value,long timeout, TimeUnit timeUnit){
        return redisTemplate.opsForValue().setIfAbsent(key,value,timeout,timeUnit);
    }

    /**
     * 批量获取key 值
     * @param keys
     * @return
     */
    public List<String> multiGet(List<String> keys){
        return redisTemplate.opsForValue().multiGet(keys);
    }

    /**
     * 上锁
     * @param key 业务标识
     * @return 上锁状态
     */
    public boolean lock(String key) {
        long start = System.currentTimeMillis();
        while (true){
            // 检测是否超时
            if (System.currentTimeMillis() - start > timeout) {
                return false;
            }
            boolean absent = setnx(key,"lock", timeout, TimeUnit.MILLISECONDS);
            if(absent){
                return true;
            }
        }
    }

    /**
     * 解锁
     *  @param key 业务标识
     * @return 解锁状态
     */
    public boolean unlock(String key) {
        return delete(key);
    }


}