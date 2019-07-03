package com.redis.test;

import com.redis.config.RedisConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Created by LiBingyi on 2019/7/2 14:47
 */
public class RedisTest {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(RedisConfig.class);
        StringRedisTemplate redisTemplate = (StringRedisTemplate) context.getBean("stringRedisTemplate");
        for (int i = 0; i < 1000; i++) {
            redisTemplate.opsForValue().set(String.valueOf(i), String.valueOf(i));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 1000; i++) {
            redisTemplate.opsForValue().get(String.valueOf(i));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
