package com.ajayam.myapplication;

import static androidx.constraintlayout.widget.Constraints.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.ajayam.myapplication.adapter.CourseAdapter;
import com.ajayam.myapplication.databinding.ActivityMainBinding;
import com.ajayam.myapplication.model.Collections;
import com.ajayam.myapplication.model.Courses;
import com.ajayam.myapplication.model.DataCollection;
import com.ajayam.myapplication.model.Example;
import com.ajayam.myapplication.model.Smart;
import com.ajayam.myapplication.model.User;
import com.ajayam.myapplication.viewmodels.MainViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    private MainViewModel viewModel;
    ArrayList<Collections> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        getCourses();
    }

    private void getCourses() {

            viewModel.getData()
                    .observe(this, new Observer<Example>() {
                        @Override
                        public void onChanged(Example response) {

                            extractData(response);


                            Log.e(TAG, "onChangedData: "+new Gson().toJson(response));
                        }
                    });

            viewModel.getIsLoading().observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    Log.e(TAG, "onChangedLoading: "+aBoolean );
                }
            });

            viewModel.getError().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    Log.e(TAG, "onChangedError: "+s );
                }
            });

    }

    private void extractData(Example response) {
        List<Integer> coursesIdList = new ArrayList<>();

        List<Smart> smartList = response.getRecord().getResult().getCollections().getSmart();
        List<User> userList = response.getRecord().getResult().getCollections().getUser();
        String imageBaseUrl = "http://d2xkd1fof6iiv9.cloudfront.net/images/courses/";

        int smartListSize = response.getRecord().getResult().getCollections().getSmart().size();
        int userListSize = response.getRecord().getResult().getCollections().getUser().size();

        ArrayList<DataCollection> smartCollectionsList = new ArrayList<>();
        ArrayList<DataCollection> userCollectionsList = new ArrayList<>();


        //smart
        for (int i = 0; i < smartListSize; i++) {
            ArrayList<Courses> coursesList = new ArrayList<>();
            for (int j = 0; j < smartList.get(i).getCourses().size(); j++) {
                for (int k = 0; k < response.getRecord().getResult().getIndex().size(); k++) {
                   // Log.e(TAG, "extractData: "+"i="+i+" j="+j+" k="+k);
                    if( response.getRecord().getResult().getIndex().get(k).getId() == smartList.get(i).getCourses().get(j)){
                        Log.e(TAG, "extractData: "+"i="+i+" j="+j+" k="+k);
                        Courses course = new Courses(response.getRecord().getResult().getIndex().get(k).getTitle(),
                                response.getRecord().getResult().getIndex().get(k).getEducator(),
                                imageBaseUrl+response.getRecord().getResult().getIndex().get(k).getId()+"/169_820.jpg");
                        coursesList.add(course);
                    }
                }
            }
            DataCollection collections = new DataCollection(
                    response.getRecord().getResult().getCollections().getSmart().get(i).getLabel(),coursesList);
            smartCollectionsList.add(collections);
        }

        //user
//        for (int i = 0; i < userListSize; i++) {
//            ArrayList<Courses> coursesList = new ArrayList<>();
//            for (int j = 0; j < userList.get(i).getCourses().size(); j++) {
//                for (int k = 0; k < response.getRecord().getResult().getIndex().size(); k++) {
//                    if(userList.get(i).getCourses().get(j) == response.getRecord().getResult().getIndex().get(k).getId()){
//                        Courses course = new Courses(response.getRecord().getResult().getIndex().get(k).getTitle(),
//                                response.getRecord().getResult().getIndex().get(k).getEducator(),
//                                imageBaseUrl+response.getRecord().getResult().getIndex().get(k).getId()+"/169_820.jpg");
//                        coursesList.add(course);
//                    }
//                }
//            }
//            DataCollection collections = new DataCollection(
//                    response.getRecord().getResult().getCollections().getUser().get(i).getLabel(),coursesList);
//            userCollectionsList.add(collections);
//        }


        Log.e(TAG, "extractData: "+new Gson().toJson(smartCollectionsList) );
        setAdapterView(smartCollectionsList);
    }

    private void setAdapterView(ArrayList<DataCollection> collectionsList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvCourses.setLayoutManager(layoutManager);
        CourseAdapter adapter = new CourseAdapter(this, collectionsList);
        binding.rvCourses.setAdapter(adapter);
    }
}