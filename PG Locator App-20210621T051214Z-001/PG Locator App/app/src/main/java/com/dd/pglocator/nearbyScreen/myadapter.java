package com.dd.pglocator.nearbyScreen;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dd.pglocator.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class myadapter extends RecyclerView.Adapter<myadapter.ViewHolder>
{
    String pgid,pgname;
    DatabaseReference myRef;
    FirebaseUser user;
    public Context context;
   // model model=new model();
    FirebaseAuth auth;
    String userid;
   // String companynm;
   ArrayList<model> listData;
    int pos;


    public myadapter(Context context, ArrayList<model> listData) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        model model1=listData.get(position);
        holder.name.setText(model1.getPgname());
        holder.address.setText(model1.getAddress());
        holder.actext.setText(model1.getAc_noac());
        holder.renttext.setText(model1.getRent() +" ₹");
        holder.boysgirlstext.setText(model1.getBoys_girls());
        holder.bhksize.setText(model1.getBhksize() + " with " +model1.getNo_of_beds() + " beds.");


        //holder.email.setText(model.getName());
        Glide.with(holder.img.getContext()).load(model1.getPgimage()).into(holder.img);

        holder.cardclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pgname = model1.getPgname();
                userid=model1.getUserid();
                Intent i = new Intent(context.getApplicationContext(),PgdetailsActivity.class);
                i.putExtra("name",pgname);
                i.putExtra("userid",userid);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView name,address,actext, boysgirlstext, renttext, bhksize, no_of_beds;
        LinearLayout cardclick;

        public ViewHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            name=(TextView)itemView.findViewById(R.id.nametext);
            address=(TextView)itemView.findViewById(R.id.pgaddresstext);
            actext=(TextView)itemView.findViewById(R.id.actext);
            boysgirlstext=(TextView)itemView.findViewById(R.id.boysgirlstext);
            renttext=(TextView)itemView.findViewById(R.id.monthlyrenttext);
            bhksize=(TextView)itemView.findViewById(R.id.bhkbedstext);
            cardclick = itemView.findViewById(R.id.card_click);

        }
    }
}
