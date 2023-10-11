package com.ajayam.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajayam.myapplication.R;
import com.ajayam.myapplication.ViewAllActivity;
import com.ajayam.myapplication.model.Index;
import com.ajayam.myapplication.model.Smart;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder>{
    Context context;
    List<Smart> list;
    List<Index> index;

    public CourseAdapter(Context context, List<Smart> list, List<Index> index) {
        this.context = context;
        this.list = list;
        this.index = index;
    }

    @NonNull
    @Override
    public CourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.courses_adapter_view, parent, false);
        CourseAdapter.ViewHolder viewHolder = new CourseAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).getLabel());
        setCourseDetailsAdapter(holder,position);

        holder.tvViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewAllActivity.class);
                intent.putIntegerArrayListExtra("COURSES", (ArrayList<Integer>) list.get(position).getCourses());
                intent.putExtra("TITLE",list.get(position).getLabel());
                context.startActivity(intent);
            }
        });

    }

    private void setCourseDetailsAdapter(ViewHolder holder, int position) {
        Log.e("TAG", "setCourseDetailsAdapter"+position+": "+new Gson().toJson(list.get(position)) );
        LinearLayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        holder.rvCourse.setLayoutManager(layoutManager);
        CourseDetailsAdapter adapter = new CourseDetailsAdapter(context, list.get(position).getCourses(),index);
        holder.rvCourse.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvViewAll;
        RecyclerView rvCourse;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvCollectionName);
            tvViewAll = itemView.findViewById(R.id.tvViewAll);
            rvCourse = itemView.findViewById(R.id.rvCourse);
        }
    }
}
