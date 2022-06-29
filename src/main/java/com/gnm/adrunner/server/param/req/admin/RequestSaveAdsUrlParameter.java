package com.gnm.adrunner.server.param.req.admin;

public class RequestSaveAdsUrlParameter {
    
    private String  key;

    private String  value;

    private Integer  type;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RequestSaveAdsUrlParameter [key=" + key + ", type=" + type + ", value=" + value + "]";
    }
 
    

    
}
