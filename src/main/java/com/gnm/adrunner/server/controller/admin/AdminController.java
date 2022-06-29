package com.gnm.adrunner.server.controller.admin;

import java.text.ParseException;
 
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.gnm.adrunner.server.RequestResponseInterface;
import com.gnm.adrunner.server.entity.Admin;
import com.gnm.adrunner.server.entity.AdminLogin;
import com.gnm.adrunner.server.entity.Ads;
import com.gnm.adrunner.server.param.req.admin.RequestLogin;
import com.gnm.adrunner.server.param.req.admin.RequestRePassword;
import com.gnm.adrunner.server.param.req.admin.RequestSignup;
import com.gnm.adrunner.server.repo.AdsRepository;
import com.gnm.adrunner.server.repo.AdminRepository;
import com.gnm.adrunner.server.repo.AffParamRepository;
import com.gnm.adrunner.server.repo.AffRepository;
import com.gnm.adrunner.server.repo.MediaRepository;
import com.gnm.adrunner.server.repo.ServerInstanceRepository;
import com.gnm.adrunner.server.service.AdminLoginService;
import com.gnm.adrunner.server.service.SchedulerService;
import com.gnm.adrunner.util.aes256;
import com.gnm.adrunner.util.redisUtil;
import com.gnm.adrunner.util.timeBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller 
@RequestMapping(path="/admin")
public class AdminController extends RequestResponseInterface{


    @Autowired
    AdminLoginService               adminLoginService; 

    @Autowired
    SchedulerService                schedulerService;


    @Autowired
    AffParamRepository              affParamRepository;

    
    @Autowired
    AdsRepository                   adsRepository;


    @Autowired
    AffRepository                   affRepository;

    
    @Autowired
    MediaRepository                 mediaRepository;


    @Autowired
    ServerInstanceRepository        serverInstanceRepository;


    @Autowired
    AdminRepository                 adminRepository;



    // 회원 가입
    @CrossOrigin(origins = "*")
    @PostMapping("/signup") 
    public @ResponseBody ResponseEntity<String> signup(@RequestBody RequestSignup req, HttpServletRequest request) throws Exception {

        HttpHeaders responseHeaders = new HttpHeaders();

        String password = aes256.encrypt(req.getPassword());

        Admin adm = new Admin();
        adm.setAdmin_id(req.getId());
        adm.setPassword(password);
        adm.setCreatetime(timeBuilder.getCurrentTime());
        adm.setUpdatetime(timeBuilder.getCurrentTime());
        
        adminRepository.save(adm);
        
        return ResponseEntity.status(200)
            .headers(responseHeaders)
            .body(getStatusMessage(200));
    }




    // 비밀번호 변경
    @CrossOrigin(origins = "*")
    @PostMapping("/re-password") 
    public @ResponseBody ResponseEntity<String> repassword(@RequestBody RequestRePassword req, HttpServletRequest request) throws Exception {

        HttpHeaders responseHeaders = new HttpHeaders();


        Admin adm = adminRepository.findByAdminId(req.getId());

        // 관리자 비밀번호가 일치하지 않을 경우 202
        if(!req.getPassword().equals(aes256.decrypt(adm.getPassword()))){
            return ResponseEntity.status(202)
                .headers(responseHeaders)
                .body(getStatusMessage(202));
        }

        String password = aes256.encrypt(req.getNewpassword());

        adminRepository.updatePassword(password, adm.getId());

    
        return ResponseEntity.status(200)
            .headers(responseHeaders)
            .body(getStatusMessage(200));
    }



    




    // 토큰 체크
    @CrossOrigin(origins = "*")
    @GetMapping("/check-token") 
    public @ResponseBody ResponseEntity<String> login(@RequestParam(value="token", required=true) String token,  HttpServletRequest request) {

        HttpHeaders responseHeaders = new HttpHeaders();

        Integer statusCode = adminLoginService.chkToken(token);
        
        return ResponseEntity.status(statusCode)
                .headers(responseHeaders)
                .body(getStatusMessage(statusCode));
    }






    // 테스트 : 리포트 신규 삽입
    @CrossOrigin(origins = "*")
    @GetMapping("/test") 
    public @ResponseBody ResponseEntity<String>  test(HttpServletRequest request) throws ParseException {
        
        HttpHeaders responseHeaders = new HttpHeaders();
        
        schedulerService.updateRptDay(true);
        
        return ResponseEntity.status(200)
                .headers(responseHeaders)
                .body(getStatusMessage(200));
    }

    

    
    // 테스트 : 클릭 찾기
    @CrossOrigin(origins = "*")
    @GetMapping("/test1") 
    public @ResponseBody ResponseEntity<String>  test1(@RequestParam(value="click", required=true) String ck, @RequestParam(value="ads_key", required=true) String adsKey, HttpServletRequest request) throws ParseException {
        
        HttpHeaders responseHeaders = new HttpHeaders();
        
        Ads ads = adsRepository.findByAdsKey(adsKey);
        
        String message = "";
        if(redisUtil.findck(adsKey, ck, ads.getRedisIndex()))
            message = "success";
        else message = "fail";
        
        return ResponseEntity.status(200)
                .headers(responseHeaders)
                .body(message); 
    }


    // 관리자 로그인
    @CrossOrigin(origins = "*")
    @PostMapping("/login") 
    public @ResponseBody ResponseEntity<String> login(@RequestBody RequestLogin req, HttpServletRequest request) throws Exception {


        HttpHeaders responseHeaders = new HttpHeaders();



        Admin adm = adminRepository.findByAdminId(req.getId());



        // 관리자 비밀번호가 일치하지 않을 경우 202
        if(!req.getPassword().equals(aes256.decrypt(adm.getPassword()))){
            return ResponseEntity.status(202)
                .headers(responseHeaders)
                .body(getStatusMessage(202));
        }

            

        String token = UUID.randomUUID().toString();

 

        // 로그인 후에 토큰 발급하여 갱신 
        AdminLogin log = new AdminLogin();
        log.setToken(token);
        log.setCreatetime(timeBuilder.getCurrentTime());
        log.setClientIp(request.getRemoteAddr());
        log.setAdminId(adm.getAdmin_id());

        adminLoginService.saveLog(log);


        
        return ResponseEntity.status(200)
            .headers(responseHeaders)
            .body(token);
    }




    
}

