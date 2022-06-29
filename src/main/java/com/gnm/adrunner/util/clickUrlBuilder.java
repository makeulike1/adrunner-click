package com.gnm.adrunner.util;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.gnm.adrunner.config.GlobalConstant;
import com.gnm.adrunner.server.controller.click.clickParam;

import org.apache.commons.codec.digest.DigestUtils;

public class clickUrlBuilder {




    // 파라미터 타입 1 처리
    public static String buildUrlAddQuery(String paramKey, String paramValue, String redirectURL, clickParam cp){        

        paramValue = paramValue.replace("{ptn_pub}",        getPassValue("ptn_pub",         cp));
        paramValue = paramValue.replace("{click_key}",      getPassValue("click_key",       cp));
        paramValue = paramValue.replace("{sub_pub}",        getPassValue("sub_pub",         cp));
        paramValue = paramValue.replace("{gaid}",           getPassValue("gaid",            cp));
        paramValue = paramValue.replace("{idfa}",           getPassValue("idfa",            cp));
        paramValue = paramValue.replace("{postback_url}",   getPassValue("postback_url",    cp));

        redirectURL+="&"    +paramKey+  "=" +paramValue;

        paramKey    = null;
        
        return redirectURL;
    }








    // 파라미터 타입 0, 2 처리
    public static String replaceUrlQuery(String paramKey, String paramValue, String passValue, String redirectURL, clickParam cp) { 

        // 문자로 나눠져있을 경우, 선택적으로 파싱 */
        if(passValue.contains("|")){
            for(String s : passValue.split("\\|")){
                if(s.equals("gaid") && (cp.getOs().equals("0")))
                    return redirectURL.replace(paramValue, getPassValue("gaid", cp));
                if(s.equals("idfa") && (cp.getOs().equals("1")))
                    return redirectURL.replace(paramValue, getPassValue("idfa", cp));
            }
        }

        return redirectURL.replace(paramValue, getPassValue(passValue, cp));
    }










    // 서버에서 보낼 데이터 선별
    public static String getPassValue(String passValue,  clickParam cp){

        switch(passValue){
            case "ptn_pub"      :   return cp.getPtn_pub();
            case "click_key"    :   return cp.getClick_key();
            case "sub_pub"      :   return cp.getSub_pub();
            case "gaid"         :   return cp.getGaid();
            case "gaid_sha1"    :   return DigestUtils.sha1Hex(cp.getGaid());
            case "gaid_md5"     :   return DigestUtils.md5Hex(cp.getGaid());
            case "idfa"         :   return cp.getIdfa();
            case "idfa_sha1"    :   return DigestUtils.sha1Hex(cp.getIdfa());
            case "idfa_md5"     :   return DigestUtils.md5Hex(cp.getIdfa());
            case "brand"        :   return cp.getBrand();
            case "model"        :   return cp.getModel();
            case "postback_url" :   
                return URLEncoder.encode(
                    GlobalConstant.getNCPIPostbackUrl(
                        cp.getClick_key(), 
                        cp.getPtn_pub(), 
                        cp.getSub_pub(), 
                        cp.getAff()), 
                        StandardCharsets.UTF_8);

            default:return passValue;
        }
        
    }
    
}
