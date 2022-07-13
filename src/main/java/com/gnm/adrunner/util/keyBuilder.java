package com.gnm.adrunner.util;
import java.util.UUID;
;

public class keyBuilder {


    // 클릭키 생성
    // 클릭키 = [광고키]:[매체키]:[클릭시간]:[6자의 랜덤 대문자 UUID]:[매체사 고유 클릭키]:[ptnPub]:[subPub]:[GAID]:[IDFA]:[S_P1]:[S_P2]:[S_P3]:[S_P4]:[S_P5]
    public static String buildCK(
        String adsKey, 
        String mediaKey, 
        String ptnCK,
        String ptnPub,
        String subPub,
        String sp1,
        String sp2, 
        String sp3,
        String sp4,
        String sp5,
        String gaid,
        String idfa){
    
        return  adsKey + ":" + mediaKey + ":" + timeBuilder.getCurrentTime2() + ":" + 
                UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase() + ":" +
                ptnCK   + ":" +
                ptnPub  + ":" +
                subPub  + ":" +
                gaid    + ":" +
                idfa    + ":" +
                sp1     + ":" +
                sp2     + ":" +
                sp3     + ":" +
                sp4     + ":" +
                sp5;
    }



    // UUID 생성
    public static String buildUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }




    // 클릭키 리스트 식별자 생성 = [광고키]:[일자]:[매체키]
    public static String buildCKListID(String adsKey, String mediaKey){
        return adsKey + ":" + timeBuilder.getTodayDate() + ":" + mediaKey;
    }



    // min <= (x) <max 사이의 랜덤 값 생성 */
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }


    // 매체사 키, 광고주 키 발급
    public static String buildIdentifier(Integer Id){
        return Id.toString() + keyBuilder.buildUUID().substring(0, 5).toUpperCase();
    }
}
