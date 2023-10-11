package com.ajayam.myapplication.data;

import static androidx.constraintlayout.widget.Constraints.TAG;

import android.util.Log;

import com.ajayam.myapplication.model.Courses;
import com.ajayam.myapplication.model.DataCollection;
import com.ajayam.myapplication.model.Example;
import com.ajayam.myapplication.model.Smart;
import com.ajayam.myapplication.model.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

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
