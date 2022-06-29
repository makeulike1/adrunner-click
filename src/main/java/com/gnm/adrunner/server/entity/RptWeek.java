package com.gnm.adrunner.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rpt_week")
public class RptWeek {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer     id;

    private String      date;

    private Integer     weekNum;

    private String      startdate;

    private String      enddate;

    private Integer     adsId;

    private String      adsName;

    private Integer     avgClicks;

    private Integer     avgConversions;

    private Float       avgConversionrFloat;

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

    public Integer getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(Integer weekNum) {
        this.weekNum = weekNum;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public Integer getAdsId() {
        return adsId;
    }

    public void setAdsId(Integer adsId) {
        this.adsId = adsId;
    }

    public String getAdsName() {
        return adsName;
    }

    public void setAdsName(String adsName) {
        this.adsName = adsName;
    }

    public Integer getAvgClicks() {
        return avgClicks;
    }

    public void setAvgClicks(Integer avgClicks) {
        this.avgClicks = avgClicks;
    }

    public Integer getAvgConversions() {
        return avgConversions;
    }

    public void setAvgConversions(Integer avgConversions) {
        this.avgConversions = avgConversions;
    }

    public Float getAvgConversionrFloat() {
        return avgConversionrFloat;
    }

    public void setAvgConversionrFloat(Float avgConversionrFloat) {
        this.avgConversionrFloat = avgConversionrFloat;
    }

    @Override
    public String toString() {
        return "RptWeek [adsId=" + adsId + ", adsName=" + adsName + ", avgClicks=" + avgClicks
                + ", avgConversionrFloat=" + avgConversionrFloat + ", avgConversions=" + avgConversions + ", date="
                + date + ", enddate=" + enddate + ", id=" + id + ", startdate=" + startdate + ", weekNum=" + weekNum
                + "]";
    }
    
}
