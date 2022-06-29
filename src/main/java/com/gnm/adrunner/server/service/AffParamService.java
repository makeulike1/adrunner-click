package com.gnm.adrunner.server.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnm.adrunner.server.entity.AffParam;
import com.gnm.adrunner.server.repo.AffParamRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("AffParamService")
public class AffParamService {

    
    
    @PersistenceContext
	private EntityManager entityManager;

 

    @Autowired
    AffParamRepository affParamRepository;



    public Iterable<AffParam> findByAffId(Integer affId) {
        return affParamRepository.findByAffId(affId);
    }


    @Transactional
    public Integer saveAffParam(AffParam ap){
        entityManager.persist(ap);
        return (int)(long)ap.getId();
    }


    public void deleteById(Integer affId){
        affParamRepository.deleteById(affId);
    }

}
