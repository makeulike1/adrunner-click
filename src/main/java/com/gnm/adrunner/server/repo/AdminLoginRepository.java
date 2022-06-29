package com.gnm.adrunner.server.repo;

import com.gnm.adrunner.server.entity.AdminLogin;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AdminLoginRepository extends CrudRepository<AdminLogin, Integer> {
 
    @Query(value="select id from admin_login where token=?1", nativeQuery = true)
    public Integer findByToken(String token);

    @Query(value="select admin_id from admin_login where token=?1", nativeQuery = true)
    public String findAdminIdByToken(String token);
}
