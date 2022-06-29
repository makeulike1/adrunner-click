package com.gnm.adrunner.server.param.req.admin;


import java.util.List;

public class RequestSaveAds {
     
    private Integer aff;

    private Integer type;

    private Integer os;
 
    private Boolean supplyDemand;

    private String  startdate;

    private String  enddate;

    private String  loopback;
    
    private String  name;

    private String  description; 
  
    private Boolean isDailyCap;

    private Integer targetImp; 
 
    private Boolean autostart;
 
    private Boolean autodown;

    private Integer status;

    private String  trackingUrl;

    private String  eventName;

    private Integer cost1;

    private Integer cost2;

    private String  advKey;

    private List<RequestSaveAds1> media;

    public Integer getAff() {
        return aff;
    }

    public void setAff(Integer aff) {
        this.aff = aff;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOs() {
        return os;
    }

    public void setOs(Integer os) {
        this.os = os;
    }

    public Boolean getSupplyDemand() {
        return supplyDemand;
    }

    public void setSupplyDemand(Boolean supplyDemand) {
        this.supplyDemand = supplyDemand;
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

    public String getLoopback() {
        return loopback;
    }

    public void setLoopback(String loopback) {
        this.loopback = loopback;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsDailyCap() {
        return isDailyCap;
    }

    public void setIsDailyCap(Boolean isDailyCap) {
        this.isDailyCap = isDailyCap;
    }

    public Integer getTargetImp() {
        return targetImp;
    }

    public void setTargetImp(Integer targetImp) {
        this.targetImp = targetImp;
    }

    public Boolean getAutostart() {
        return autostart;
    }

    public void setAutostart(Boolean autostart) {
        this.autostart = autostart;
    }

    public Boolean getAutodown() {
        return autodown;
    }

    public void setAutodown(Boolean autodown) {
        this.autodown = autodown;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTrackingUrl() {
        return trackingUrl;
    }

    public void setTrackingUrl(String trackingUrl) {
        this.trackingUrl = trackingUrl;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
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

    public String getAdvKey() {
        return advKey;
    }

    public void setAdvKey(String advKey) {
        this.advKey = advKey;
    }

    public List<RequestSaveAds1> getMedia() {
        return media;
    }

    public void setMedia(List<RequestSaveAds1> media) {
        this.media = media;
    }

    @Override
    public String toString() {
        return "RequestSaveAds [advKey=" + advKey + ", aff=" + aff + ", autodown=" + autodown + ", autostart="
                + autostart + ", cost1=" + cost1 + ", cost2=" + cost2 + ", description=" + description + ", enddate="
                + enddate + ", eventName=" + eventName + ", isDailyCap=" + isDailyCap + ", loopback=" + loopback
                + ", media=" + media + ", name=" + name + ", os=" + os + ", startdate=" + startdate + ", status="
                + status + ", supplyDemand=" + supplyDemand + ", targetImp=" + targetImp + ", trackingUrl="
                + trackingUrl + ", type=" + type + "]";
    }
     
}
