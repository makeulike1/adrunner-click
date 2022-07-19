package com.gnm.adrunner.server.repo;

import com.gnm.adrunner.server.entity.SystemConfig;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;


public interface SystemConfigRepository extends CrudRepository<SystemConfig, Integer>{

    @Query(value="select number_of_redis_group from system_config where id=1", nativeQuery = true)
    public Integer findNumberOfRedsiGroup();

    @Query(value="select current_redis_group from system_config where id=1", nativeQuery = true)
    public Integer getCurrentRedisGroup();

    @Transactional
    @Modifying
    @Query(value="update system_config set current_redis_group=?1 where id=1", nativeQuery = true)
    public void updateCurrentRedisGroup(int i);

}
