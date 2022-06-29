package com.gnm.adrunner.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import com.gnm.adrunner.config.GlobalConstant;
import com.gnm.adrunner.config.RedisConfig;

import org.springframework.data.redis.core.RedisCallback;

public class redisUtil {

    
    // Redis 리스트 사이즈 반환
    public static Integer getListSize(String ckListId, Integer redisIndex){
        Integer result = 0;

        for(int i=0;i<GlobalConstant.NUMBER_OF_REDIS;i++){
            Integer tmp = 0;
            Object clkKeyListSize       = RedisConfig.redisConn.get(i).get(redisIndex).opsForList().size(ckListId);
            if(clkKeyListSize == null)
                tmp = 0;
            else tmp = Integer.parseInt(clkKeyListSize.toString());
            result += tmp;
        }

        return result;
    }
    




    

    // Redis 클릭수 반환
    public static Integer getCkCount(String adsKey, Integer redisIndex){

        Integer totalCkCount = 0;
        for(int i=0;i<GlobalConstant.NUMBER_OF_REDIS;i++){
            Set<String> redisKeys = RedisConfig.redisConn.get(i).get(redisIndex).keys(adsKey+"*");
            List<String> keysList = new ArrayList<>(); 
            Iterator<String> it = redisKeys.iterator();
            while (it.hasNext()) {
                keysList.add(it.next());
            }

            
            Integer clickCount = 0;
            for(String key : keysList){
                Object tmp = RedisConfig.redisConn.get(i).get(redisIndex).opsForList().size(key);
                if(tmp != null)
                    clickCount += Integer.parseInt(tmp.toString());
            }

            totalCkCount += clickCount;
        }

        return totalCkCount;
    }




    // Redis DB 초기화 
    public static void flushDB(Integer redisIndex){
        for(int i=0;i<GlobalConstant.NUMBER_OF_REDIS;i++){
            RedisConfig.redisConn.get(i).get(redisIndex).execute((RedisCallback<String>) connection -> {
                connection.flushDb();
                return "ok";
            });
        }
    }
 






    // Redis에 클릭키 삽입
    public static void putck(String ck, String adsKey, String mediaKey, Integer redisIndex){
        RedisConfig.redisConn
                .get(ThreadLocalRandom.current()
                .nextInt(0, GlobalConstant.NUMBER_OF_REDIS))
                .get(redisIndex).opsForList().leftPush(keyBuilder.buildCKListID(adsKey, mediaKey), ck);    

    }

 


    

    // Redis에서 클릭키 검색
    public static Boolean findck(String adsKey, String ck, Integer redisIndex){

        for(int i=0;i<GlobalConstant.NUMBER_OF_REDIS;i++){
            Set<String> redisKeys = RedisConfig.redisConn.get(i).get(redisIndex).keys(adsKey+"*");
            List<String> keysList = new ArrayList<>(); 
            Iterator<String> it = redisKeys.iterator();
            while (it.hasNext()) {
                keysList.add(it.next());
            }

            for(String listKey : keysList){
                Object tmp = RedisConfig.redisConn.get(i).get(redisIndex).opsForList().indexOf(listKey, ck);
                if(tmp != null)
                    return true;
            }
        }

        
        return false;
    }




    // Redis에서 금일 마지막으로 발생한 클릭키 100건 검색
    public static List<String> getLatestck(String adsKey, String[] mediaKeyList, Integer redisIndex){


        List<String> result = new ArrayList<String>();
        for(String e : mediaKeyList){
            // Redis에서 클릭키 조회 - 리스트 이름은 [광고키:오늘날짜:매체사키] 정규식 패턴
            for(int i=0;i<GlobalConstant.NUMBER_OF_REDIS;i++){
                String ck = adsKey+":"+timeBuilder.getTodayDate()+":"+e;
                List<Object> list = RedisConfig.redisConn.get(i).get(redisIndex).opsForList().range(ck, 0, 5);
                for(Object e1 : list)result.add(e1.toString());
                list = null;
            }
        }

        return result;
    }




}
