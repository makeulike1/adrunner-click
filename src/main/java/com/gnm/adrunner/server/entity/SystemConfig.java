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

    private Integer     numberOfRedisGroup;

    private Integer     currentRedisGroup;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumberOfRedisGroup() {
        return numberOfRedisGroup;
    }

    public void setNumberOfRedisGroup(Integer numberOfRedisGroup) {
        this.numberOfRedisGroup = numberOfRedisGroup;
    }

    public Integer getCurrentRedisGroup() {
        return currentRedisGroup;
    }

    public void setCurrentRedisGroup(Integer currentRedisGroup) {
        this.currentRedisGroup = currentRedisGroup;
    }

    @Override
    public String toString() {
        return "SystemConfig [currentRedisGroup=" + currentRedisGroup + ", id=" + id + ", numberOfRedisGroup="
                + numberOfRedisGroup + "]";
    }
    
    
}
