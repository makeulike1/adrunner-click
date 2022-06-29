package com.gnm.adrunner.server.repo;

import java.util.List;

import com.gnm.adrunner.server.entity.AffParam;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Modifying;
import javax.transaction.Transactional;

public interface AffParamRepository extends CrudRepository<AffParam, Integer> {

    @Query(value="select * from aff_param where aff_id=?1", nativeQuery = true)
    Iterable<AffParam> findByAffId(Integer affId);
 
    @Query(value="select * from aff_param", nativeQuery = true)
    public List<AffParam> listAll();

    @Query(value="select * from aff_param where id=?1", nativeQuery = true)
    public AffParam findByID(Integer id);

    @Transactional
    @Modifying
    @Query(value="delete from aff_param where id=?1", nativeQuery = true)
    public void deleteById(Integer id);

}
