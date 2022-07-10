package com.gnm.adrunner.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class timeBuilder {


    public static TimeZone          timeZone            = TimeZone.getTimeZone("Asia/Seoul");

    public static SimpleDateFormat  simpleDateFormat    = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);

    public static SimpleDateFormat  simpleDateFormat0    = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);

    public static SimpleDateFormat  simpleDateFormat1   = new SimpleDateFormat("yyyy-MM-dd 00:00:00", Locale.KOREA);

    public static SimpleDateFormat  simpleDateFormat2   = new SimpleDateFormat("yyyy-MM-dd 23:59:59", Locale.KOREA);

    public static SimpleDateFormat  simpleDateFormat3   = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);

    // 현재 시각 반환
    public static String getCurrentTime(){
        simpleDateFormat0.setTimeZone(timeZone);
        return simpleDateFormat0.format(Calendar.getInstance(timeZone).getTime());
    }

    // 현재 시각 반환
    public static String getCurrentTime2(){
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(Calendar.getInstance(timeZone).getTime());
    }

    // 현재 시각 반환
    public static Date toDate(String time) throws ParseException{
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.parse(time);
    }

    
    // 어제 날짜로 00:00:00 시각 반환
    public static String getYesterdayStartTime(){
        Calendar c = Calendar.getInstance(timeZone);
        c.add(Calendar.DATE, -1);
        simpleDateFormat1.setTimeZone(timeZone);
        return simpleDateFormat1.format(c.getTime());
    }


    // 어제 날짜로 23:59:59 시각 반환
    public static String getYesterdayEndTime(){
        Calendar c = Calendar.getInstance(timeZone);
        c.add(Calendar.DATE, -1);
        simpleDateFormat2.setTimeZone(timeZone);
        return simpleDateFormat2.format(c.getTime());
    }
    

    // 어제 날짜 반환
    public static String getYesterdayDate(){
        Calendar c = Calendar.getInstance(timeZone);
        c.add(Calendar.DATE, -1);
        simpleDateFormat3.setTimeZone(timeZone);
        return simpleDateFormat3.format(c.getTime());
    }



    // 전 날짜 반환 : day 만큼의 전 날짜 반환
    public static String getPreviousDate(Integer day){
        Calendar c = Calendar.getInstance(timeZone);
        c.add(Calendar.DATE, day);
        simpleDateFormat3.setTimeZone(timeZone);
        return simpleDateFormat3.format(c.getTime());
    }




    // 오늘 날짜 반환
    public static String getTodayDate(){
        simpleDateFormat3.setTimeZone(timeZone);
        return simpleDateFormat3.format(Calendar.getInstance(timeZone).getTime());
    }


    // 오늘 날짜로 00:00:00 시각 반환
    public static String getTodayStartTime(){
        simpleDateFormat1.setTimeZone(timeZone);
        return simpleDateFormat1.format(Calendar.getInstance(timeZone).getTime());
    }


    // 오늘 날짜로 23:59:59 시각 반환
    public static String getTodayEndTime(){
        simpleDateFormat2.setTimeZone(timeZone);
        return simpleDateFormat2.format(Calendar.getInstance(timeZone).getTime());
    }

    
}
