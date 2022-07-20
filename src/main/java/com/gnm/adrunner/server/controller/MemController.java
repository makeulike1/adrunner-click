package com.gnm.adrunner.server.controller;

import javax.servlet.http.HttpServletRequest;

import com.gnm.adrunner.config.MemoryData;
import com.gnm.adrunner.server.RequestResponseInterface;
import com.gnm.adrunner.server.entity.Ads;
import com.gnm.adrunner.server.entity.AdsMedia;
import com.gnm.adrunner.server.entity.Aff;
import com.gnm.adrunner.server.entity.AffParam;
import com.gnm.adrunner.server.entity.Media;

import com.gnm.adrunner.server.repo.AdsMediaRepository;
import com.gnm.adrunner.server.repo.AdsRepository;
import com.gnm.adrunner.server.repo.AffParamRepository;
import com.gnm.adrunner.server.repo.AffRepository;
import com.gnm.adrunner.server.repo.MediaRepository;
import com.gnm.adrunner.server.repo.ServerInstanceRepository;
import com.gnm.adrunner.util.timeBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



// 현재 활성화되어 있는 스프링 부트의 메모리 변수에 접근, 혹은 데이터 업데이트 
@Controller 
@RequestMapping(path="/mem")
public class MemController extends RequestResponseInterface{


    @Autowired
    AffParamRepository              affParamRepository;

    
    @Autowired
    AdsRepository                   adsRepository;


    @Autowired
    AffRepository                   affRepository;

    
    @Autowired
    MediaRepository                 mediaRepository;



    @Autowired
    AdsMediaRepository              adsMediaRepository;



    @Autowired
    ServerInstanceRepository        serverInstanceRepository;



    // 메모리 데이터의 제휴사 파라미터 목록 조회
    @GetMapping("/list/aff-param")
    public @ResponseBody ResponseEntity<String> affParamList(HttpServletRequest request) {
    
        HttpHeaders responseHeaders = new HttpHeaders();
 
        return ResponseEntity.status(200)
                .headers(responseHeaders)
                .body(gson.toJson(MemoryData.affParamList));
    }



    // 메모리 데이터의 광고 목록 조회
    @GetMapping("/list/ads")
    public @ResponseBody ResponseEntity<String> adsList(HttpServletRequest request) {
    
        HttpHeaders responseHeaders = new HttpHeaders();
 
        return ResponseEntity.status(200)
                .headers(responseHeaders)
                .body(gson.toJson(MemoryData.adsList));
    }



    // 메모리 데이터의 제휴사 목록 조회
    @GetMapping("/list/aff")
    public @ResponseBody ResponseEntity<String> affList(HttpServletRequest request) {
    
        HttpHeaders responseHeaders = new HttpHeaders();
 
        return ResponseEntity.status(200)
                .headers(responseHeaders)
                .body(gson.toJson(MemoryData.affList));
    }




    // 메모리 데이터의 매체 목록 조회
    @GetMapping("/list/media")
    public @ResponseBody ResponseEntity<String> mediaList(HttpServletRequest request) {
    
        HttpHeaders responseHeaders = new HttpHeaders();
 
        return ResponseEntity.status(200)
                .headers(responseHeaders)
                .body(gson.toJson(MemoryData.mediaList));
    }





    // 메모리 데이터 추가
    @GetMapping("/add")
    public @ResponseBody ResponseEntity<String> add(
        @RequestParam(value="type",   required=true)String type,
        @RequestParam(value="id",   required=true)Integer id, HttpServletRequest request) {
        

        HttpHeaders responseHeaders = new HttpHeaders();

        
        switch(type){

            // 제휴사 파라미터
            case "aff-param":{
                AffParam e1 = affParamRepository.findByID(id);
                MemoryData.affParamList.add(e1);
                System.out.println(timeBuilder.getCurrentTime() + "  MEMORY DATA NEWLY INSERTED : [AFF_PARAM][ID : "+id+"]");
                break;
            }

            // 광고 
            case "ads":{
                Ads e1 = adsRepository.findByID(id);
                MemoryData.adsList.add(e1);
                System.out.println(timeBuilder.getCurrentTime() + "  MEMORY DATA NEWLY INSERTED : [ADS][ID : "+id+"]");
                break;
            }
            


            // 광고 : 매체사
            case "ads-media":{
                AdsMedia e1 = adsMediaRepository.findByID(id);
                MemoryData.adsMediaList.add(e1);
                System.out.println(timeBuilder.getCurrentTime() + "  MEMORY DATA NEWLY INSERTED : [ADS_MEDIA][ID : "+id+"]");
                break;
            }



            // 매체사
            case "media":{
                Media e1 = mediaRepository.findByID(id);
                MemoryData.mediaList.add(e1);
                System.out.println(timeBuilder.getCurrentTime() + "  MEMORY DATA NEWLY INSERTED : [MEDIA][ID : "+id+"]");
                break;
                
            }


            // 제휴사
            case "aff":{
                Aff e1 = affRepository.findByID(id);
                MemoryData.affList.add(e1);
                System.out.println(timeBuilder.getCurrentTime() + "  MEMORY DATA NEWLY INSERTED : [AFF][ID : "+id+"]");
                break;
            }

        }

 
        return ResponseEntity.status(200)
                .headers(responseHeaders)
                .body(getStatusMessage(200));
    }




     // 메모리 데이터의 특정 데이터 삭제
     @GetMapping("/delete")
     public @ResponseBody ResponseEntity<String> delete(
         @RequestParam(value="type",   required=true)String type,
         @RequestParam(value="id",   required=true)Integer id, HttpServletRequest request) {
         
 
         HttpHeaders responseHeaders = new HttpHeaders();
 
         
         switch(type){
 
             // 제휴사 파라미터
             case "aff-param":{
                for(AffParam e : MemoryData.affParamList){
                    if(e.getId().equals(id)){
                        MemoryData.affParamList.remove(e);
                        System.out.println(timeBuilder.getCurrentTime() + "  MEMORY DATA DELETED : [AFF_PARAM][ID : "+id+"]");
                        break;
                    }
                }
                break;
             }
 
             // 광고 
             case "ads":{
                 for(Ads e : MemoryData.adsList){
                    if(e.getId().equals(id)){
                        MemoryData.adsList.remove(e);
                        System.out.println(timeBuilder.getCurrentTime() + "  MEMORY DATA DELETED : [ADS][ID : "+id+"]");
                        break;
                    }
                 }
                 break;
             }



             // 광고 매체사
             case "ads-media":{
                 for(AdsMedia e : MemoryData.adsMediaList){
                     if(e.getId().equals(id)){
                         MemoryData.adsMediaList.remove(e);
                         System.out.println(timeBuilder.getCurrentTime() + "  MEMORY DATA DELETED : [ADS_MEDIA][ID : "+id+"]");
                         break;
                     }
                 }
             }


 

             
             // 매체사
             case "media":{
                 for(Media e : MemoryData.mediaList){
                    if(e.getId().equals(id)){
                        MemoryData.mediaList.remove(e);
                        System.out.println(timeBuilder.getCurrentTime() + "  MEMORY DATA DELETED : [MEDIA][ID : "+id+"]");
                        break;
                    }
                 }
                 break;
                 
             }



             // 제휴사
             case "aff":{
                 for(Aff e : MemoryData.affList){
                     if(e.getId().equals(id)){
                         MemoryData.affList.remove(e);
                         System.out.println(timeBuilder.getCurrentTime() + "  MEMORY DATA DELETED : [AFF][ID : "+id+"]");
                     }
                 }
                 break;
             }
 
 
         }
 
  
         return ResponseEntity.status(200)
                 .headers(responseHeaders)
                 .body(getStatusMessage(200));
     }






    // 메모리 데이터 리스트의 특정 엘리먼트 신규로 업데이트
    @GetMapping("/refresh")
    public @ResponseBody ResponseEntity<String> find(
        @RequestParam(value="type",   required=true)String type,
        @RequestParam(value="id",   required=true)Integer id, HttpServletRequest request) {
        

        HttpHeaders responseHeaders = new HttpHeaders();

        
        switch(type){

            // 제휴사 파라미터
            case "aff-param":{
                AffParam e1 = affParamRepository.findByID(id);
                for(AffParam e2 : MemoryData.affParamList){
                    if(e2.getId().equals(id)){
                        Integer indexOfItem = MemoryData.affParamList.indexOf(e2);
                        MemoryData.affParamList.set(indexOfItem, e1);
                        System.out.println(timeBuilder.getCurrentTime() + "  MEMORY DATA UPDATED : [AFF_PARAM][ID : "+id+"]");
                    }
                }
                break;
            }
            

            // 광고 
            case "ads":{
                Ads e1 = adsRepository.findByID(id);
                for(Ads e2 : MemoryData.adsList){
                    if(e2.getId().equals(id)){
                        Integer indexOfItem = MemoryData.adsList.indexOf(e2);
                        MemoryData.adsList.set(indexOfItem, e1);
                        System.out.println(timeBuilder.getCurrentTime() + "  MEMORY DATA UPDATED : [ADS][ID : "+id+"]");
                    }
                }
                break;
            }



            // 광고 : 매체사
            case "ads-media":{
                AdsMedia e1 = adsMediaRepository.findByID(id);
                for(AdsMedia e2 : MemoryData.adsMediaList){
                    if(e2.getId().equals(id)){
                        Integer indexOfItem = MemoryData.adsMediaList.indexOf(e2);
                        MemoryData.adsMediaList.set(indexOfItem, e1);
                        System.out.println(timeBuilder.getCurrentTime() + "  MEMORY DATA UPDATED : [ADS_MEDIA][ID : "+id+"]");
                    }
                }

            }



            // 매체사
            case "media":{
                Media e1 = mediaRepository.findByID(id);
                for(Media e2 : MemoryData.mediaList){
                    if(e2.getId().equals(id)){
                        Integer indexOfItem = MemoryData.mediaList.indexOf(e2);
                        MemoryData.mediaList.set(indexOfItem, e1);
                        System.out.println(timeBuilder.getCurrentTime() + "  MEMORY DATA UPDATED : [MEDIA][ID : "+id+"]");
                    }
                }
                break;
                
            }
            

            // 제휴사
            case "aff":{   
                Aff e1 = affRepository.findByID(id);
                for(Aff e2 : MemoryData.affList){
                    if(e2.getId().equals(id)){
                        Integer indexOfItem = MemoryData.affList.indexOf(e2);
                        MemoryData.affList.set(indexOfItem, e1);
                        System.out.println(timeBuilder.getCurrentTime() + "  MEMORY DATA UPDATED : [AFF][ID : "+id+"]");
                    }
                }
                break;
            }


        }

 
        return ResponseEntity.status(200)
                .headers(responseHeaders)
                .body(getStatusMessage(200));
    }



    
}

