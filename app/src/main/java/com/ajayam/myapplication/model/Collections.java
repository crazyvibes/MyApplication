package com.ajayam.myapplication.model;

import java.util.ArrayList;

public class Collections {
    private String id;
    private String name;
    //private ArrayList<Courses> list;
    private ArrayList<Courses> list;

    public Collections(String name, ArrayList<Courses> list) {
        this.name = name;
        this.list = list;
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

    public ArrayList<Courses> getList() {
        return list;
    }

    public void setList(ArrayList<Courses> list) {
        this.list = list;
    }
}
