package com.gnm.adrunner.server.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.gnm.adrunner.server.entity.Ads;
import com.gnm.adrunner.server.entity.AdsMedia;
import com.gnm.adrunner.server.param.req.admin.RequestSaveAds1;
import com.gnm.adrunner.server.repo.AdsMediaRepository;
import com.gnm.adrunner.server.repo.MediaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdsMediaService")
public class AdsMediaService {

    @Autowired
    AdsMediaRepository adsMediaRepository;

    @Autowired
    MediaRepository mediaRepository;

    @Autowired
    LogAdsService logAdsService;

    @PersistenceContext
	private EntityManager entityManager;

    @Transactional
    public Integer saveAdsMedia(AdsMedia adsMedia){
        entityManager.persist(adsMedia);
        return (int)(long)adsMedia.getId();
    }

    @Transactional
    public void updateIsDayLimit(Boolean isDayLimit, String adsKey, String mediaKey){
        adsMediaRepository.updateIsDayLimit(isDayLimit, adsKey, mediaKey);
    }

    @Transactional
    public void modifyAdsMedia(
                Integer adid, 
                RequestSaveAds1 e, 
                Ads ads, 
                String remoteAddr, 
                String adsKey, 
                String adminId, 
                String mediaKey,
                Integer prevMediaCost,
                Integer prevMediaDailyCap) {
    
        String name             = e.getName();
        Integer mediaCost       = e.getCost();
        Integer mediaDailyCap   = e.getDailycap();            

        // 특정 매체사키에 대하여 매체사 단가와 데일리캡을 업데이트
        adsMediaRepository.updateMediaCostAndDailyCap(adsKey, mediaKey, mediaCost, mediaDailyCap);            

        // 매체사 단가 업데이트에 대해서 업데이트 로그
        if(!prevMediaCost.equals(mediaCost))
            logAdsService.insert(adsKey, remoteAddr, adminId, name+"의 매체사 단가", prevMediaCost.toString(), mediaCost.toString());   

        // 매체사 데일리캡 업데이트에 대해서 업데이트 로그
        if(!prevMediaDailyCap.equals(mediaDailyCap))
            logAdsService.insert(adsKey, remoteAddr, adminId, name+" 매체사 일일 한도", prevMediaDailyCap.toString(), mediaDailyCap.toString());   
    }

    public void deleteById(Integer amid) {
        adsMediaRepository.deleteById(amid);
    }

}
