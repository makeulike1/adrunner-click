package com.gnm.adrunner.server.service;


import com.gnm.adrunner.server.repo.ServerInstanceRepository;
import com.gnm.adrunner.util.reqRemoteServer;
import com.gnm.adrunner.config.GlobalConstant;
import com.gnm.adrunner.server.entity.ServerInstance;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
@Service("MemoryDataService")
public class MemoryDataService {
    

    @Autowired
    ServerInstanceRepository serverInstanceRepository;
    
    // 원격 서버 메모리 데이터 업데이트
    public void updateMemoryData(String type, Integer id){
        for(ServerInstance e : serverInstanceRepository.listAll())
            reqRemoteServer.requestGET(e.getProtocol()+e.getClientIp()+":"+e.getPort()+GlobalConstant.PATH_UPDATE_MEMORY_DATA+"?type="+type+"&id="+id);
    }


    // 원격 서버 메모리 데이터 추가
    public void addMemoryData(String type, Integer id){
        for(ServerInstance e : serverInstanceRepository.listAll())
            reqRemoteServer.requestGET(e.getProtocol()+e.getClientIp()+":"+e.getPort()+GlobalConstant.PATH_ADD_MEMORY_DATA+"?type="+type+"&id="+id);
    }


    // 원격 서버 메모리 데이터 삭제
    public void deleteMemoryData(String type, Integer id){
        for(ServerInstance e : serverInstanceRepository.listAll())
            reqRemoteServer.requestGET(e.getProtocol()+e.getClientIp()+":"+e.getPort()+GlobalConstant.PATH_DELETE_MEMORY_DATA+"?type="+type+"&id="+id);
    }
    
}
