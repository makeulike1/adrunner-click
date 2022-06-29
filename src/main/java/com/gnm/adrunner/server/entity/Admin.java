package com.gnm.adrunner.server.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String  admin_id;

    private String  password;

    private String  updatetime;

    private String  createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "Admin [admin_id=" + admin_id + ", createtime=" + createtime + ", id=" + id + ", password=" + password
                + ", updatetime=" + updatetime + "]";
    }

    
}
