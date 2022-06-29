package com.gnm.adrunner.server.param.res.admin;

import com.gnm.adrunner.server.RequestResponseInterface;

public class ResponseListAds {

    private Integer status;
    
    private String  message;

    private Integer totalCnt;

    private Integer liveCnt;
    
    private Iterable<ResponseListAds1> data;

    public ResponseListAds(Integer statusCode){
        this.status = statusCode;
        this.message = null;
        this.data = null;
        this.totalCnt = null;
        this.liveCnt = null;
    }
    

    public ResponseListAds(Integer statusCode, Iterable<ResponseListAds1> list, Integer totalCnt, Integer liveCnt){
        this.status = statusCode;
        this.message = RequestResponseInterface.getStatusMessage(statusCode);
        this.data = list;
        this.totalCnt = totalCnt;
        this.liveCnt = liveCnt;
    }


    public Integer getStatus() {
        return status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public Integer getTotalCnt() {
        return totalCnt;
    }


    public void setTotalCnt(Integer totalCnt) {
        this.totalCnt = totalCnt;
    }


    public Integer getLiveCnt() {
        return liveCnt;
    }


    public void setLiveCnt(Integer liveCnt) {
        this.liveCnt = liveCnt;
    }


    public Iterable<ResponseListAds1> getData() {
        return data;
    }


    public void setData(Iterable<ResponseListAds1> data) {
        this.data = data;
    }


    
    
}
