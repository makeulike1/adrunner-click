package com.gnm.adrunner.server.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "system_config_3")
public class SystemConfig3 {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer     id;

    private Integer     groupid;

    private Integer     db0;

    private Integer     db1;

    private Integer     db2;

    private Integer     db3;

    private Integer     db4;

    private Integer     db5;

    private Integer     db6;

    private Integer     db7;

    private Integer     db8;

    private Integer     db9;

    private Integer     db10;

    private Integer     db11;

    private Integer     db12;

    private Integer     db13;

    private Integer     db14;

    private Integer     db15;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public Integer getDb0() {
        return db0;
    }

    public void setDb0(Integer db0) {
        this.db0 = db0;
    }

    public Integer getDb1() {
        return db1;
    }

    public void setDb1(Integer db1) {
        this.db1 = db1;
    }

    public Integer getDb2() {
        return db2;
    }

    public void setDb2(Integer db2) {
        this.db2 = db2;
    }

    public Integer getDb3() {
        return db3;
    }

    public void setDb3(Integer db3) {
        this.db3 = db3;
    }

    public Integer getDb4() {
        return db4;
    }

    public void setDb4(Integer db4) {
        this.db4 = db4;
    }

    public Integer getDb5() {
        return db5;
    }

    public void setDb5(Integer db5) {
        this.db5 = db5;
    }

    public Integer getDb6() {
        return db6;
    }

    public void setDb6(Integer db6) {
        this.db6 = db6;
    }

    public Integer getDb7() {
        return db7;
    }

    public void setDb7(Integer db7) {
        this.db7 = db7;
    }

    public Integer getDb8() {
        return db8;
    }

    public void setDb8(Integer db8) {
        this.db8 = db8;
    }

    public Integer getDb9() {
        return db9;
    }

    public void setDb9(Integer db9) {
        this.db9 = db9;
    }

    public Integer getDb10() {
        return db10;
    }

    public void setDb10(Integer db10) {
        this.db10 = db10;
    }

    public Integer getDb11() {
        return db11;
    }

    public void setDb11(Integer db11) {
        this.db11 = db11;
    }

    public Integer getDb12() {
        return db12;
    }

    public void setDb12(Integer db12) {
        this.db12 = db12;
    }

    public Integer getDb13() {
        return db13;
    }

    public void setDb13(Integer db13) {
        this.db13 = db13;
    }

    public Integer getDb14() {
        return db14;
    }

    public void setDb14(Integer db14) {
        this.db14 = db14;
    }

    public Integer getDb15() {
        return db15;
    }

    public void setDb15(Integer db15) {
        this.db15 = db15;
    }

    @Override
    public String toString() {
        return "SystemConfig3 [db0=" + db0 + ", db1=" + db1 + ", db10=" + db10 + ", db11=" + db11 + ", db12=" + db12
                + ", db13=" + db13 + ", db14=" + db14 + ", db15=" + db15 + ", db2=" + db2 + ", db3=" + db3 + ", db4="
                + db4 + ", db5=" + db5 + ", db6=" + db6 + ", db7=" + db7 + ", db8=" + db8 + ", db9=" + db9
                + ", groupid=" + groupid + ", id=" + id + "]";
    }

}
