package com.gnm.adrunner.server.param.req.admin;

public class RequestSignup {

    private String  id;

    private String  password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RequestSignup [id=" + id + ", password=" + password + "]";
    }

    
}
