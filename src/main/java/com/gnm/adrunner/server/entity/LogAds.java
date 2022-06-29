package com.gnm.adrunner.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "log_ads")
public class LogAds {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String  adsKey;

    private String  adminId;

    private String  clientIp;

    private String  updatedKey;
 
    private String  prevValue;

    private String  updatedValue;

    private String  createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdsKey() {
        return adsKey;
    }

    public void setAdsKey(String adsKey) {
        this.adsKey = adsKey;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getUpdatedKey() {
        return updatedKey;
    }

    public void setUpdatedKey(String updatedKey) {
        this.updatedKey = updatedKey;
    }

    public String getPrevValue() {
        return prevValue;
    }

    public void setPrevValue(String prevValue) {
        this.prevValue = prevValue;
    }

    public String getUpdatedValue() {
        return updatedValue;
    }

    public void setUpdatedValue(String updatedValue) {
        this.updatedValue = updatedValue;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "LogAds [adminId=" + adminId + ", adsKey=" + adsKey + ", clientIp=" + clientIp + ", createtime="
                + createtime + ", id=" + id + ", prevValue=" + prevValue + ", updatedKey=" + updatedKey
                + ", updatedValue=" + updatedValue + "]";
    }
 
    

 
}
