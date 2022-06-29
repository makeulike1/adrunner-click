package com.gnm.adrunner.server.param.req.admin;

public class RequestSaveAdsMedia {

    private String adsKey;
    
    private String mediaName;

    public String getAdsKey() {
        return adsKey;
    }

    public void setAdsKey(String adsKey) {
        this.adsKey = adsKey;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    @Override
    public String toString() {
        return "RequestSaveAdsMedia [adsKey=" + adsKey + ", mediaName=" + mediaName + "]";
    }

    
    
}
