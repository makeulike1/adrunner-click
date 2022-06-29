package com.gnm.adrunner.server.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "view_ads_media")
public class ViewAdsMedia {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer     id;

    private String      adsKey;

    private String      name;

    private String      mediaKey;

    private Integer     mediaCost;

    private Integer     mediaDailyCap;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "ViewAdsMedia [adsKey=" + adsKey + ", id=" + id + ", mediaCost=" + mediaCost + ", mediaDailyCap="
                + mediaDailyCap + ", mediaKey=" + mediaKey + ", name=" + name + "]";
    }
 
    

}
