package com.gnm.adrunner.server.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
 
import org.springframework.stereotype.Service;

@Service("RptWeekService")
public class RptWeekService {
    
    @PersistenceContext
	private EntityManager entityManager;

}
