package com.gnm.adrunner.config;

import java.util.ArrayList;
import java.util.List;

import com.gnm.adrunner.server.object.RedisEntity;
import com.gnm.adrunner.server.object.RedisGroup;

import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;


public class RedisConfig {
    
   
    public static List<RedisGroup> redisConn = new ArrayList<RedisGroup>();

 
    //REDIS 시작
	public static void init(){

        // 레디스 서버 그룹 순회
        for(int rgId=0; rgId<GlobalConstant.NUMBER_OF_REDIS_GROUP;rgId++){


            Integer numOfRe   = GlobalConstant.SERVER_HOST_REDIS.get(rgId).size();

            

            List<RedisEntity> entityList = new ArrayList<RedisEntity>();

            // 레디스 그룹별 서버 호스트 순회
            for(int reId=0; reId<numOfRe; reId++){

                List<RedisTemplate<String, Object>> tmp = new ArrayList<RedisTemplate<String, Object>>();

                String  redisHost       = GlobalConstant.SERVER_HOST_REDIS.get(rgId).get(reId);

                // 레디스 DB는 1부터 16개까지
                for(int rdbId=0; rdbId<GlobalConstant.NUMBER_OF_REDIS_DB; rdbId++)
                    tmp.add(buildTemplate(redisHost, GlobalConstant.REDIS_PORT, rdbId));

                redisHost = null;

                entityList.add(new RedisEntity(rgId, tmp));
            }

        
            redisConn.add(new RedisGroup(rgId, entityList));

            numOfRe = null;
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
