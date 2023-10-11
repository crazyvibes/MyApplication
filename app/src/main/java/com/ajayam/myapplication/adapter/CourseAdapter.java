package com.ajayam.myapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajayam.myapplication.R;
import com.ajayam.myapplication.model.Collections;
import com.ajayam.myapplication.model.DataCollection;
import com.google.gson.Gson;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder>{
    Context context;
    ArrayList<DataCollection> list;

    public CourseAdapter(Context context, ArrayList<DataCollection> list) {
        this.context = context;
        this.list = list;
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
        holder.tvName.setText(list.get(position).getCollectionName());
        setCourseDetailsAdapter(holder,position);

    }

    private void setCourseDetailsAdapter(ViewHolder holder, int position) {
        Log.e("TAG", "setCourseDetailsAdapter"+position+": "+new Gson().toJson(list.get(position)) );
        LinearLayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        holder.rvCourse.setLayoutManager(layoutManager);
        CourseDetailsAdapter adapter = new CourseDetailsAdapter(context, list.get(position).getList());
        holder.rvCourse.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        RecyclerView rvCourse;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvCollectionName);
            rvCourse = itemView.findViewById(R.id.rvCourse);
        }
    }
}
