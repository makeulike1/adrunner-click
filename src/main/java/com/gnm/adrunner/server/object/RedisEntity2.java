package com.gnm.adrunner.server.object;

public class RedisEntity2 {

    private Integer db;

    private Integer group;

    public Integer getDb() {
        return db;
    }

    public void setDb(Integer db) {
        this.db = db;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }


    public RedisEntity2(Integer db, Integer group){
        this.db = db;
        this.group = group;
    }
    
    @Override
    public String toString() {
        return "RedisEntity2 [db=" + db + ", group=" + group + "]";
    }


}
