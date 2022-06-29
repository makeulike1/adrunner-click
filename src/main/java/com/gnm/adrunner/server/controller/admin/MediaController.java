package com.gnm.adrunner.server.controller.admin;
 
import com.gnm.adrunner.server.RequestResponseInterface;
import com.gnm.adrunner.server.entity.Media;
import com.gnm.adrunner.server.param.req.admin.RequestSaveMedia;

import org.springframework.stereotype.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gnm.adrunner.server.repo.AdsMediaRepository;
import com.gnm.adrunner.server.repo.MediaRepository;
import com.gnm.adrunner.server.service.AdminLoginService;
import com.gnm.adrunner.server.service.MediaService;
import com.gnm.adrunner.server.service.MemoryDataService;
import com.gnm.adrunner.util.keyBuilder;
import com.gnm.adrunner.util.timeBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller 
@RequestMapping(path="/media")
public class MediaController extends RequestResponseInterface{
    
    @Autowired
    AdminLoginService adminLoginService; 


    @Autowired
    AdsMediaRepository adsMediaRepository;
    
    @Autowired
    MediaRepository mediaRepository;

    @Autowired
    MediaService mediaService;

    @Autowired
    MemoryDataService memoryDataService;
 

    // 매체사 목록  
    @CrossOrigin(origins = "*")
    @GetMapping("/list")
    public @ResponseBody ResponseEntity<String> list(
        @RequestParam(value="ads_key", required=false)String adsKey, HttpServletRequest request) {
        
        
        HttpHeaders responseHeaders = new HttpHeaders();
        
        // 유효하지 않은 토큰인 경우 203 에러 
        if(adminLoginService.chkToken(request.getHeader("token")) == 203){
            return ResponseEntity.status(203)
                .headers(responseHeaders)
                .body(gson.toJson(getStatusMessage(203)));
        }

        
        List<Media> mediaList = mediaRepository.listAll();

        if(adsKey != null){            
            
            String mediaKey[] = adsMediaRepository.mediaKeyListByAdsKey(adsKey);
            
            // 특정 광고키에 대해서 연동된 매체사는 제외하여 내려줌
            for(String e1 : mediaKey){

                for(Media e : mediaList){
                    if(e.getMediaKey().equals(e1)){
                        mediaList.remove(e);
                        break;
                    }
                } 
            }

        }
        
        return ResponseEntity.status(200)
                .headers(responseHeaders)
                .body(gson.toJson(mediaList));
    }




    // 매체사 등록
    @CrossOrigin(origins = "*")
    @PostMapping("/save") 
    public @ResponseBody ResponseEntity<String> add(
        @RequestBody RequestSaveMedia req, HttpServletRequest request) {
         
        HttpHeaders responseHeaders = new HttpHeaders();
    
    
        // 유효하지 않은 토큰인 경우 203 에러 
        if(adminLoginService.chkToken(request.getHeader("token")) == 203){
            return ResponseEntity.status(203)
                .headers(responseHeaders)
                .body(getStatusMessage(203));
        } 

        
        Media m = new Media();
        m.setName(req.getName());
        m.setMediaKey(""); 
        m.setIsDelete(false);
        m.setCreatetime(timeBuilder.getCurrentTime());

        
        Integer mediaId = mediaService.saveMedia(m);

        // 매체사 키는 [매체사번호][5자리의 대문자 난수]
        String mediaKey = keyBuilder.buildIdentifier(mediaId);

        mediaService.updateMediaKey(mediaKey.toUpperCase(), mediaId);

        // 메모리 데이터 업데이트
        memoryDataService.addMemoryData("media", mediaId);
 
        return ResponseEntity.status(200)
                .headers(responseHeaders)
                .body(mediaKey);
    }



    
    // 매체사 삭제
    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete/{mediaid}") 
    public @ResponseBody ResponseEntity<String> delete(
        @PathVariable Integer mediaid, HttpServletRequest request) {
         
        HttpHeaders responseHeaders = new HttpHeaders();
    
    
        // 유효하지 않은 토큰인 경우 203 에러 
        if(adminLoginService.chkToken(request.getHeader("token")) == 203){
            return ResponseEntity.status(203)
                .headers(responseHeaders)
                .body(getStatusMessage(203));
        } 
 
    
        return ResponseEntity.status(200)
                .headers(responseHeaders)
                .body(getStatusMessage(200));
    }

}
