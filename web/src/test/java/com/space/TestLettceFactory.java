package com.space;

import com.space.utils.RedisConfiguration;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestLettceFactory {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisConfiguration configuration;

    @Autowired
    private RedisAdvancedClusterCommands<String, String> reds;

    @Test

//    @Cacheable(cacheNames = "cacheNames",keyGenerator = "keyGenerator")
    public void redisTemplate() throws Exception {
        Set<ZSetOperations.TypedTuple<String>> typedTupleSet = new HashSet<>();
        Random r = new Random();
        for (int i = 0; i < 9; i++) {
            int score = r.nextInt(100);
            ZSetOperations.TypedTuple<String> z = new DefaultTypedTuple<String>("value_"+i, (double) score);
            typedTupleSet.add(z);
        }
        redisTemplate.opsForZSet().add("zet1",typedTupleSet);
        BoundZSetOperations<String,String> zSetOperations = redisTemplate.boundZSetOps("zet1");
        zSetOperations.add("value10",66);

        Set<String> set = zSetOperations.range(1,6);
        System.out.println("set = " + set);

        Set<String> socreset = zSetOperations.rangeByScore(65,100);
        System.out.println("socreset = " + socreset);

//        RedisZSetCommands.Range range = new RedisZSetCommands.Range() ;
//        range.gt("value_3");
//        Set<String> setLet = zSetOperations.rangeByLex(range);
//        System.out.println("setLet = " + setLet);

//        zSetOperations.remove("value_0","value_8");
        System.out.println("zSetOperations.score(\"value_3\") = " + zSetOperations.score("value_3"));

        Set<ZSetOperations.TypedTuple<String>> set1 = zSetOperations.rangeWithScores(1,6);
//        zSetOperations.rang
        System.out.println("set1 = " + set1);

        Map<String,Object> map = new HashMap<>();
        set1.forEach(new Consumer<ZSetOperations.TypedTuple<String>>() {
            @Override
            public void accept(ZSetOperations.TypedTuple<String> stringTypedTuple) {
                map.put(stringTypedTuple.getValue(),stringTypedTuple.getScore());
            }
        });
        System.out.println("map = " + map);
        Set<ZSetOperations.TypedTuple<String>> sets= zSetOperations.rangeByScoreWithScores(1,68);
        System.out.println("sets = " + sets);



//        sets.forEach(new Consumer<ZSetOperations.TypedTuple<String>>() {
//            @Override
//            public void accept(ZSetOperations.TypedTuple<String> stringTypedTuple) {
//                maps.put(stringTypedTuple.getValue(),stringTypedTuple.getScore());
//            }
//        });


        Set<String> reverseSet = zSetOperations.reverseRange(1,50);
        System.out.println("reverseSet = " + reverseSet);


    }


}




















