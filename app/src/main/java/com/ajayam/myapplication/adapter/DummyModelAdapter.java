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
import com.ajayam.myapplication.model.DummyModel;

import java.util.ArrayList;

public class DummyModelAdapter extends RecyclerView.Adapter<DummyModelAdapter.ViewHolder> {

    Context context;
    ArrayList<DummyModel> dummyArray;

    public DummyModelAdapter(Context context, ArrayList<DummyModel> dummyArray) {
        this.context = context;
        this.dummyArray = dummyArray;
    }

    @NonNull
    @Override
    public DummyModelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_all_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DummyModelAdapter.ViewHolder holder, int position) {
        holder.movieImg.setImageResource(dummyArray.get(position).img);
        holder.movieTitle.setText(dummyArray.get(position).name);
        holder.movieAuth.setText(dummyArray.get(position).auth);
    }

    @Override
    public int getItemCount() {
        return dummyArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitle, movieAuth;
        ImageView movieImg;

        public ViewHolder(View itemView) {
            super((itemView));

            movieImg = itemView.findViewById(R.id.viewALL_movieImg);
            movieTitle = itemView.findViewById(R.id.viewAll_movieTitle);
            movieAuth = itemView.findViewById(R.id.viewAll_movieAuth);
        }
    }
}
