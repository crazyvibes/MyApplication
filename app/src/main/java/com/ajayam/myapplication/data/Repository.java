package com.ajayam.myapplication.data;

import com.ajayam.myapplication.model.Example;

import io.reactivex.Single;

public class Repository {
    private ApiManager apiManager;

    public Repository(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

    public Single<Example> getCoursesData() {
        return apiManager.getCoursesData();
    }
}
