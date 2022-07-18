package com.gnm.adrunner.server.param.res.admin;

public class ResponseTest {

    private String url;

    public ResponseTest(String url){
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ResponseTest [url=" + url + "]";
    }

    
}
