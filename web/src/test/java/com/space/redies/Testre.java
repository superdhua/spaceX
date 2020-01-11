package com.space.redies;

//import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Testre {
//    /**
//     * 切片链接池
//     */
//    private ShardedJedisPool shardedJedisPool;
//
//    /**
//     * 构造函数
//     */
//    public Testre() {
//        initialShardedPool();
//    }
//
//
//    /**
//     * @param jedis
//     * @Description: 关闭连接
//     */
//    private void returnResource(ShardedJedis jedis) {
//        if (jedis != null) {
//            shardedJedisPool.returnResource(jedis);
//        }
//    }
//
//    /**
//     * 初始化切片池
//     */
//    private void initialShardedPool() {
//        // 池基本配置
//        JedisPoolConfig config = new JedisPoolConfig();
//        //是否启用后进先出, 默认true
//        config.setLifo(true);
//        //最大空闲连接数, 默认8个
//        config.setMaxIdle(8);
//        //最大连接数, 默认8个
//        config.setMaxTotal(8);
//        //获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间, 默认-1
//        config.setMaxWaitMillis(-1);
//        //逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
//        config.setMinEvictableIdleTimeMillis(1800000);
//        //最小空闲连接数, 默认0
//        config.setMinIdle(0);
//        //每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
//        config.setNumTestsPerEvictionRun(3);
//        //对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断 (默认逐出策略)
//        config.setSoftMinEvictableIdleTimeMillis(1800000);
//        //在获取连接的时候检查有效性, 默认false
//        config.setTestOnBorrow(false);
//        //在空闲时检查有效性, 默认false
//        config.setTestWhileIdle(false);
//
//        // slave链接
//        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
//        //(存在部分问题)
//        JedisShardInfo re7000 = new JedisShardInfo("192.168.59.131", 7000);re7000.setPassword("etoak");
//        JedisShardInfo re7001 = new JedisShardInfo("192.168.59.131", 7001);re7001.setPassword("etoak");
//        JedisShardInfo re7002 = new JedisShardInfo("192.168.59.131", 7002);re7002.setPassword("etoak");
//        shards.add(re7000);
//        shards.add(re7001);
//        shards.add(re7002);
//        shardedJedisPool = new ShardedJedisPool(config, shards);
//        // 构造池
////        shardedJedisPool = new ShardedJedisPool(config, shards);
//
////   shardedJedisPool =new ShardedJedisPool(config, shards, Hashing.MURMUR_HASH,Sharded.DEFAULT_KEY_TAG_PATTERN);
//
//    }
//
//
//    // 获取连接(1)
//    private ShardedJedis getResource() {
//        ShardedJedis jedis = shardedJedisPool.getResource();
//        // jedis.auth(pwd);
//        return jedis;
//    }
//
//
//    // 获取连接(2)
//    private JedisCluster getJedisCluster() {
//        //可以设置单节点即可访问所有节点数据
//        Set<HostAndPort> set = new HashSet<HostAndPort>();
//        set.add(new HostAndPort("172.168.63.202", 7000));
//        JedisCluster client = new JedisCluster(set);
//        // client.auth("123456");
//        return client;
//    }
//
//
//    /**
//     * redis存储字符串
//     */
//
//    private static String testString(ShardedJedis jedis, String key) {
//        return jedis.get(key);
//    }
//
//
//    //test
//    public static  void main(String[] args) {
//        Testre service = new Testre();
//
//        String key = "ss";
//
//        //方式1
////        JedisCluster cluster = service.getJedisCluster();
////        cluster.set(key, "Cluster");
////        String name = cluster.get(key);
////        System.out.println("JedisCluster-name：" + name);
//
////        //方式2
//        ShardedJedis jedis = service.getResource();
////        String name2 = testString(jedis, key);
//        jedis.set("name","zhangsan");
//        System.out.println("ShardedJedis-name：" + jedis.get("name"));
//        jedis.close();
//    }

}
