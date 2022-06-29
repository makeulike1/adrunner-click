package com.gnm.adrunner.server.service;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.gnm.adrunner.server.entity.Adv;
import com.gnm.adrunner.server.repo.AdvRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("AdvService")
public class AdvService {

    
    
    @PersistenceContext
	private EntityManager entityManager;

 

    @Autowired
    AdvRepository advRepository;

    
    public List<String> findByName(String name){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> query = criteriaBuilder.createQuery(String.class);
        Root<Adv> root = query.from(Adv.class);
        query.multiselect(root.get("name"));
        query.where(criteriaBuilder.like(root.get("name"), '%' +name+ '%'));
        return entityManager.createQuery(query).getResultList();
    }

    @Transactional
    public Integer saveAdv(Adv adv){
        entityManager.persist(adv);
        return (int)(long)adv.getId();
    }
}
