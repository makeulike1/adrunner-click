package com.gnm.adrunner.server.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnm.adrunner.server.entity.LogAds;
import com.gnm.adrunner.server.repo.LogAdsRepository;
import com.gnm.adrunner.util.timeBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("LogAdsService")
public class LogAdsService {


    @PersistenceContext
	private EntityManager entityManager;


    @Autowired
    LogAdsRepository logAdsRepository;

    public void insert(String adsKey, String clientIp, String adminId, String updatedKey, String prevValue, String updatedValue) {

        LogAds la = new LogAds();

        String CURRENT_TIME = timeBuilder.getCurrentTime();

        la.setAdsKey(adsKey);
        la.setClientIp(clientIp);
        la.setUpdatedKey(updatedKey);
        la.setPrevValue(prevValue);
        la.setUpdatedValue(updatedValue);
        la.setCreatetime(CURRENT_TIME);
        la.setAdminId(adminId);

        logAdsRepository.save(la);
        
    }
 
 
}
