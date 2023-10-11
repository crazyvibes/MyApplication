package com.ajayam.myapplication.data;

import com.ajayam.myapplication.model.Example;

import io.reactivex.Single;
import retrofit2.http.GET;


public interface ApiManager {

    //get data
    @GET("6458ec108e4aa6225e98d54d")
    Single<Example> getCoursesData();
}
