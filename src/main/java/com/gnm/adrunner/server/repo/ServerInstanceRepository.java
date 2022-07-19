package com.gnm.adrunner.server.repo;

import java.util.List;

import com.gnm.adrunner.server.entity.ServerInstance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

public interface ServerInstanceRepository extends CrudRepository<ServerInstance, Integer>{

    @Query(value="select * from server_instance", nativeQuery = true)
    public List<ServerInstance> listAll();

    @Query(value="select * from server_instance where type=?1", nativeQuery = true)
    public List<ServerInstance> listClickServer(Integer e1);


    @Query(value="select * from server_instance where id=?1", nativeQuery = true)
    public ServerInstance findBySid(Integer e1);

    @Query(value="select fullURL from server_instance where type=?1 and server_group=-1", nativeQuery = true)
    public String getServerHost(Integer type);

    @Query(value="select fullURL from server_instance where type=?1 and server_group=?2", nativeQuery = true)
    public List<String> getServerHostWithGroup(Integer type, int i);

    @Query(value="select client_ip from server_instance where type=?1 and server_group=?2", nativeQuery = true)
    public List<String> getServerClientIpWithGroup(Integer type, int i);

}
