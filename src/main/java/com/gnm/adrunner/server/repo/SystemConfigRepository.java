package com.gnm.adrunner.server.repo;

import com.gnm.adrunner.server.entity.SystemConfig;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;


public interface SystemConfigRepository extends CrudRepository<SystemConfig, Integer>{

    @Query(value="select redis_index from system_config where id=1", nativeQuery = true)
    public Integer findRedisIndex();


    @Transactional
    @Modifying
    @Query(value="update system_config set redis_index=?1 where id=1", nativeQuery = true)
    public void updateRedisIndex(Integer redisIndex);

}
