package com.gnm.adrunner.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rpt_day")
public class RptDay {
    
     
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer     id;

    private String      date;

    private String      adsKey;

    private String      mediaKey;

    private String      advKey;
    
    private Integer     clicks;

    private Integer     conversions;

    private Float       conversionRate;

    private Integer     cost1;

    private Integer     cost2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getAdvKey() {
        return advKey;
    }

    public void setAdvKey(String advKey) {
        this.advKey = advKey;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public Integer getConversions() {
        return conversions;
    }

    public void setConversions(Integer conversions) {
        this.conversions = conversions;
    }

    public Float getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Float conversionRate) {
        this.conversionRate = conversionRate;
    }

    public Integer getCost1() {
        return cost1;
    }

    public void setCost1(Integer cost1) {
        this.cost1 = cost1;
    }

    public Integer getCost2() {
        return cost2;
    }

    public void setCost2(Integer cost2) {
        this.cost2 = cost2;
    }

    @Override
    public String toString() {
        return "RptDay [adsKey=" + adsKey + ", advKey=" + advKey + ", clicks=" + clicks + ", conversionRate="
                + conversionRate + ", conversions=" + conversions + ", cost1=" + cost1 + ", cost2=" + cost2 + ", date="
                + date + ", id=" + id + ", mediaKey=" + mediaKey + "]";
    }
  
}
