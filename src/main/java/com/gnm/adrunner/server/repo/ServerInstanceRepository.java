package com.gnm.adrunner.server.repo;

import java.util.List;

import com.gnm.adrunner.server.entity.ServerInstance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

public interface ServerInstanceRepository extends CrudRepository<ServerInstance, Integer>{

    @Query(value="select * from server_instance", nativeQuery = true)
    public List<ServerInstance> listAll();


    @Query(value="select * from server_instance where id=?1", nativeQuery = true)
    public ServerInstance findBySid(Integer e1);

}
