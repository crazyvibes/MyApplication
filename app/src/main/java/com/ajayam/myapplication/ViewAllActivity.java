package com.ajayam.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ajayam.myapplication.adapter.DummyModelAdapter;
import com.ajayam.myapplication.model.DummyModel;

import java.util.ArrayList;

public class ViewAllActivity extends AppCompatActivity {

    ArrayList<DummyModel> arrContacts = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        RecyclerView recyclerView = findViewById(R.id.rvListCourses);
        GridLayoutManager  gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrContacts.add(new DummyModel(R.drawable.imgb, "According to the docs", "7717788236" ));
        arrContacts.add(new DummyModel(R.drawable.imgb, "when the recyclerView is being", "7717788236" ));
        arrContacts.add(new DummyModel(R.drawable.imgb, "How could I make the scroll down", "7717788236" ));
        arrContacts.add(new DummyModel(R.drawable.imgb, "NestedScrollView because it disable", "7717788236" ));
        arrContacts.add(new DummyModel(R.drawable.imgb, "when the recyclerView is being", "7717788236" ));
        arrContacts.add(new DummyModel(R.drawable.imgb, "According to the docs", "7717788236" ));
        arrContacts.add(new DummyModel(R.drawable.imgb, "NestedScrollView because it disable", "7717788236" ));
        arrContacts.add(new DummyModel(R.drawable.imgb, "NestedScrollView because it", "7717788236" ));
        arrContacts.add(new DummyModel(R.drawable.imgb, "when the recyclerView is", "7717788236" ));
        arrContacts.add(new DummyModel(R.drawable.imgb, "when the recyclerView is being", "7717788236" ));

        DummyModelAdapter adapter = new DummyModelAdapter(this, arrContacts);
        recyclerView.setAdapter(adapter);

    }
}