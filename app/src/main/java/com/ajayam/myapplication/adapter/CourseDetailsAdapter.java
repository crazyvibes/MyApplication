package com.ajayam.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajayam.myapplication.R;
import com.ajayam.myapplication.model.Collections;
import com.ajayam.myapplication.model.Courses;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CourseDetailsAdapter extends RecyclerView.Adapter<CourseDetailsAdapter.ViewHolder> {
    Context context;
    ArrayList<Courses> list;

    public CourseDetailsAdapter(Context context, ArrayList<Courses> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CourseDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.courses_sub_adapter_view, parent, false);
        CourseDetailsAdapter.ViewHolder viewHolder = new CourseDetailsAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseDetailsAdapter.ViewHolder holder, int position) {
        holder.tvCourseName.setText(list.get(position).getCourseName());
        holder.tvAuthorName.setText(list.get(position).getEducator());
        Picasso.get()
                .load(list.get(position).getImageUrl())
                .into(holder.ivCourse);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCourse;
        TextView tvCourseName, tvAuthorName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCourseName = itemView.findViewById(R.id.tvCourseNName);
            tvAuthorName = itemView.findViewById(R.id.tvAuthorName);
            ivCourse = itemView.findViewById(R.id.ivCourse);
        }
    }
}
