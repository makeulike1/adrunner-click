package com.gnm.adrunner.config;

import java.util.ArrayList;
import java.util.List;


import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;


public class RedisConfig {
    
    public static List<List<RedisTemplate<String, Object>>> redisConn = new ArrayList<List<RedisTemplate<String, Object>>>();

 
    //REDIS 시작
	public static void init(){

        for(int i=0; i<GlobalConstant.NUMBER_OF_REDIS; i++){
            List<RedisTemplate<String, Object>> tmp = new ArrayList<RedisTemplate<String, Object>>();

            for(int i1=0; i1<=GlobalConstant.NUMBER_OF_REDISDB; i1++){
                RedisTemplate<String, Object> conn = buildTemplate(GlobalConstant.REDIS_HOST[i], GlobalConstant.REDIS_PORT[i], i1); 
                tmp.add(conn);
            }

            redisConn.add(tmp);
        }
	}


    public static RedisTemplate<String, Object> buildTemplate(String host, Integer port, Integer dbIndex){
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(host);
        config.setPort(port);
        config.setDatabase(dbIndex);


        JedisConnectionFactory conn = new JedisConnectionFactory(config);
        conn.afterPropertiesSet();

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(conn);   
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setEnableDefaultSerializer(false);
        template.afterPropertiesSet();
        return template;
    }
}
