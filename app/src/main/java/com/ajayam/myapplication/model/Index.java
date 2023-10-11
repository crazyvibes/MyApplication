package com.ajayam.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Index {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;


    @SerializedName("authorid")
    @Expose
    private Integer authorid;



    @SerializedName("educator")
    @Expose
    private String educator;
    @SerializedName("owned")
    @Expose
    private Integer owned;
    @SerializedName("sale")
    @Expose
    private Integer sale;
//    @SerializedName("purchase_order")
//    @Expose
//    private Integer purchaseOrder;
    @SerializedName("watched")
    @Expose
    private Integer watched;
    @SerializedName("progress_tracking")
    @Expose
    private Double progressTracking;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }



    public String getEducator() {
        return educator;
    }

    public void setEducator(String educator) {
        this.educator = educator;
    }

    public Integer getOwned() {
        return owned;
    }

    public void setOwned(Integer owned) {
        this.owned = owned;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

//    public Integer getPurchaseOrder() {
//        return purchaseOrder;
//    }
//
//    public void setPurchaseOrder(Integer purchaseOrder) {
//        this.purchaseOrder = purchaseOrder;
//    }
//
    public Integer getWatched() {
        return watched;
    }

    public void setWatched(Integer watched) {
        this.watched = watched;
    }

    public Double getProgressTracking() {
        return progressTracking;
    }

    public void setProgressTracking(Double progressTracking) {
        this.progressTracking = progressTracking;
    }


}
