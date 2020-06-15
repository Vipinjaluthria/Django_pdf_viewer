package com.example.djangopdfview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AllpdfAdpater extends RecyclerView.Adapter<AllpdfViewholder> {
    Clicklistneronrecyclerview clicklistneronrecyclerview;
    public AllpdfAdpater(List<Model> modelList,Clicklistneronrecyclerview clicklistneronrecyclerview) {
        this.modelList = modelList;
        this.clicklistneronrecyclerview=clicklistneronrecyclerview;
    }

    List<Model> modelList;
    @NonNull
    @Override
    public AllpdfViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.allpdfshow, parent, false);
        return  new AllpdfViewholder(view,clicklistneronrecyclerview);
    }

    @Override
    public void onBindViewHolder(@NonNull AllpdfViewholder holder, int position) {
        holder.username.setText(modelList.get(position).getUsername());
        holder.link.setText(modelList.get(position).getLink());
        holder.teamname.setText(modelList.get(position).getTeamname());
        holder.eventname.setText(modelList.get(position).getEventname());    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
