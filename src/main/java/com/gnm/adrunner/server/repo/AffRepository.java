package com.gnm.adrunner.server.repo;

import java.util.List;

import com.gnm.adrunner.server.entity.Aff;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AffRepository extends CrudRepository<Aff, Integer> {

    @Query(value="select * from aff where is_delete=false", nativeQuery = true)
    Iterable<Aff> list();

    @Query(value="select * from aff where is_delete=false", nativeQuery = true)
    List<Aff> listAll();

    @Query(value="select name from aff where id=?1", nativeQuery = true)
    public String findNameById(Integer affId);
 

    @Transactional
    @Modifying
    @Query(value="update aff set status=1 where id=?1", nativeQuery = true)
    public void updateStatusDone(Integer id);

    @Transactional
    @Modifying
    @Query(value="update aff set status=0 where id=?1", nativeQuery = true)
    public void updateStatusToBeDone(Integer id);


    @Query(value="select * from aff where id=?1", nativeQuery = true)
    public Aff findByID(Integer id);

    @Query(value="select * from aff where is_delete=false and status=1", nativeQuery = true)
    Iterable<Aff> listStatusComplete();
 
}
