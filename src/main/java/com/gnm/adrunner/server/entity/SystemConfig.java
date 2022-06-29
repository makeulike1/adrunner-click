package com.gnm.adrunner.server.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "system_config")
public class SystemConfig {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer     id;

    private Integer     redisIndex;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRedisIndex() {
        return redisIndex;
    }

    public void setRedisIndex(Integer redisIndex) {
        this.redisIndex = redisIndex;
    }

    @Override
    public String toString() {
        return "SystemConfig [id=" + id + ", redisIndex=" + redisIndex + "]";
    }

    

}
