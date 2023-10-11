package com.ajayam.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("index")
    @Expose
    private List<Index> index;

    @SerializedName("collections")
    @Expose
    private CollectionList collections;

    public List<Index> getIndex() {
        return index;
    }

    public void setIndex(List<Index> index) {
        this.index = index;
    }

    public CollectionList getCollections() {
        return collections;
    }

    public void setCollections(CollectionList collections) {
        this.collections = collections;
    }

}
