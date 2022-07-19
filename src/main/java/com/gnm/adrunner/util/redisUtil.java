package com.gnm.adrunner.util;

import java.util.List;

import com.gnm.adrunner.config.RedisConfig;
import com.gnm.adrunner.server.object.RedisEntity;
import com.gnm.adrunner.server.object.RedisGroup;

public class redisUtil {


    // Redis에 클릭키 삽입
    public static void putck(String ck, String adsKey, String mediaKey, Integer redisGroup, Integer redisDB){

        RedisGroup rg = RedisConfig.redisConn.get(redisGroup);
        List<RedisEntity> re = rg.getList();
        
        re.get(keyBuilder.getRandomNumber(0, re.size()))
            .getDbList()
            .get(redisDB)
            .opsForList()
            .leftPush(keyBuilder.buildCKListID(adsKey, mediaKey), ck);    
        
        rg = null;
        re = null;
        
    }

}