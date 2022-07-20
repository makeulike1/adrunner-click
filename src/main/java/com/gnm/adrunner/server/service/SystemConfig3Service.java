package com.gnm.adrunner.server.service;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.gnm.adrunner.server.entity.SystemConfig3;
 
import org.springframework.stereotype.Service;

@Service("SystemConfig3Service")
public class SystemConfig3Service {
    
        
    @PersistenceContext
	private EntityManager entityManager;

 
    @Transactional
    public void resetAds(Integer adsRedisGroup, Integer adsRedisDB){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<SystemConfig3> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(SystemConfig3.class);
        Root<SystemConfig3> root = criteriaUpdate.from(SystemConfig3.class);

        String col = "";

        switch(adsRedisDB){
            case 0:col="db0";break;
            case 1:col="db1";break;
            case 2:col="db2";break;
            case 3:col="db3";break;
            case 4:col="db4";break;
            case 5:col="db5";break;
            case 6:col="db6";break;
            case 7:col="db7";break;
            case 8:col="db8";break;
            case 9:col="db9";break;
            case 10:col="db10";break;
            case 11:col="db11";break;
            case 12:col="db12";break;
            case 13:col="db13";break;
            case 14:col="db14";break;
            case 15:col="db15";break;   
        }

        criteriaUpdate.set(col, -1);
        criteriaUpdate.where(criteriaBuilder.equal(root.get("groupid"), adsRedisGroup));
        entityManager.createQuery(criteriaUpdate).executeUpdate();
    }


    @Transactional
    public void insertAds(Integer adsRedisGroup, Integer adsRedisDB, Integer adid){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<SystemConfig3> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(SystemConfig3.class);
        Root<SystemConfig3> root = criteriaUpdate.from(SystemConfig3.class);

        String col = "";

        switch(adsRedisDB){
            case 0:col="db0";break;
            case 1:col="db1";break;
            case 2:col="db2";break;
            case 3:col="db3";break;
            case 4:col="db4";break;
            case 5:col="db5";break;
            case 6:col="db6";break;
            case 7:col="db7";break;
            case 8:col="db8";break;
            case 9:col="db9";break;
            case 10:col="db10";break;
            case 11:col="db11";break;
            case 12:col="db12";break;
            case 13:col="db13";break;
            case 14:col="db14";break;
            case 15:col="db15";break;   
        }

        criteriaUpdate.set(col, adid);
        criteriaUpdate.where(criteriaBuilder.equal(root.get("groupid"), adsRedisGroup));
        entityManager.createQuery(criteriaUpdate).executeUpdate();
    }
    
    public Integer countTotalRedisGroup(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<SystemConfig3> root = query.from(SystemConfig3.class);
        query.select(criteriaBuilder.countDistinct(root.get("groupid")));
        return (int) (long) entityManager.createQuery(query).getSingleResult();
        
    }
}
