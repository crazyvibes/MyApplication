package com.ajayam.myapplication;

import static androidx.constraintlayout.widget.Constraints.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ajayam.myapplication.adapter.CourseAdapter;
import com.ajayam.myapplication.databinding.ActivityMainBinding;
import com.ajayam.myapplication.model.Collections;
import com.ajayam.myapplication.model.Courses;
import com.ajayam.myapplication.model.DataCollection;
import com.ajayam.myapplication.model.Example;
import com.ajayam.myapplication.model.Index;
import com.ajayam.myapplication.model.Smart;
import com.ajayam.myapplication.model.User;
import com.ajayam.myapplication.viewmodels.MainViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private ArrayList<Collections> list = new ArrayList<>();
    private Example example;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //initialise instances
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.rlSmart.setOnClickListener(this);
        binding.rlUser.setOnClickListener(this);
        binding.rlCurated.setOnClickListener(this);

        //get data
        getCourses();
    }

    private void getCourses() {
        //observing data from viewModel
            viewModel.getData()
                    .observe(this, new Observer<Example>() {
                        @Override
                        public void onChanged(Example response) {
                            example = response;
                            setAdapterView(example.getRecord().getResult().getCollections().getSmart(),
                                    example.getRecord().getResult().getIndex());

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
        //setAdapterView(smartCollectionsList);
    }

    private void setAdapterView(List<Smart> smart, List<Index> index) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvCourses.setLayoutManager(layoutManager);
        CourseAdapter adapter = new CourseAdapter(this, smart, index);
        binding.rvCourses.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.rlSmart){
            setAdapterView(example.getRecord().getResult().getCollections().getSmart(),
                    example.getRecord().getResult().getIndex());
            setBackgrounds(0);
        }else if(view.getId() == R.id.rlUser){
            setAdapterView(example.getRecord().getResult().getCollections().getSmart(),
                    example.getRecord().getResult().getIndex());
            setBackgrounds(1);

            //Json data not suitable, should be similar list type
//            setAdapterView(example.getRecord().getResult().getCollections().getUser(),
//                    example.getRecord().getResult().getIndex());


        }else if(view.getId() == R.id.rlCurated){
            setAdapterView(example.getRecord().getResult().getCollections().getSmart(),
                    example.getRecord().getResult().getIndex());
            setBackgrounds(2);
//            setAdapterView(example.getRecord().getResult().getCollections().getCurated(),
//                    example.getRecord().getResult().getIndex());

        }
    }

    private void setBackgrounds(int i) {
        //changing background
        if(i==0){
            binding.rlSmart.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.bg_rounded_white) );
            binding.rlUser.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.bg_rounded_corner) );
            binding.rlCurated.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.bg_rounded_corner) );

            binding.tvSmart.setTextColor(getResources().getColor(android.R.color.black));
            binding.tvUser.setTextColor(getResources().getColor(android.R.color.white));
            binding.tvCurated.setTextColor(getResources().getColor(android.R.color.white));

        } else if (i==1) {
            binding.rlSmart.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.bg_rounded_corner) );
            binding.rlUser.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.bg_rounded_white) );
            binding.rlCurated.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.bg_rounded_corner) );
            binding.tvSmart.setTextColor(getResources().getColor(android.R.color.white));
            binding.tvUser.setTextColor(getResources().getColor(android.R.color.black));
            binding.tvCurated.setTextColor(getResources().getColor(android.R.color.white));
        }else {
            binding.rlSmart.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.bg_rounded_corner) );
            binding.rlUser.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.bg_rounded_corner) );
            binding.rlCurated.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.bg_rounded_white) );
            binding.tvSmart.setTextColor(getResources().getColor(android.R.color.white));
            binding.tvUser.setTextColor(getResources().getColor(android.R.color.white));
            binding.tvCurated.setTextColor(getResources().getColor(android.R.color.black));
        }
    }
}