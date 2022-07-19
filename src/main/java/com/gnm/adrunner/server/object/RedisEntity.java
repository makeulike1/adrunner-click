package com.gnm.adrunner.server.object;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;

public class RedisEntity {
    private Integer groupId;

    private List<RedisTemplate<String, Object>> dbList = new ArrayList<RedisTemplate<String, Object>>();

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public List<RedisTemplate<String, Object>> getDbList() {
        return dbList;
    }

    public void setDbList(List<RedisTemplate<String, Object>> dbList) {
        this.dbList = dbList;
    }

    public RedisEntity(Integer id, List<RedisTemplate<String, Object>> list ){
        this.groupId = id;
        this.dbList = list;
    }

    @Override
    public String toString() {
        return "RedisEntity [dbList=" + dbList + ", groupId=" + groupId + "]";
    }

}
