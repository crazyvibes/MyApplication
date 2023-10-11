package com.ajayam.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.ajayam.myapplication.adapter.CourseDetailsAdapter;
import com.ajayam.myapplication.adapter.DummyModelAdapter;
import com.ajayam.myapplication.databinding.ActivityMainBinding;
import com.ajayam.myapplication.databinding.ActivityViewAllBinding;
import com.ajayam.myapplication.model.DummyModel;
import com.ajayam.myapplication.model.Index;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity implements View.OnClickListener {


    ActivityViewAllBinding binding;
    ArrayList<Integer> filteredList;
    ArrayList<Integer> list ;
    CourseDetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewAllBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentData();
        textWatcher();

    }

    private void textWatcher() {
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                filter(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });
    }

    private void getIntentData() {
        if(getIntent().hasExtra("TITLE")){
            String title = getIntent().getStringExtra("TITLE");
            list = getIntent().getIntegerArrayListExtra("COURSES");

            filteredList = list;

            binding.tvTitle.setText(title);
            setAdapter(list);

        }
    }

    private void setAdapter(ArrayList<Integer> list) {

        GridLayoutManager  gridLayoutManager = new GridLayoutManager(this, 2);
        binding.rvCourses.setLayoutManager(gridLayoutManager);

        adapter = new CourseDetailsAdapter(this, filteredList);
        binding.rvCourses.setAdapter(adapter);
    }

    public void filter(String searchText) {
        Log.e("TAG", "filter: "+searchText );
        filteredList.clear();
        if (TextUtils.isEmpty(searchText)) {
            filteredList = list; // If the search text is empty, show all items.
            Log.e("TAG", "filte: "+new Gson().toJson(filteredList)+" "+new Gson().toJson(list));
        } else {
            try {
                int searchValue = Integer.parseInt(searchText);
                for (Integer data : list) {
                    if (data == searchValue) {
                        filteredList.add(data); // Add items that match the search text.
                    }
                }
            }
            catch (NumberFormatException e) {
                    // Handle invalid integer input (e.g., non-integer search text).
                }

        }
        adapter.notifyDataSetChanged(); // Notify the adapter that the data has changed.
        Log.e("TAG", "filter: "+new Gson().toJson(filteredList));
    }

    @Override
    public void onClick(View view) {
        if()
    }
}