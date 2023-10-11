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
import com.ajayam.myapplication.model.Index;
import com.bumptech.glide.Glide;

import java.util.List;


public class CourseDetailsAdapter extends RecyclerView.Adapter<CourseDetailsAdapter.ViewHolder> {
    Context context;
    List<Integer> list;
    List<Index> index;
    String imageBaseUrl = "https://d2xkd1fof6iiv9.cloudfront.net/images/courses/";


    public CourseDetailsAdapter(Context context, List<Integer> list, List<Index> index) {
        this.context = context;
        this.list = list;
        this.index = index;
    }

    public CourseDetailsAdapter(Context context, List<Integer> list) {
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

      //  setData(holder,position);

        holder.tvCourseName.setText(String.valueOf(list.get(position)));
        Glide.with(context)
                .load(imageBaseUrl+list.get(position)+"/169_820.jpg")
                .into(holder.ivCourse);

    }

    private void setData(ViewHolder holder, int position) {
        String imageBaseUrl = "https://d2xkd1fof6iiv9.cloudfront.net/images/courses/";
        for (int i = 0; i < index.size(); i++) {
            if(list.get(position) == index.get(i).getId()){

                holder.tvCourseName.setText(index.get(i).getTitle());
                holder.tvAuthorName.setText(index.get(i).getEducator());

                Glide.with(context)
                        .load(imageBaseUrl+index.get(i).getId()+"/169_820.jpg")
                        .into(holder.ivCourse);
            }
        }

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

    public interface Filter{

    }
}
