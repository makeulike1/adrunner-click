package com.gnm.adrunner.server.controller.admin;
 
import com.gnm.adrunner.server.RequestResponseInterface;
import com.gnm.adrunner.server.entity.Adv;
import com.gnm.adrunner.server.param.req.admin.RequestSaveAdv;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

import com.gnm.adrunner.server.repo.AdsMediaRepository;
import com.gnm.adrunner.server.repo.AdvRepository;
import com.gnm.adrunner.server.repo.MediaRepository;
import com.gnm.adrunner.server.service.AdminLoginService;
import com.gnm.adrunner.server.service.MemoryDataService;
import com.gnm.adrunner.server.service.AdvService;
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
@RequestMapping(path="/adv")
public class AdvController extends RequestResponseInterface{
    
    @Autowired
    AdminLoginService adminLoginService; 

    @Autowired
    AdsMediaRepository adsMediaRepository;

    @Autowired
    MediaRepository mediaRepository;

    @Autowired
    MemoryDataService memoryDataService;

    @Autowired
    AdvRepository advRepository;

    @Autowired
    AdvService advService;
 

    // 광고주 등록
    @CrossOrigin(origins = "*")
    @PostMapping("/save") 
    public @ResponseBody ResponseEntity<String> add(
        @RequestBody RequestSaveAdv req, HttpServletRequest request) {
         
        HttpHeaders responseHeaders = new HttpHeaders();
    
    
        // 유효하지 않은 토큰인 경우 203 에러 
        if(adminLoginService.chkToken(request.getHeader("token")) == 203){
            return ResponseEntity.status(203)
                .headers(responseHeaders)
                .body(getStatusMessage(203));
        } 


        Adv adv = new Adv();
        adv.setContact(req.getContact());
        adv.setName(req.getName());
        adv.setIsDelete(false);
        adv.setDeletetime("1111-11-11 11:11:11");
        adv.setUpdatetime(timeBuilder.getCurrentTime());
        adv.setCreatetime(timeBuilder.getCurrentTime());



        Integer advId   = advService.saveAdv(adv);
        String  advKey  = keyBuilder.buildIdentifier(advId);

        advRepository.updateAdvKey(advKey, advId);

 
        return ResponseEntity.status(200)
                .headers(responseHeaders)
                .body(getStatusMessage(200));
    }



    // 광고주 목록
    @CrossOrigin(origins = "*")
    @GetMapping("/list") 
    public @ResponseBody ResponseEntity<String> list(HttpServletRequest request) {
         
        HttpHeaders responseHeaders = new HttpHeaders();
    
        // 유효하지 않은 토큰인 경우 203 에러 
        if(adminLoginService.chkToken(request.getHeader("token")) == 203){
            return ResponseEntity.status(203)
                .headers(responseHeaders)
                .body(getStatusMessage(203));
        } 


        return ResponseEntity.status(200)
                .headers(responseHeaders)
                .body(gson.toJson(advRepository.listAll()));
    }


    // 광고주 검색
    @CrossOrigin(origins = "*")
    @GetMapping("/find") 
    public @ResponseBody ResponseEntity<String> find(@RequestParam(value="name", required=false) String name, HttpServletRequest request) {
         
        HttpHeaders responseHeaders = new HttpHeaders();
    
        // 유효하지 않은 토큰인 경우 203 에러 
        if(adminLoginService.chkToken(request.getHeader("token")) == 203){
            return ResponseEntity.status(203)
                .headers(responseHeaders)
                .body(getStatusMessage(203));
        } 


        return ResponseEntity.status(200)
                .headers(responseHeaders)
                .body(gson.toJson(advService.findByName(name)));
    }


    
    // 광고주 삭제
    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete/{id}") 
    public @ResponseBody ResponseEntity<String> delete(
        @PathVariable Integer id, HttpServletRequest request) {
         
        HttpHeaders responseHeaders = new HttpHeaders();
    
        // 유효하지 않은 토큰인 경우 203 에러 
        if(adminLoginService.chkToken(request.getHeader("token")) == 203){
            return ResponseEntity.status(203)
                .headers(responseHeaders)
                .body(getStatusMessage(203));
        } 


        // 광고주 삭제
        advRepository.deleteByAdvId(id);
 
    
        return ResponseEntity.status(200)
                .headers(responseHeaders)
                .body(getStatusMessage(200));
    }

}
