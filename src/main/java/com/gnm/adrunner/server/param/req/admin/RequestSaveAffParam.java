package com.gnm.adrunner.server.param.req.admin;

public class RequestSaveAffParam {

    private Integer paramType;

    private String  queryKey;

    private String  queryValue;

    private String  passValue;

    public Integer getParamType() {
        return paramType;
    }

    public void setParamType(Integer paramType) {
        this.paramType = paramType;
    }

    public String getQueryKey() {
        return queryKey;
    }

    public void setQueryKey(String queryKey) {
        this.queryKey = queryKey;
    }

    public String getQueryValue() {
        return queryValue;
    }

    public void setQueryValue(String queryValue) {
        this.queryValue = queryValue;
    }

    public String getPassValue() {
        return passValue;
    }

    public void setPassValue(String passValue) {
        this.passValue = passValue;
    }

    @Override
    public String toString() {
        return "RequestSaveAffParam [paramType=" + paramType + ", passValue=" + passValue + ", queryKey=" + queryKey
                + ", queryValue=" + queryValue + "]";
    }

    
}
