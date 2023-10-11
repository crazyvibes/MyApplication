package com.ajayam.myapplication.model;

import java.util.ArrayList;
import java.util.List;

public class DataCollection {
    private String id;
    private String collectionName;
    private ArrayList<Courses> list;

    public DataCollection( String collectionName, ArrayList<Courses> list) {
        this.collectionName = collectionName;
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public ArrayList<Courses> getList() {
        return list;
    }

    public void setList(ArrayList<Courses> list) {
        this.list = list;
    }
}
