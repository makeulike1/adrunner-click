package com.gnm.adrunner.server.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.gnm.adrunner.server.entity.AdminLogin;
import com.gnm.adrunner.server.repo.AdminLoginRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdminLoginService")
public class AdminLoginService {


    @PersistenceContext
	private EntityManager entityManager;


    @Autowired  
    private AdminLoginRepository adminLoginRepository;
     

    
    @Transactional
    public Integer saveLog(AdminLogin adminLogin){
        entityManager.persist(adminLogin);
        return (int)(long)adminLogin.getId();
    }
    

    
    public Integer chkToken(String token){
        if(adminLoginRepository.findByToken(token) == null){
            return 203;
        }
        else return 200;
    }
}
