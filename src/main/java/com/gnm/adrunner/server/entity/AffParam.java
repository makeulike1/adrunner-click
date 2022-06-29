package com.gnm.adrunner.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aff_param")
public class AffParam {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer     id;

    private Integer     affId;

    private Integer     paramType;

    private String      paramKey;

    private String      paramValue;

    private String      passValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAffId() {
        return affId;
    }

    public void setAffId(Integer affId) {
        this.affId = affId;
    }

    public Integer getParamType() {
        return paramType;
    }

    public void setParamType(Integer paramType) {
        this.paramType = paramType;
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getPassValue() {
        return passValue;
    }

    public void setPassValue(String passValue) {
        this.passValue = passValue;
    }

    @Override
    public String toString() {
        return "AffParam [affId=" + affId + ", id=" + id + ", paramKey=" + paramKey + ", paramType=" + paramType
                + ", paramValue=" + paramValue + ", passValue=" + passValue + "]";
    }
 
    
    
}
