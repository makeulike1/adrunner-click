package com.gnm.adrunner.server.param.res.admin;

import com.gnm.adrunner.server.entity.Ads;

public class ResponseListAds1 {
 
    private Ads         ads;

    private String      affName;

    private Integer     click;

    private Integer     conversion;

    private Double      conversionRate;
 
    public Ads getAds() {
        return ads;
    }

    public void setAds(Ads ads) {
        this.ads = ads;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public Integer getConversion() {
        return conversion;
    }

    public void setConversion(Integer conversion) {
        this.conversion = conversion;
    }

    public Double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public String   getAffName(){
        return affName;
    }

    public void setAffName(String affName){
        this.affName = affName;
    }

    @Override
    public String toString() {
        return "ResListAds1 [ads=" + ads + ", click=" + click + ", conversion=" + conversion + ", conversionRate="
                + conversionRate + "]";
    }

    
}
