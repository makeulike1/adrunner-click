package com.gnm.adrunner.server.controller.click.web.cpa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.gnm.adrunner.config.GlobalConstant;
import com.gnm.adrunner.config.MemoryData;
import com.gnm.adrunner.server.RequestResponseInterface;
import com.gnm.adrunner.server.entity.Ads;
import com.gnm.adrunner.server.entity.Media;
import com.gnm.adrunner.server.repo.AdsRepository;
import com.gnm.adrunner.util.keyBuilder;
import com.gnm.adrunner.util.redisUtil;



// 클릭 요청 처리
@Controller 
public class ClickWebCpaController extends RequestResponseInterface{
    



    @Autowired  
    private AdsRepository adsRepository;

 

    

    // 웹 광고 클릭 참여
    @CrossOrigin(origins = "*")
    @GetMapping("/click/web") 
    public ResponseEntity<String> clickWebCPA(
            @RequestParam(value="ads_key", required = false) String adsKey,
            @RequestParam(value="ptn_clk", required = false, defaultValue = "") String ptnCK,
            @RequestParam(value="ptn_pub", required = false) String ptnPub,
            @RequestParam(value="sub_pub", required = false) String subPub,
            @RequestParam(value="gaid", required = false, defaultValue = "") String gaid,
            @RequestParam(value="idfa", required = false, defaultValue = "") String idfa,
            @RequestParam(value="s_p1", required = false, defaultValue = "") String sP1,
            @RequestParam(value="s_p2", required = false, defaultValue = "") String sP2,
            @RequestParam(value="s_p3", required = false, defaultValue = "") String sP3,
            @RequestParam(value="s_p4", required = false, defaultValue = "") String sP4,
            @RequestParam(value="s_p5", required = false, defaultValue = "") String sP5,
            @RequestParam(value="media_key", required = false,  defaultValue = "00000") String mediaKey,
            HttpServletRequest request,
            HttpServletResponse response){
 
    
        HttpHeaders responseHeaders = new HttpHeaders();


        if(adsKey == null)
            return ResponseEntity.status(209)
                .headers(responseHeaders)
                .body(RequestResponseInterface.getStatusMessage(209));
        

        

        String ck = "";
        
        try{




            Ads ads = adsRepository.findByAdsKey(adsKey);
    


    
            // 존재하지 않는 광고의 경우 208 에러
            if(ads == null)
                return ResponseEntity.status(208)
                    .headers(responseHeaders)
                    .body(RequestResponseInterface.getStatusMessage(208));

                
                



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


                    
                        




            // 클릭키 생성
            ck    =  keyBuilder.buildCK(adsKey, mediaKey, ptnCK, ptnPub, subPub, sP1, sP2, sP3, sP4, sP5, gaid, idfa);
                       

            
 
            // Redis에 클릭키 삽입
            redisUtil.putck(ck, adsKey, mediaKey, ads.getRedisIndex());




                    
            ads  = null;
    

            
    


        }catch(NoSuchElementException e){
            return ResponseEntity.status(201)
                .headers(responseHeaders)
                .body(RequestResponseInterface.getStatusMessage(201));
        }
                            

    

        return ResponseEntity.status(200)
                .headers(responseHeaders)
                .body(ck);
                
    }

}
