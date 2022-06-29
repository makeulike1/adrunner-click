package com.gnm.adrunner.server.controller.click;

public class clickParam {

    private String  ads_id;
    
    private String  ads_key;

    private String  s_p1;

    private String  s_p2;

    private String  s_p3;

    private String  s_p4;

    private String  s_p5;

    private String  ptn_clk;

    private String  ads_type;

    private String  datetime;

    private String  ip;

    private String  ptn_pub;

    private String  click_key;

    private String  sub_pub;

    private String  gaid;

    private String  idfa;
    
    private String  os;

    private String  aff;

    private String  brand;

    private String  model;

    private String  referrer;

    private String  user_agent;

    public clickParam(){
        this.ads_key = "";
        this.s_p1 = "";
        this.s_p2 = "";
        this.s_p3 = "";
        this.s_p4 = "";
        this.s_p5 = "";
        this.ptn_clk = "";
        this.ads_type = "-1";
        this.datetime = "";
        this.ip = "";
        this.ptn_pub = "";
        this.click_key = "";
        this.sub_pub = "";
        this.gaid = "";
        this.idfa = "";
        this.os = "-1";
        this.aff = "-1";  
        this.brand = "";
        this.model = "";
        this.referrer = "";
        this.user_agent = "";
    }

    public String getPtn_pub() {
        return ptn_pub;
    }

    public void setPtn_pub(String ptn_pub) {
        this.ptn_pub = ptn_pub;
    }

    public String getClick_key() {
        return click_key;
    }

    public void setClick_key(String click_key) {
        this.click_key = click_key;
    }

    public String getSub_pub() {
        return sub_pub;
    }

    public void setSub_pub(String sub_pub) {
        this.sub_pub = sub_pub;
    }

    public String getGaid() {
        return gaid;
    }

    public void setGaid(String gaid) {
        this.gaid = gaid;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAds_key() {
        return ads_key;
    }

    public void setAds_key(String ads_key) {
        this.ads_key = ads_key;
    }

    public String getS_p1() {
        return s_p1;
    }

    public void setS_p1(String s_p1) {
        this.s_p1 = s_p1;
    }

    public String getS_p2() {
        return s_p2;
    }

    public void setS_p2(String s_p2) {
        this.s_p2 = s_p2;
    }

    public String getS_p3() {
        return s_p3;
    }

    public void setS_p3(String s_p3) {
        this.s_p3 = s_p3;
    }

    public String getS_p4() {
        return s_p4;
    }

    public void setS_p4(String s_p4) {
        this.s_p4 = s_p4;
    }

    public String getS_p5() {
        return s_p5;
    }

    public void setS_p5(String s_p5) {
        this.s_p5 = s_p5;
    }

    public String getPtn_clk() {
        return ptn_clk;
    }

    public void setPtn_clk(String ptn_clk) {
        this.ptn_clk = ptn_clk;
    }
 
    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getUser_agent() {
        return user_agent;
    }

    public void setUser_agent(String user_agent) {
        this.user_agent = user_agent;
    }

    public String getAds_type() {
        return ads_type;
    }

    public void setAds_type(String ads_type) {
        this.ads_type = ads_type;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getAff() {
        return aff;
    }

    public void setAff(String aff) {
        this.aff = aff;
    }

    public String getAds_id() {
        return ads_id;
    }

    public void setAds_id(String ads_id) {
        this.ads_id = ads_id;
    }

    @Override
    public String toString() {
        return "clickParam [ads_id=" + ads_id + ", ads_key=" + ads_key + ", ads_type=" + ads_type + ", aff=" + aff
                + ", brand=" + brand + ", click_key=" + click_key + ", datetime=" + datetime + ", gaid=" + gaid
                + ", idfa=" + idfa + ", ip=" + ip + ", model=" + model + ", os=" + os + ", ptn_clk=" + ptn_clk
                + ", ptn_pub=" + ptn_pub + ", referrer=" + referrer + ", s_p1=" + s_p1 + ", s_p2=" + s_p2 + ", s_p3="
                + s_p3 + ", s_p4=" + s_p4 + ", s_p5=" + s_p5 + ", sub_pub=" + sub_pub + ", user_agent=" + user_agent
                + "]";
    }

    

    
}