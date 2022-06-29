package com.gnm.adrunner.server.param.req.admin;

public class RequestSaveAds1 {

    private String      name;

    private Integer     cost;

    private Integer     dailycap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getDailycap() {
        return dailycap;
    }

    public void setDailycap(Integer dailycap) {
        this.dailycap = dailycap;
    }

    @Override
    public String toString() {
        return "RequestSaveAds1 [cost=" + cost + ", dailycap=" + dailycap + ", name=" + name + "]";
    }

    
    
}
