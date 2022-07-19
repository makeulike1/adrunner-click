package com.gnm.adrunner.config;

import java.util.ArrayList;
import java.util.List;

public class GlobalConstant {
  


        // REDIS 서버 호스트 
        public static Integer       REDIS_PORT                  =   6379;
        public static Integer       NUMBER_OF_REDIS_DB          =   16;
        public static Integer       NUMBER_OF_REDIS_GROUP       =   1;    
    
    
        public static String        PATH_UPDATE_MEMORY_DATA     = "/mem/refresh";
        public static String        PATH_ADD_MEMORY_DATA        = "/mem/add";
        public static String        PATH_DELETE_MEMORY_DATA     = "/mem/delete";
        
        public static Integer       ADS_TYPE_APP_NCPI           = 0;
        public static Integer       ADS_TYPE_WEB_CPA            = 1;
        public static Integer       ADS_TYPE_APP_CPA            = 2;
    
    
        public static Integer       ADS_STATUS_LIVE             = 0;
        public static Integer       ADS_STATUS_READY            = 1;
        public static Integer       ADS_STATUS_DISMISS          = 2;
        public static Integer       ADS_STATUS_LOOPBACK         = 3;
        public static Integer       ADS_STATUS_PAUSE            = 4;
    
        public static Integer       AFF_STATUS_READY            = 0;
        public static Integer       AFF_STATUS_PAUSE            = 1;
        public static Integer       AFF_STATUS_DISMISS          = 2;
    
        public static Integer       SERVER_TYPE_CLICK           = 0;
        public static Integer       SERVER_TYPE_ADMIN           = 1;
        public static Integer       SERVER_TYPE_PBACK           = 2;
        public static Integer       SERVER_TYPE_REDIS           = 3;
        public static Integer       SERVER_TYPE_MYSQL           = 4;


        
        // 제휴사 번호 0은 파이어베이스 동적링크, 1은 직광고
        public static Integer   AFF_ID_DYNAMIC_LINK         = 0;        
        public static Integer   AFF_ID_DIRECT_LINK          = 1;
    
    
        public static String                SERVER_HOST_CLICK           = "";
        public static String                SERVER_HOST_PBACK           = "";
        public static String                SERVER_HOST_ADMIN           = "";
        public static List<List<String>>    SERVER_HOST_REDIS           = new ArrayList<List<String>>();
    
    
    
    
        public static String getNCPIPostbackUrl(String ck, String ptnPub, String subPub, String aff){
    
    
            return SERVER_HOST_PBACK+"?aff="+aff+
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
