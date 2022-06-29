package com.gnm.adrunner.server.repo;

import com.gnm.adrunner.server.entity.Admin;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Integer> {

    @Query(value="select * from admin where admin_id=?1", nativeQuery = true)
    public Admin findByAdminId(String admin_id);

    @Transactional
    @Modifying
    @Query(value="update admin set password=?1 and updatetime=now() where id=?2", nativeQuery = true)
    public void updatePassword(String password, Integer admin_id);

}
