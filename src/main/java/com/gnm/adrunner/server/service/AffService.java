package com.gnm.adrunner.server.service;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnm.adrunner.server.entity.Aff;
import com.gnm.adrunner.server.repo.AffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
@Service("AffService")
public class AffService {
  


    
    @PersistenceContext
	private EntityManager entityManager;

 

    @Autowired
    AffRepository affRepository;
    

    public Iterable<Aff> listAff(){
        return affRepository.list();
    }



    public void updateStatusToBeDone(Integer affId){
        affRepository.updateStatusToBeDone(affId);
    }



    public void updateStatusDone(Integer affId){
        affRepository.updateStatusDone(affId);
    }
    
}
