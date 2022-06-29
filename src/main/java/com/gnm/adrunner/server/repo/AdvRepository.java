package com.gnm.adrunner.server.repo;


import com.gnm.adrunner.server.entity.Adv;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;

public interface AdvRepository extends CrudRepository<Adv, Integer> {

    @Transactional
    @Modifying
    @Query(value="update adv SET is_delete=true, deletetime=now() where id=?1", nativeQuery = true)
    public void deleteByAdvId(Integer id);

    @Query(value="select * FROM adv where is_delete=false", nativeQuery = true)
    public Iterable<Adv> listAll();

    @Transactional
    @Modifying
    @Query(value="update adv SET adv_key=?1 where id=?2", nativeQuery = true)
    public void updateAdvKey(String advKey, Integer advId);
    
}
