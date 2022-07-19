package com.gnm.adrunner.server.object;

import java.util.List;

public class RedisGroup {

    private Integer id;

    private List<RedisEntity> list;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<RedisEntity> getList() {
        return list;
    }

    public void setList(List<RedisEntity> list) {
        this.list = list;
    }

    public RedisGroup(Integer id, List<RedisEntity> list){
        this.id = id;
        this.list = list;
    }

    @Override
    public String toString() {
        return "RedisGroup [id=" + id + ", list=" + list + "]";
    }

}
