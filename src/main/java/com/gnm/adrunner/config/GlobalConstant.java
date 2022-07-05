package com.gnm.adrunner.config;

public class GlobalConstant {


    // 서버 정보
    public static String    HOST_NCPI_POSTBACK = "https://postback.adrunner.com/postback/ncpi";



    // REDIS 서버 호스트 1
	//public static String[]      REDIS_HOST          =   {"172.16.1.6"};
    public static String[]      REDIS_HOST          =   {"localhost"};
	public static Integer[]     REDIS_PORT          =   {6379};
    public static Integer       NUMBER_OF_REDIS     =   1;
    public static Integer       NUMBER_OF_REDISDB   =   16;


    // 클릭 서버 호스트
    public static String    CLICK_SERVER_HOST       =   "http://test.adrunner.co.kr:8081";


    // 포스트백 서버 호스트
    public static String    POSTBACK_SERVER_HOST    =   "http://test.adrunner.co.kr:8082";

    
    // 데이터베이스 서버 호스트

    public static String    DATABASE_HOST       =   "http://test.adrunner.co.kr:3306";



    public static String    PATH_UPDATE_MEMORY_DATA     = "/mem/refresh";
    public static String    PATH_ADD_MEMORY_DATA        = "/mem/add";
    public static String    PATH_DELETE_MEMORY_DATA     = "/mem/delete";
    
    public static Integer   ADS_TYPE_APP_NCPI           = 0;
    public static Integer   ADS_TYPE_WEB_CPA            = 1;
    public static Integer   ADS_TYPE_APP_CPA            = 2;


    public static Integer   ADS_STATUS_LIVE             = 0;
    public static Integer   ADS_STATUS_PAUSE            = 1;
    public static Integer   ADS_STATUS_DISMISS          = 2;
    public static Integer   ADS_STATUS_LOOPBACK         = 3;




    // 제휴사 번호 0은 파이어베이스 동적링크
    public static Integer   AFF_ID_DYNAMIC_LINK         = 0;        


    public static Integer   AFF_STATUS_READY            = 0;
    public static Integer   AFF_STATUS_PAUSE            = 1;
    public static Integer   AFF_STATUS_DISMISS          = 2;




    public static String getNCPIPostbackUrl(String ck, String ptnPub, String subPub, String aff){


        return HOST_NCPI_POSTBACK+"?aff="+aff+
                "&clickid="     + ck +
                "&pub="         + ptnPub + 
                "&sub_pub="     + subPub + 
                "&gaid={gaid}" + 
                "&idfa={idfa}" + 
                "&android_id={android_id}" +
                "&ip={ip_address}" +
                "&carrier={isp}" +
                "&country={country}" +
                "&language={language}" +
                "&os={os_name}" + 
                "&os_ver={os_version}" + 
                "&model={device_name}";
    }


   
    
    
}
