package com.gnm.adrunner.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class timeBuilder {

    // 현재 시각 반환
    public static String getCurrentTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    }


    // 현재 시각 반환
    public static Date toDate(String time) throws ParseException{
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
    }

    
    // 어제 날짜로 00:00:00 시각 반환
    public static String getYesterdayStartTime(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        return new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(c.getTime());
    }

    // 어제 날짜로 23:59:59 시각 반환
    public static String getYesterdayEndTime(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        return new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(c.getTime());
    }

    // 어제 날짜 반환
    public static String getYesterdayDate(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        return new SimpleDateFormat("yyyyMMdd").format(c.getTime());
    }

    // 오늘 날짜 반환
    public static String getTodayDate(){
        Calendar c = Calendar.getInstance();
        return new SimpleDateFormat("yyyyMMdd").format(c.getTime());
    }

    // 오늘 날짜로 00:00:00 시각 반환
    public static String getTodayStartTime(){
        return new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(Calendar.getInstance().getTime());
    }

    // 오늘 날짜로 23:59:59 시각 반환
    public static String getTodayEndTime(){
        return new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(Calendar.getInstance().getTime());
    }
    
}
