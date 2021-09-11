package com.dd.pglocator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dd.pglocator.nearbyScreen.PgdetailsActivity;
import com.dd.pglocator.nearbyScreen.model;

import java.util.ArrayList;

public class searchadapter extends RecyclerView.Adapter<searchadapter.MyViewHolder> {
    String userid,pgname;
    public Context context;
    ArrayList<model> list;


    public searchadapter(Context context, ArrayList<model> list){
        this.context = context;
        this.list=list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_search_adapter,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        model model1=list.get(i);
        holder.pgname.setText(list.get(i).getPgname());
        holder.pgaddress.setText(list.get(i).getAddress());
        holder.rent.setText(list.get(i).getRent());
        Glide.with(holder.ImageView.getContext()).load(model1.getPgimage()).into(holder.ImageView);

        holder.cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pgname = model1.getPgname();
                userid=model1.getUserid();
                Intent i = new Intent(context.getApplicationContext(), PgdetailsActivity.class);
                i.putExtra("name",pgname);
                i.putExtra("userid",userid);
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView pgname,pgaddress,rent;
        private ImageView ImageView;
        private CardView cv1;

        public MyViewHolder(View itemView){
            super(itemView);


            pgname=itemView.findViewById(R.id.pgname_search);
            pgaddress=itemView.findViewById(R.id.pgaddress_search);
            rent=itemView.findViewById(R.id.rent_search);
            ImageView = (ImageView)itemView.findViewById(R.id.imageView2);
            cv1=itemView.findViewById(R.id.cardv);
        }
    }
}
