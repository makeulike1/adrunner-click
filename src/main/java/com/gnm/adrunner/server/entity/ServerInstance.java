package com.gnm.adrunner.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "server_instance")
public class ServerInstance {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer     id;

    private String      protocol;

    private String      clientIp;

    private Integer     port;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "ServerInstance [clientIp=" + clientIp + ", id=" + id + ", port=" + port + ", protocol=" + protocol
                + "]";
    }
 
    

    

}
