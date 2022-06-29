package com.gnm.adrunner.server.param.req.admin;

public class RequestModifyAff {

    private Integer type;

    private String  name;

    private Integer status;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RequestModifyAff [name=" + name + ", status=" + status + ", type=" + type + "]";
    }

    
}
