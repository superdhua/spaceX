package com.space.utils;

import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;
import java.util.ArrayList;

@Configuration
public class RedisConfiguration {

    @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;

    @Value("${spring.redis.password}")
    private String pwd;
//    @Bean
//    public RedisTemplate<String, Serializable> redisTemplate() {
//        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        template.setConnectionFactory(myLettuceConnectionFactory);
//        return template;
//    }
    //连接池配置信息（如果不用连接池可以省略这一步）
//    @Bean
    //prefix，表示从配置文件读取以前缀开头的信息，注入到GenericObjectPoolConfig中
//    @ConfigurationProperties(prefix="generic.pool.config")
//    public GenericObjectPoolConfig genericObjectPoolConfig() {
//        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
//        return poolConfig;
//    }

    //lettuce客户端配置信息（如果不用连接池通过LettuceClientConfiguration来builder）
//    @Bean
//    public LettuceClientConfiguration lettuceClientConfiguration(){
//        //构造LettucePoolingClientConfiguration对象，同时加入连接池配置信息
////        LettucePoolingClientConfiguration build = LettucePoolingClientConfiguration.builder().poolConfig(genericObjectPoolConfig).build();
//        LettuceClientConfiguration build = (LettuceClientConfiguration) LettuceClientConfiguration.builder();
//        return build;
//    }
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }
    //Redis继续配置信息
    @Bean
    @ConfigurationProperties(prefix="redis.cluster")
    public RedisClusterConfiguration redisClusterConfiguration(){
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        //配置集群信息（也可以从配置文件加载）
        ArrayList<RedisNode> list = new ArrayList<>();
        String[] cnodes = clusterNodes.split(",");
        String[] hp;
        for (String cp : cnodes) {
            hp = cp.split(":");
            list.add(new RedisNode(hp[0],Integer.parseInt(hp[1])));
        }
        redisClusterConfiguration.setClusterNodes(list);
        redisClusterConfiguration.setPassword(pwd.toCharArray());
        return  redisClusterConfiguration;
    }

    //配置RedisTemplate
    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory){
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        //设置key的存储方式为字符串
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置为value的存储方式为JDK二进制序列化方式，还有jackson序列化方式（Jackson2JsonRedisSerialize）
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        //注入Lettuce连接工厂
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        return redisTemplate;
    }
    //配置连接模式
    @Bean
    public  RedisAdvancedClusterCommands<String, String> LettuceFatory(){
        //配置集群信息（也可以从配置文件加载）
        ArrayList<RedisURI> list = new ArrayList<>();
        String[] cnodes = clusterNodes.split(",");
        String[] hp;
        for (String cp : cnodes) {
            hp = cp.split(":");
            RedisURI node = RedisURI.create(hp[0], Integer.parseInt(hp[1]));
            node.setPassword(pwd.toCharArray());
            list.add(node);
        }
    RedisClusterClient clusterClient = RedisClusterClient.create(list);
    StatefulRedisClusterConnection<String, String> connection = clusterClient.connect();
    //        RedisAdvancedClusterAsyncCommands<String, String> conn = connection.async();
    RedisAdvancedClusterCommands<String, String> syncCommands = connection.sync();
        return syncCommands;
    }
}
