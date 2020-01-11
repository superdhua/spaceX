package com.space;

import com.space.utils.RedisConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestLettceFactory {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisConfiguration configuration;


    @Test

//    @Cacheable(cacheNames = "cacheNames",keyGenerator = "keyGenerator")
    public void redisTemplate() throws Exception {
//        redisTemplate.opsForValue().set("author", "Damein_xym");
//        ValueOperations<String, Serializable> opsForValue = (ValueOperations<String, Serializable>) redisTemplate.o();

        Boolean bool = redisTemplate.hasKey("author");
        System.out.println("============= bool ================ " + bool);
        Set<String> set = new HashSet<>();
        set.add("111");
        set.add("222");
        set.add("122");
        set.add("33");
//        redisTemplate.opsForSet().add("ssss",set);
        redisTemplate.opsForZSet();
//        Boolean bs =  redisTemplate.hasKey("set");
//        System.out.println("============== bs ==================" + bs);



    }

}























