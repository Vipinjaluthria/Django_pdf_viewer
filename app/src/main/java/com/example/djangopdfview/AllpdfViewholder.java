package com.example.djangopdfview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AllpdfViewholder extends RecyclerView.ViewHolder  {
    private Clicklistneronrecyclerview clicklistneronrecyclerview;
    TextView username,link,teamname,eventname;
    public AllpdfViewholder(@NonNull View itemView,Clicklistneronrecyclerview clicklistneronrecyclerview) {
        super(itemView);
        this.clicklistneronrecyclerview=clicklistneronrecyclerview;
        username=itemView.findViewById(R.id.username);
        link=itemView.findViewById(R.id.link);
        teamname=itemView.findViewById(R.id.teamname);
        eventname=itemView.findViewById(R.id.eventname);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicklistneronrecyclerview.click(getAdapterPosition());
            }
        });
    }



}
