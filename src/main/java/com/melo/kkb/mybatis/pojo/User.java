package com.melo.kkb.mybatis.pojo;

public class User {
    private int id;
    private String name;
    private String website;

    public User(){}
    public User(int id, String name, String website) {
        this.id = id;
        this.name = name;
        this.website = website;
    }

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
