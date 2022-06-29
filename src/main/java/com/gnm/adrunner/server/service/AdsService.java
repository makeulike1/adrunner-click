package com.gnm.adrunner.server.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import java.text.ParseException;
 
import com.gnm.adrunner.server.entity.Aff;
import com.gnm.adrunner.config.GlobalConstant;
import com.gnm.adrunner.server.entity.Ads;
import com.gnm.adrunner.server.param.req.admin.RequestSaveAds;
import com.gnm.adrunner.server.param.res.admin.ResponseListAds1;
import com.gnm.adrunner.server.repo.AdsRepository;
import com.gnm.adrunner.server.repo.AffRepository;
import com.gnm.adrunner.util.redisUtil;
import com.gnm.adrunner.util.timeBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdsService")
public class AdsService {
  

    @Autowired
    LogAdsService logAdsService;

    @PersistenceContext
	private EntityManager entityManager;

    @Autowired
    PostbackService postbackService;

    @Autowired
    AffService affService;

    @Autowired
    AdsRepository adsRepository;

    @Autowired
    AffRepository affRepository;
 


    @Transactional
    public void updateAdsByStatus(Integer adsId, Integer status){
        // 광고 식별자에 대해서 광고 상태 변경
        adsRepository.updateAdsByStatus(adsId, status);
    }


    @Transactional
    public void modifyAds(Integer id,   RequestSaveAds req,       Ads prevData,   String adminIp,   String adsKey,  String adminId){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Ads> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Ads.class);
        Root<Ads> root = criteriaUpdate.from(Ads.class);



        
        //광고 항목이 변경되었을 경우 변경된 내용에 대해서 DB에 로깅 */
        Integer REQ_STATUS          = req.getStatus();
        Integer REQ_AFF             = req.getAff(); 
        Integer REQ_TYPE            = req.getType();
        Integer REQ_TARGETIMP       = req.getTargetImp();
        Boolean REQ_AUTOSTART       = req.getAutostart();
        Boolean REQ_AUTODOWN        = req.getAutodown();
        String  REQ_NAME            = req.getName();
        String  REQ_STARTDATE       = req.getStartdate();
        String  REQ_ENDDATE         = req.getEnddate();
        String  REQ_EVENTNAME       = req.getEventName();
        String  REQ_TRACKING_URL    = req.getTrackingUrl();
        String  REQ_LOOPBACK        = req.getLoopback();
        Boolean REQ_ISDAILY_CAP     = req.getIsDailyCap();
        String  REQ_ADVKEY          = req.getAdvKey();


        Integer REQ_COST1           = req.getCost1();
        Integer REQ_COST2           = req.getCost2();

        Integer PREV_STATUS         = prevData.getStatus();
        Integer PREV_AFF            = prevData.getAff();
        Integer PREV_TYPE           = prevData.getType();
        Integer PREV_TARGETIMP      = prevData.getTargetImp();
        Boolean PREV_AUTOSTART      = prevData.getAutostart();
        Boolean PREV_AUTODOWN       = prevData.getAutodown();
        Integer PREV_COST1          = prevData.getCost1();
        Integer PREV_COST2          = prevData.getCost2();
        String  PREV_LOOPBACK       = prevData.getLoopbackdate();
        String  PREV_ADVKEY         = prevData.getAdvKey();
        String  PREV_TRACKING_URL   = prevData.getTrackingUrl();
        String  PREV_NAME           = prevData.getName();
        String  PREV_STARTDATE      = prevData.getStartdate();
        String  PREV_ENDDATE        = prevData.getEnddate();
        String  PREV_EVENTNAME      = prevData.getEventName();
        Boolean PREV_ISDAILY_CAP    = prevData.getIsDailyCap();



        // Loopback 기간
        if(REQ_LOOPBACK != null){
            criteriaUpdate.set("loopbackdate",        REQ_LOOPBACK);    
            if(!REQ_LOOPBACK.equals(PREV_LOOPBACK)){
                logAdsService.insert(adsKey, adminIp,   adminId,    "loopbackdate", PREV_LOOPBACK.toString(), REQ_LOOPBACK.toString());
            }
        }



        

        // 광고 상태
        if(REQ_STATUS != null){
            criteriaUpdate.set("status",        REQ_STATUS);    
            if(!REQ_STATUS.equals(PREV_STATUS)){
                logAdsService.insert(adsKey, adminIp,   adminId,    "status", PREV_STATUS.toString(), REQ_STATUS.toString());
            }
        }
 
        
        // 제휴사 항목
        if(REQ_AFF != null){
            criteriaUpdate.set("aff",           REQ_AFF);    
            if(!REQ_AFF.equals(PREV_AFF)){
                String PREV_AFF_NAME    = affRepository.findNameById(PREV_AFF);
                String REQ_AFF_NAME     = affRepository.findNameById(REQ_AFF); 
                logAdsService.insert(adsKey, adminIp, adminId,    "aff",    PREV_AFF_NAME, REQ_AFF_NAME);
            }
        }


        // 자동 시작 여부
        if(REQ_AUTOSTART != null){
            criteriaUpdate.set("autostart",           REQ_AUTOSTART);    
            if(!REQ_AUTOSTART.equals(PREV_AUTOSTART))
                logAdsService.insert(adsKey, adminIp, adminId,    "autostart", PREV_AUTOSTART.toString(), REQ_AUTOSTART.toString());
        }




        // 자동 종료 여부
        if(REQ_AUTODOWN != null){
            criteriaUpdate.set("autodown",           REQ_AUTODOWN);    
            if(!REQ_AUTODOWN.equals(PREV_AUTODOWN))
                logAdsService.insert(adsKey, adminIp, adminId,    "autodown", PREV_AUTODOWN.toString(), REQ_AUTODOWN.toString());
        }



        // 광고 이름
        if(REQ_NAME != null){
            criteriaUpdate.set("name",          REQ_NAME);
            if(!REQ_NAME.equals(PREV_NAME)){
                logAdsService.insert(adsKey, adminIp, adminId,    "name", PREV_NAME, REQ_NAME);
            }
        }




        // 광고 구분
        if(REQ_TYPE != null){
            criteriaUpdate.set("type",          REQ_TYPE);
            if(!REQ_TYPE.equals(PREV_TYPE)){
                logAdsService.insert(adsKey, adminIp, adminId,    "type", PREV_TYPE.toString(), REQ_TYPE.toString());
            }
        }





        // 광고 시작시간
        if(REQ_STARTDATE != null){
            criteriaUpdate.set("startdate",     REQ_STARTDATE);
            if(!REQ_STARTDATE.equals(PREV_STARTDATE)){
                logAdsService.insert(adsKey, adminIp, adminId,    "startdate", PREV_STARTDATE, REQ_STARTDATE);
            }
        }





        // 광고 종료시간
        if(REQ_ENDDATE != null){
            criteriaUpdate.set("enddate",       REQ_ENDDATE);
            if(!REQ_ENDDATE.equals(PREV_ENDDATE)){
                logAdsService.insert(adsKey, adminIp, adminId,    "enddate", PREV_ENDDATE, REQ_ENDDATE);
            }
            
        }




        // 데일리캡 여부
        if(REQ_ISDAILY_CAP != null){
            criteriaUpdate.set("isDailyCap",    REQ_ISDAILY_CAP);
            if(REQ_ISDAILY_CAP != PREV_ISDAILY_CAP){
                logAdsService.insert(adsKey, adminIp, adminId,    "isDailyCap", PREV_ISDAILY_CAP.toString(), REQ_ISDAILY_CAP.toString());
            }
        }





        // 광고 목표한도
        if(REQ_TARGETIMP != null){
            criteriaUpdate.set("targetImp",     REQ_TARGETIMP);
            if(!REQ_TARGETIMP.equals(PREV_TARGETIMP)){
                logAdsService.insert(adsKey, adminIp, adminId,    "targetImp", PREV_TARGETIMP.toString(), REQ_TARGETIMP.toString());
            }
        }
        


        // 이벤트 이름
        if(REQ_EVENTNAME != null){
            criteriaUpdate.set("eventName",     REQ_EVENTNAME);
            if(!REQ_EVENTNAME.equals(PREV_EVENTNAME)){
                logAdsService.insert(adsKey, adminIp, adminId,    "eventName", PREV_EVENTNAME, REQ_EVENTNAME);
            }
        }




        // 광고주 단가
        if(REQ_COST1 != null){
            criteriaUpdate.set("cost1",     REQ_COST1);
            if(!REQ_COST1.equals(PREV_COST1)){
                logAdsService.insert(adsKey, adminIp, adminId,    "cost1", PREV_COST1.toString(), REQ_COST1.toString());
            }
        }


        
        if(REQ_COST2 != null){
            criteriaUpdate.set("cost2",     REQ_COST2);
            if(!REQ_COST2.equals(PREV_COST2)){
                logAdsService.insert(adsKey, adminIp, adminId,    "cost2", PREV_COST2.toString(), REQ_COST2.toString());
            }
        }



        // 트래킹 URL 
        if(REQ_TRACKING_URL != null){
            criteriaUpdate.set("trackingUrl",  REQ_TRACKING_URL);
            if(!REQ_TRACKING_URL.equals(PREV_TRACKING_URL)){
                logAdsService.insert(adsKey, adminIp, adminId,    "trackingUrl", PREV_TRACKING_URL.toString(), REQ_TRACKING_URL.toString());
            }
        }



        // 광고주
        if(REQ_ADVKEY != null){
            criteriaUpdate.set("advKey",  REQ_ADVKEY);
            if(!REQ_ADVKEY.equals(PREV_ADVKEY)){
                logAdsService.insert(adsKey, adminIp, adminId,    "advKey", PREV_ADVKEY.toString(), REQ_ADVKEY.toString());
            }
        }
        
        

        criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), id));
        entityManager.createQuery(criteriaUpdate).executeUpdate();
    }

    



    @Transactional
    public void deleteAds(Integer id){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Ads> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Ads.class);
        Root<Ads> root = criteriaUpdate.from(Ads.class);
        criteriaUpdate.set("isDelete",      true);
        criteriaUpdate.set("deletetime",    timeBuilder.getCurrentTime());
        criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), id));
        entityManager.createQuery(criteriaUpdate).executeUpdate();
    }





    @Transactional
    public Integer saveAds(Ads ads){
        entityManager.persist(ads);
        return (int)(long)ads.getId();
    }



    

    //광고 목록 조회
    public List<ResponseListAds1> listAds(Integer limit, Integer offset, String name, Integer status) throws ParseException{
        
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ads> query = criteriaBuilder.createQuery(Ads.class);
        Root<Ads> ads = query.from(Ads.class);
        query.select(ads);
        query.orderBy(criteriaBuilder.desc(ads.get("id")));

        List<Predicate> predicates = buildSelectPredicate(status, name, ads, criteriaBuilder);
        query.where(predicates.toArray(new Predicate[]{}));
        
        
        Iterable<Ads> list = entityManager.createQuery(query).setFirstResult(offset).setMaxResults(limit).getResultList();


 

        
        //제휴사 이름 표출을 위해서 제휴사 목록 전체 조회
        Iterable<Aff> affList = affService.listAff();



 





        //광고 목록 조회시 클릭수, 전환 수, 전환율 추가 조회
        List<ResponseListAds1> result = new ArrayList<ResponseListAds1>();
        for(Ads it : list){
            ResponseListAds1 record = new ResponseListAds1();
            record.setAds(it);






            //Redis 에서 광고에 대한 일별 클릭 수 조회
            Integer clickCount = redisUtil.getCkCount(it.getAdsKey()+"*", it.getRedisIndex());

            






            //제휴사 식별자를 참조하여 제휴사 이름 조회
            for(Aff aff : affList){
                if(it.getAff() == aff.getId())
                    record.setAffName(aff.getName());
            }




            //포스트백 테이블에서 광고 식별자에 해당하는 포스트백 수 조회
            Integer conversion = postbackService.countTotalPostbackByAdsKey(it.getAdsKey());







            //클릭을 전환 수로 나눈 값으로 전환율 계산
            Double  conversionRate = 0.0;
            if((clickCount != 0 ) && (conversion != 0))
                conversionRate = conversion/(double)clickCount;


            conversionRate *= 100;


            record.setClick(clickCount); 
            record.setConversion(conversion);
            record.setConversionRate(conversionRate);
            
            result.add(record);
        }

        return result;
    }





    public Integer countTotalAds(String name, Integer status){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<Ads> ads = query.from(Ads.class);
        query.select(criteriaBuilder.count(ads));

        List<Predicate> predicates = buildSelectPredicate(status, name, ads, criteriaBuilder);
        query.where(predicates.toArray(new Predicate[]{}));

        return (int) (long) entityManager.createQuery(query).getSingleResult();
        
    }


    public Integer countTotalLiveAds(){

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<Ads> ads = query.from(Ads.class);
        query.select(criteriaBuilder.count(ads));

        List<Predicate> predicates = new ArrayList<Predicate>();     
        predicates.add(criteriaBuilder.equal(ads.get("status"), GlobalConstant.ADS_STATUS_LIVE));
        predicates.add(criteriaBuilder.equal(ads.get("isDelete"), false));
        query.where(predicates.toArray(new Predicate[]{}));

        return (int) (long) entityManager.createQuery(query).getSingleResult();

    }



    public Ads findById(Integer adsId) {
        return adsRepository.findByID(adsId);
    }

    public Ads findByAdsKey(String adsKey) {
        return adsRepository.findByAdsKey(adsKey);
    }


 



    public Iterable<Integer> getIdList() {
        return adsRepository.listAllId();
    }



    public Integer getAdTypeByAdsKey(String adsKey) {
        return adsRepository.getAdType(adsKey);
    }


    public List<Predicate> buildSelectPredicate(Integer status, String name, Root<Ads> root, CriteriaBuilder cb){
        List<Predicate> predicates = new ArrayList<Predicate>();     
        predicates.add(cb.equal(root.get("isDelete"), false));

        // -1일때는 광고 종료된 목록은 보여지지 않도록
        if(status == -1)   
            predicates.add(cb.notEqual(root.get("status"), GlobalConstant.ADS_STATUS_DISMISS));
        else predicates.add(cb.equal(root.get("status"), status));
        if((name != null) && (!name.equals(""))){
            predicates.add(cb.like(root.get("name"), '%' +name+ '%'));
        }

        return predicates;
    }
}
