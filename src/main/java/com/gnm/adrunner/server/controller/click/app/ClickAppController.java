package com.gnm.adrunner.server.controller.click.app;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import com.gnm.adrunner.util.timeBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gnm.adrunner.config.GlobalConstant;
import com.gnm.adrunner.config.MemoryData;
import com.gnm.adrunner.server.RequestResponseInterface;
import com.gnm.adrunner.server.controller.click.clickParam;
import com.gnm.adrunner.server.entity.Ads;
import com.gnm.adrunner.server.entity.AffParam;
import com.gnm.adrunner.server.entity.Media;
import com.gnm.adrunner.util.keyBuilder;
import com.gnm.adrunner.util.redisUtil;
import com.gnm.adrunner.util.clickUrlBuilder;


   

// 앱 클릭 요청 처리 : NCPI, CPA 광고 참여
@Controller 
public class ClickAppController extends RequestResponseInterface{
     

    @GetMapping("/click/app") 
    public ResponseEntity<String> clickAPP(
        @RequestParam(value="ptn_clk", required = false, defaultValue = "") String ptnClk,
        @RequestParam(value="ptn_pub", required = false) String ptnPub,
        @RequestParam(value="sub_pub", required = false) String subPub,
        @RequestParam(value="gaid", required = false, defaultValue = "") String gaid,
        @RequestParam(value="idfa", required = false, defaultValue = "") String idfa,
        @RequestParam(value="s_p1", required = false, defaultValue = "") String sP1,
        @RequestParam(value="s_p2", required = false, defaultValue = "") String sP2,
        @RequestParam(value="s_p3", required = false, defaultValue = "") String sP3,
        @RequestParam(value="s_p4", required = false, defaultValue = "") String sP4,
        @RequestParam(value="s_p5", required = false, defaultValue = "") String sP5,
        @RequestParam(value="ads_key", required = false) String adsKey,
        @RequestParam(value="media_key", required = false, defaultValue = "00000") String mediaKey,
        HttpServletRequest request,
        HttpServletResponse response){
            
        
            HttpHeaders responseHeaders = new HttpHeaders();

            String redirectURL = "";


            // 광고키가 없는 경우 209 에러
            if(adsKey == null)
                return ResponseEntity.status(209)
                    .headers(responseHeaders)
                    .body(RequestResponseInterface.getStatusMessage(209));
                

            try{
                 


                Ads ads = MemoryData.getAdsByAdsKey(adsKey);
           
                


                // 존재하지 않는 광고의 경우 208 에러
                if(ads == null)
                    return ResponseEntity.status(208)
                        .headers(responseHeaders)
                        .body(RequestResponseInterface.getStatusMessage(208));
                




                // 없는 매체사 키인 경우 212 에러
                if(!mediaKey.equals("00000")){

                    Boolean isIn = false;
                    for(Media e : MemoryData.mediaList)
                        if(e.getMediaKey().equals(mediaKey)){
                            isIn = true;
                            break;
                        }
                    
                    if(!isIn)
                        return ResponseEntity.status(212)
                            .headers(responseHeaders)
                            .body(RequestResponseInterface.getStatusMessage(212));
                }




                // 광고에 연동되어 있는 매체사가 아닌 경우 213 에러
                if(!MemoryData.isMediatoAds(mediaKey, adsKey))
                    return ResponseEntity.status(213)
                                .headers(responseHeaders)
                                .body(RequestResponseInterface.getStatusMessage(213));

 




                // 일시 중지된 광고인 경우 211 에러
                if(ads.getStatus() == GlobalConstant.ADS_STATUS_PAUSE)
                    return ResponseEntity.status(211)
                            .headers(responseHeaders)
                            .body(RequestResponseInterface.getStatusMessage(211));






                // 종료된 광고인 경우 226 에러
                if(ads.getStatus() == GlobalConstant.ADS_STATUS_DISMISS)
                    return ResponseEntity.status(226)
                            .headers(responseHeaders)
                            .body(RequestResponseInterface.getStatusMessage(226));



                


                // 광고가 루프백 상태인 경우 226 에러
                if(ads.getStatus() == GlobalConstant.ADS_STATUS_LOOPBACK)
                    return ResponseEntity.status(226)
                            .headers(responseHeaders)
                            .body(RequestResponseInterface.getStatusMessage(226));

                
                


                
                // 클릭키 생성
                String ck    =  keyBuilder.buildCK(adsKey, mediaKey);
                       





 
                // 파라미터 생성을 위해서 클릭 객체 생성
                clickParam cp = new clickParam();
                cp.setClick_key(ck);
                cp.setGaid(gaid);
                cp.setIdfa(idfa);
                cp.setPtn_pub(ptnPub);
                cp.setSub_pub(subPub);
                cp.setOs(ads.getOs().toString());
                cp.setAff(ads.getAff().toString());
                cp.setAds_type(ads.getType().toString());
                cp.setIp(request.getRemoteAddr());
                cp.setDatetime(timeBuilder.getCurrentTime());
                cp.setAds_key(adsKey);
                cp.setS_p1(sP1);
                cp.setS_p2(sP2);
                cp.setS_p3(sP3);
                cp.setS_p4(sP4);
                cp.setS_p5(sP5);
                cp.setPtn_clk(ptnClk);
                cp.setAds_id(ads.getId().toString());
 

             
                // 클릭 키 삽입
                redisUtil.putck(ck, adsKey, mediaKey, ads.getRedisIndex());


                

                // 제휴사 파라미터 조합으로 트래킹 URL 생성 ****/
                redirectURL = buildTrackingURL(ads.getTrackingUrl(),  ads.getAff(),  cp);



                ads  = null;
                cp = null;
                ck = null;



            }catch(NoSuchElementException e){
                return ResponseEntity.status(201).headers(responseHeaders).body(RequestResponseInterface.getStatusMessage(201));
            }
                        
            
            // 클릭 요청이 성공했을 경우 조합된 URL로 링크 이동
            responseHeaders.set("Location", redirectURL); 
            
            return ResponseEntity.status(302)
                .headers(responseHeaders)
                .body("url : "+redirectURL);

    }








    // 제휴사 파라미터 조합으로 트래킹 URL 생성 ****/
    public String buildTrackingURL(String trackingURL, Integer affId, clickParam cp){
        
        String redirectURL = "";
 
        // 제휴사 식별자가 0이면 Firebase 동적링크 리다이렉트
        if(affId == GlobalConstant.AFF_ID_DYNAMIC_LINK){
 
            try{

                // 동적링크 : [URL프리픽스]/?link=[딥 링크][&쿼리]
                String[] token = trackingURL.split("link=");
                String[] urlQuery = token[1].split("&");

                String tmp = "";

                if(urlQuery[0].contains("%3F"))
                    tmp = "%26";
                else tmp =  "%3F";

                redirectURL = token[0] +"link="+urlQuery[0]+tmp+"adrunner_ck="+cp.getClick_key();

                if(urlQuery.length > 1){
                    for(int i=1; i<urlQuery.length; i++)
                        redirectURL += "&" + urlQuery[i];
                }

            }catch(Exception e){

            }
        

        }else if(affId == GlobalConstant.AFF_ID_DIRECT_LINK){
            redirectURL = trackingURL;

        }else{

            for(AffParam it : MemoryData.affParamList){

                if(it.getAffId() == affId){
                    // 파라미터 타입이 0 혹은 2인 경우,  기존 URL의 파라미터를 클릭으로 들어온 인자로 변경함
                    if((it.getParamType().equals(0) || it.getParamType().equals(2)))
                        redirectURL = clickUrlBuilder.replaceUrlQuery(
                                it.getParamKey(),
                                it.getParamValue(),
                                it.getPassValue(),
                                trackingURL, cp);
                            

                    // 파라미터 타입이 1인 경우, 기본 URL 위에 쿼리를 신규로 추가 
                    if(it.getParamType().equals(1))
                        redirectURL = clickUrlBuilder.buildUrlAddQuery(
                                it.getParamKey(),
                                it.getParamValue(), redirectURL, cp);
                }
                            
            }
        }

        return redirectURL;


    }
 
    
    public static Map<String, String> splitQuery(URL url) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String query = url.getQuery();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }



}
