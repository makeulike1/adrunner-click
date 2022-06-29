package com.gnm.adrunner.config;

import java.util.List;

import com.gnm.adrunner.server.entity.Ads;
import com.gnm.adrunner.server.entity.AdsMedia;
import com.gnm.adrunner.server.entity.Aff;
import com.gnm.adrunner.server.entity.AffParam;
import com.gnm.adrunner.server.entity.Media;

public class MemoryData {

    
    
     // [메모리 데이터]제휴사 파라미터 목록
     public static List<AffParam>           affParamList;





     // [메모리 데이터]제휴사 목록
     public static List<Aff>                affList;




    
     // [메모리 데이터]광고 목록
     public static List<Ads>                adsList;
 
 
 



     // [메모리 데이터]매체사 목록
     public static List<Media>              mediaList;




     

     // [메모리 데이터]광고에 연동된 매체사 목록
     public static List<AdsMedia>           adsMediaList;


 
 
 
     // 광고 키에 해당하는 광고 조회
     public static Ads getAdsByAdsKey(String adsKey){
 
         for(Ads it : adsList){
             if(it.getAdsKey().equals(adsKey))
                 return it;
         }
 
         return null;
 
     }




    // 광고에 연동되어 있는 매체사인지 확인
    public static boolean isMediatoAds(String mediaKey, String adsKey) {
        for(AdsMedia it : adsMediaList){
            if(it.getAdsKey().equals(adsKey)){
                if(it.getMediaKey().equals(mediaKey))
                    return true;
            }
        }
        return false;
    }

 
}
