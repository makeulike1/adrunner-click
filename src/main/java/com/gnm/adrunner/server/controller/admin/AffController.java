package com.gnm.adrunner.server.controller.admin;

import javax.servlet.http.HttpServletRequest;

import com.gnm.adrunner.config.GlobalConstant;
import com.gnm.adrunner.server.RequestResponseInterface;
import com.gnm.adrunner.server.entity.Aff;
import com.gnm.adrunner.server.entity.AffParam;
import com.gnm.adrunner.server.param.req.admin.RequestSaveAff;
import com.gnm.adrunner.server.param.req.admin.RequestSaveAffParam;
import com.gnm.adrunner.server.repo.AffRepository;
import com.gnm.adrunner.server.service.AdminLoginService;
import com.gnm.adrunner.server.service.AffService;
import com.gnm.adrunner.server.service.MemoryDataService;
import com.gnm.adrunner.util.timeBuilder;
import com.gnm.adrunner.server.service.AffParamService;
import com.gnm.adrunner.server.repo.AffParamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller 
@RequestMapping(path="/aff")
public class AffController extends RequestResponseInterface{
 
 
    @Autowired
    AdminLoginService adminLoginService;


    @Autowired
    AffService affService;


    @Autowired
    AffParamService affParamService;



    @Autowired
    AffRepository affRepository;


    @Autowired
    AffParamRepository affParamRepository;

    @Autowired
    MemoryDataService memoryDataService;
  
 

   // 제휴사 등록 
   @CrossOrigin(origins = "*")
   @PostMapping("/save") 
   public @ResponseBody ResponseEntity<String> list(@RequestBody RequestSaveAff req, HttpServletRequest request) {
      
  
      HttpHeaders responseHeaders = new HttpHeaders();
         
      // 유효하지 않은 토큰인 경우 203 에러 
      if(adminLoginService.chkToken(request.getHeader("token")) == 203){
            return ResponseEntity.status(203)
                  .headers(responseHeaders)
                  .body(getStatusMessage(203));
      }

      Aff aff = new Aff();
      aff.setIsDelete(false);
      aff.setName(req.getName());
      aff.setStatus(GlobalConstant.AFF_STATUS_READY);
      aff.setCreatetime(timeBuilder.getCurrentTime());
      aff.setDeletetime("1111-11-11 11:11:11");
      aff.setUpdatetime(timeBuilder.getCurrentTime());
          
      affRepository.save(aff);
  
      return ResponseEntity.status(200)
               .headers(responseHeaders)
               .body(gson.toJson(affService.listAff()));
   }
      

     
     // 제휴사 목록
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
                .body(gson.toJson(affService.listAff()));
     }



     // 제휴사: 연동완료된 제휴사 목록
     @CrossOrigin(origins = "*")
     @GetMapping("/list2") 
     public @ResponseBody ResponseEntity<String> list2(HttpServletRequest request) {
     
 
        HttpHeaders responseHeaders = new HttpHeaders();
        
         // 유효하지 않은 토큰인 경우 203 에러 
         if(adminLoginService.chkToken(request.getHeader("token")) == 203){
            return ResponseEntity.status(203)
                .headers(responseHeaders)
                .body(getStatusMessage(203));
         }
 
         return ResponseEntity.status(200)
                .headers(responseHeaders)
                .body(gson.toJson(affRepository.listStatusComplete()));
     }

 




     // 특정 제휴사 식별자에 대한 파라미터 목록
     @CrossOrigin(origins = "*")
     @GetMapping("/param/list/{affid}") 
     public @ResponseBody ResponseEntity<String> paramList(@PathVariable Integer affid, HttpServletRequest request) {
     
  
        HttpHeaders responseHeaders = new HttpHeaders();
 
         // 유효하지 않은 토큰인 경우 203 에러 
         if(adminLoginService.chkToken(request.getHeader("token")) == 203){
            return ResponseEntity.status(203)
                .headers(responseHeaders)
                .body(getStatusMessage(203));
         }

         return ResponseEntity.status(200)
                .headers(responseHeaders)
                .body(gson.toJson(affParamService.findByAffId(affid)));
     }



     // 특정 제휴사 파라미터 등록
     @CrossOrigin(origins = "*")
     @PostMapping("/param/save/{affid}") 
     public @ResponseBody ResponseEntity<String> paramSave(@PathVariable Integer affid, @RequestBody RequestSaveAffParam req, HttpServletRequest request) {
     
  
        HttpHeaders responseHeaders = new HttpHeaders();
 
         // 유효하지 않은 토큰인 경우 203 에러 
         if(adminLoginService.chkToken(request.getHeader("token")) == 203){
            return ResponseEntity.status(203)
                .headers(responseHeaders)
                .body(getStatusMessage(203));
         }

         AffParam ap = new AffParam();
         ap.setParamKey(req.getQueryKey());
         ap.setParamValue(req.getQueryValue());
         ap.setPassValue(req.getPassValue());
         ap.setParamType(req.getParamType());
         ap.setAffId(affid);
         affParamService.saveAffParam(ap);

         // 메모리 데이터 업데이트
         memoryDataService.addMemoryData("aff-param", ap.getId());
    
         return ResponseEntity.status(200)
                .headers(responseHeaders)
                .body(getStatusMessage(200));
     }




     // 특정 제휴사 파라미터 삭제
     @CrossOrigin(origins = "*")
     @DeleteMapping("/param/delete/{id}") 
     public @ResponseBody ResponseEntity<String> paramDelete(@PathVariable Integer id, HttpServletRequest request) {
     
  
        HttpHeaders responseHeaders = new HttpHeaders();
 
         // 유효하지 않은 토큰인 경우 203 에러 
         if(adminLoginService.chkToken(request.getHeader("token")) == 203){
            return ResponseEntity.status(203)
                .headers(responseHeaders)
                .body(getStatusMessage(203));
         }

         affParamService.deleteById(id);
 
         // 메모리 데이터 업데이트
         memoryDataService.deleteMemoryData("aff-param", id);

         return ResponseEntity.status(200)
                .headers(responseHeaders)
                .body(getStatusMessage(200));
     }
     
   


     // 특정 제휴사 삭제
     @CrossOrigin(origins = "*")
     @DeleteMapping("/delete/{id}") 
     public @ResponseBody ResponseEntity<String> delete(@PathVariable Integer id, HttpServletRequest request) {
     
  
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




     // 특정 제휴사 상태 변경
     @CrossOrigin(origins = "*")
     @PutMapping("/update-status/{id}/{status}") 
     public @ResponseBody ResponseEntity<String> updateStatus(
        @PathVariable Integer id, 
        @PathVariable Integer status,
        HttpServletRequest request) {
     
  
        HttpHeaders responseHeaders = new HttpHeaders();
 
         // 유효하지 않은 토큰인 경우 203 에러 
         if(adminLoginService.chkToken(request.getHeader("token")) == 203){
            return ResponseEntity.status(203)
                .headers(responseHeaders)
                .body(getStatusMessage(203));
         }
 

         if(status == 0){
            // 0이면 해당 제휴사에 대해서 연동 대기로 변경
            affService.updateStatusToBeDone(id);
            // 메모리 데이터 업데이트
            memoryDataService.updateMemoryData("aff", id);
         }else if(status == 1){
            // 0이면 해당 제휴사에 대해서 연동 완료로 변경
            affService.updateStatusDone(id);
            // 메모리 데이터 업데이트
            memoryDataService.updateMemoryData("aff", id);
         }

         return ResponseEntity.status(200)
                .headers(responseHeaders)
                .body(getStatusMessage(200));
     }
 
}