package com.ajayam.myapplication.model;

public class Courses {
    private int id;
    private String courseName;
    private String educator;
    private String imageUrl;

    public Courses(String courseName, String educator, String imageUrl) {
        this.courseName = courseName;
        this.educator = educator;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getEducator() {
        return educator;
    }

    public void setEducator(String educator) {
        this.educator = educator;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
