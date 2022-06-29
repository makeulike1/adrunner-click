package com.gnm.adrunner.server.param.req.admin;

public class RequestSaveMedia {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RequestSaveMedia [name=" + name + "]";
    }
 
}
