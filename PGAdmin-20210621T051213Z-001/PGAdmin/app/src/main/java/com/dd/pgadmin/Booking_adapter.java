package com.dd.pgadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import com.squareup.picasso.Picasso;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Booking_adapter extends RecyclerView.Adapter<Booking_adapter.ViewHolder> {
    public Context context;
    ArrayList<PgBookingModel> listData;

    public Booking_adapter(Context context, ArrayList<PgBookingModel> listData) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_booking_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PgBookingModel pgBookingModel = listData.get(position);
        holder.t1.setText(pgBookingModel.getCustomername());
        holder.t2.setText(pgBookingModel.getPgname());
        holder.t3.setText(pgBookingModel.getNoofguests());
        holder.t4.setText(pgBookingModel.getCust_mobileno());
        holder.t5.setText(pgBookingModel.getCheckindate());
        holder.t6.setText(pgBookingModel.getCheckoutdate());
        holder.t7.setText(pgBookingModel.getPrice());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView t1,t2,t3,t4,t5,t6,t7;
        DatabaseReference myRef, demoRef;

        public ViewHolder(View itemView) {
            super(itemView);

            t1=itemView.findViewById(R.id.custname);
            t2=itemView.findViewById(R.id.pgname);
            t3=itemView.findViewById(R.id.noofguests);
            t4=itemView.findViewById(R.id.mobileno);
            t5=itemView.findViewById(R.id.checkin);
            t6=itemView.findViewById(R.id.checkoutdate);
            t7=itemView.findViewById(R.id.price);
        }
    }
}

