package com.gnm.adrunner.server.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

@Service("AdminService")
public class AdminService {

    @PersistenceContext
	private EntityManager entityManager;
    
}
