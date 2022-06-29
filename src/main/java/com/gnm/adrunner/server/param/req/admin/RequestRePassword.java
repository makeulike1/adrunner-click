package com.gnm.adrunner.server.param.req.admin;

public class RequestRePassword {

    
    private String  id;

    private String  password;

    private String  newpassword;

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

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    @Override
    public String toString() {
        return "RequestRePassword [id=" + id + ", newpassword=" + newpassword + ", password=" + password + "]";
    }
 
    

    
}
