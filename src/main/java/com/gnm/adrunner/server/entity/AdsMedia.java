package com.gnm.adrunner.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ads_media")
public class AdsMedia {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String  adsKey;

    private String  mediaKey;

    private Integer mediaCost;

    private Integer mediaDailyCap;

    private Boolean isDayLimit;

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

    public String getMediaKey() {
        return mediaKey;
    }

    public void setMediaKey(String mediaKey) {
        this.mediaKey = mediaKey;
    }

    public Integer getMediaCost() {
        return mediaCost;
    }

    public void setMediaCost(Integer mediaCost) {
        this.mediaCost = mediaCost;
    }

    public Integer getMediaDailyCap() {
        return mediaDailyCap;
    }

    public void setMediaDailyCap(Integer mediaDailyCap) {
        this.mediaDailyCap = mediaDailyCap;
    }

    public Boolean getIsDayLimit() {
        return isDayLimit;
    }

    public void setIsDayLimit(Boolean isDayLimit) {
        this.isDayLimit = isDayLimit;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "AdsMedia [adsKey=" + adsKey + ", createtime=" + createtime + ", id=" + id + ", isDayLimit=" + isDayLimit
                + ", mediaCost=" + mediaCost + ", mediaDailyCap=" + mediaDailyCap + ", mediaKey=" + mediaKey + "]";
    }
     
    
}
