package com.space.redies;

import com.alibaba.druid.support.json.JSONUtils;
import  io.lettuce.core.*;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.async.RedisAdvancedClusterAsyncCommands;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import io.lettuce.core.codec.Utf8StringCodec;
import io.lettuce.core.masterslave.MasterSlave;
import io.lettuce.core.masterslave.StatefulRedisMasterSlaveConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ClusterOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;


public class TestLettuce {
   
    public static   void main(String[] args) {
        // Syntax: redis://[password@]host[:port]
//        RedisClusterClient redisClient = RedisClusterClient.create("redis://etoak@192.168.59.131:7000");
//
//        StatefulRedisClusterConnection<String, String> connection = redisClient.connect();
//        System.out.println("Connected to Redis");
//
//        connection.close();
//        redisClient.shutdown();
//        ClusterOperations clusterOps = redisTemplate.opsForCluster();
//        clusterOps.shutdown("2222");


        RedisURI node1 = RedisURI.create("192.168.59.131", 7000);
        node1.setPassword("etoak".toCharArray());
        RedisURI node2 = RedisURI.create("192.168.59.131", 7001);
        node2.setPassword("etoak".toCharArray());
        RedisURI node3 = RedisURI.create("192.168.59.131", 7002);
        node3.setPassword("etoak".toCharArray());
        RedisClusterClient clusterClient = RedisClusterClient.create(Arrays.asList(node1, node2,node3));
        StatefulRedisClusterConnection<String, String> connection = clusterClient.connect();
//        RedisAdvancedClusterAsyncCommands<String, String> conn = connection.async();
        RedisAdvancedClusterCommands<String, String> syncCommands = connection.sync();
//        syncCommands.lpush("list","zhangsan","lisi","王五","赵六");
//        System.out.println("syncCommands.rpop(\"list\") = " + syncCommands.rpop("list"));
//        syncCommands.set("keys","hello world");
//        System.out.println("================   "+syncCommands.get("keys")+"   ================================");

        connection.close();
        clusterClient.shutdown();

    }
}
