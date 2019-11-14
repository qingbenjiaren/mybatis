package com.melo.kkb.mybatis.pojo;

public class User {
    private String id;
    private String name;
    private String website;

    public User(String id, String name, String website) {
        this.id = id;
        this.name = name;
        this.website = website;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
