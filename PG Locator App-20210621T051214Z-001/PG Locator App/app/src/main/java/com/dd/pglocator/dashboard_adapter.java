package com.dd.pglocator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dd.pglocator.nearbyScreen.PgBookingModel;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class dashboard_adapter extends RecyclerView.Adapter<dashboard_adapter.ViewHolder> {
        public Context context;
        ArrayList<PgBookingModel> listData;

        public dashboard_adapter(Context context, ArrayList<PgBookingModel> listData) {
            this.listData = listData;
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_dashboard_adapter, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            PgBookingModel pgBookingModel = listData.get(position);
            holder.t1.setText(pgBookingModel.getPgname());
            holder.t2.setText(pgBookingModel.getCheckindate());
            holder.t3.setText(pgBookingModel.getCheckoutdate());
            holder.t4.setText(pgBookingModel.getNoofguests());
            holder.t5.setText(pgBookingModel.getPrice());
        }

        @Override
        public int getItemCount() {
            return listData.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView t1,t2,t3,t4,t5;

            public ViewHolder(View itemView) {
                super(itemView);

                t1=itemView.findViewById(R.id.textView29);
                t2=itemView.findViewById(R.id.pgcheckin_dash);
                t3=itemView.findViewById(R.id.pgcheckout_dash);
                t4=itemView.findViewById(R.id.months_dash);
                t5=itemView.findViewById(R.id.total_dash);
            }
        }
    }
