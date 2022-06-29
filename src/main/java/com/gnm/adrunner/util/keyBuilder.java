package com.gnm.adrunner.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.UUID;
;

public class keyBuilder {


    // 클릭키 생성
    // 클릭키 = [광고키]:[매체키]:[클릭시간]:[16자의 랜덤 대문자 UUID]
    public static String buildCK(String adsKey, String mediaKey){
        return adsKey + ":" + mediaKey + ":" + new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime()) + ":" + UUID.randomUUID().toString().replace("-", "").substring(0, 16).toUpperCase();
    }



    // UUID 생성
    public static String buildUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }




    // 클릭키 리스트 식별자 생성 = [광고키]:[일자]:[매체키]
    public static String buildCKListID(String adsKey, String mediaKey){
        return adsKey + ":" + new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + ":" + mediaKey;
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
