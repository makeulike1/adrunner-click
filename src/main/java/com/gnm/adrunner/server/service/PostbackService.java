package com.gnm.adrunner.server.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.gnm.adrunner.server.entity.Postback;
import com.gnm.adrunner.server.repo.PostbackRepository;
import com.gnm.adrunner.util.timeBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PostbackAppNcpiService")
public class PostbackService {
    
    @PersistenceContext
	private EntityManager entityManager;

    @Autowired 
    private PostbackRepository postbackRepository;

    @Transactional
    public Integer saveRecord(Postback p){
        entityManager.persist(p);
        return (int)(long)p.getId();
    }


    public Boolean isExistClickKey(String click_key){
        Integer id = postbackRepository.findIdByClickKey(click_key);

        if(id == null)
            return false;
        return true;
    }



    
    public Integer countTodayTotalPostbackByAdsKeyAndMediaKey(String adsKey, String mediaKey){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<Postback> root = query.from(Postback.class);
        query.select(criteriaBuilder.count(root));

        List<Predicate> predicates = new ArrayList<Predicate>();        
        predicates.add(criteriaBuilder.equal(root.get("adsKey"), adsKey));
        predicates.add(criteriaBuilder.equal(root.get("mediaKey"), mediaKey));
        predicates.add(criteriaBuilder.lessThanOrEqualTo(
            root.get("createtime"),     timeBuilder.getTodayEndTime()));
        predicates.add(criteriaBuilder.greaterThanOrEqualTo(
            root.get("createtime"),  timeBuilder.getTodayStartTime()));
        query.where(predicates.toArray(new Predicate[]{}));

        return (int) (long) entityManager.createQuery(query).getSingleResult();
    }



    public Integer countTotalPostbackByAdsKey(String adsKey){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<Postback> root = query.from(Postback.class);
        query.select(criteriaBuilder.count(root));
        query.where(criteriaBuilder.like(root.get("clickKey"), "%"+adsKey+":%"));
        return (int) (long) entityManager.createQuery(query).getSingleResult();
        
    }




    public Integer countPostbackByDate(String adsKey, String mediaKey, String startTime, String endTime){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<Postback> root = query.from(Postback.class);
        query.select(criteriaBuilder.count(root));

        List<Predicate> predicates = new ArrayList<Predicate>();        
        predicates.add(criteriaBuilder.equal(root.get("adsKey"), adsKey));
        predicates.add(criteriaBuilder.equal(root.get("mediaKey"), mediaKey));
        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createtime"), endTime));
        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createtime"), startTime));
        query.where(predicates.toArray(new Predicate[]{}));

        return (int) (long) entityManager.createQuery(query).getSingleResult();
    }




    public Integer getSumAdvCostByDate(String adsKey, String mediaKey, String startTime, String endTime){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<Postback> root = query.from(Postback.class);
        query.select(criteriaBuilder.sum(root.get("advCost")));
        query.groupBy(root.get("adsKey"));
        

        List<Predicate> predicates = new ArrayList<Predicate>();        
        predicates.add(criteriaBuilder.equal(root.get("adsKey"), adsKey));
        predicates.add(criteriaBuilder.equal(root.get("mediaKey"), mediaKey));
        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createtime"), endTime));
        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createtime"), startTime));
        query.where(predicates.toArray(new Predicate[]{}));

        Integer result = 0;

        try{
            Object tmp = entityManager.createQuery(query).getSingleResult();
            result = Integer.parseInt(tmp.toString());
        }catch(javax.persistence.NoResultException error){
            
        }
        
        return result;
    }



    public Integer getSumMediaCostByDate(String adsKey, String mediaKey, String startTime, String endTime){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<Postback> root = query.from(Postback.class);
        query.select(criteriaBuilder.sum(root.get("mediaCost")));
        query.groupBy(root.get("adsKey"));
        

        List<Predicate> predicates = new ArrayList<Predicate>();        
        predicates.add(criteriaBuilder.equal(root.get("adsKey"), adsKey));
        predicates.add(criteriaBuilder.equal(root.get("mediaKey"), mediaKey));
        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createtime"), endTime));
        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createtime"), startTime));
        query.where(predicates.toArray(new Predicate[]{}));

        Integer result = 0;

        try{
            Object tmp = entityManager.createQuery(query).getSingleResult();
            result = Integer.parseInt(tmp.toString());
        }catch(javax.persistence.NoResultException error){
            
        }
        
        return result;
    }
}
