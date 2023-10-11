package com.ajayam.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CollectionList {
    @SerializedName("smart")
    @Expose
    private List<Smart> smart;
    @SerializedName("user")
    @Expose
    private List<User> user;
    @SerializedName("curated")
    @Expose
    private List<Object> curated;

    public List<Smart> getSmart() {
        return smart;
    }

    public void setSmart(List<Smart> smart) {
        this.smart = smart;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public List<Object> getCurated() {
        return curated;
    }

    public void setCurated(List<Object> curated) {
        this.curated = curated;
    }

}
