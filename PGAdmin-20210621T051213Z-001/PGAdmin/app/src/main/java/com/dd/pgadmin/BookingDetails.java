package com.dd.pgadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class BookingDetails extends AppCompatActivity {

    ArrayList<PgBookingModel> listData;
    private RecyclerView rv;
    private Booking_adapter adapter;

    FirebaseUser user;
    FirebaseAuth auth;
    String userid;
    String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        getSupportActionBar().hide();

        rv=findViewById(R.id.recycler_view1);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listData= new ArrayList<PgBookingModel>();

        auth= FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        userid=user.getUid();


        final DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Pgowner");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                    a=dataSnapshot.child(userid).child("pgname").getValue().toString();

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"opps.....",Toast.LENGTH_SHORT).show();
            }
        });


        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference().child("PgBookings");

        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                    //Toast.makeText(getApplicationContext(),a,Toast.LENGTH_SHORT).show();
                    PgBookingModel l=npsnapshot.getValue(PgBookingModel.class);
                    String b=l.getPgname();
                   // Toast.makeText(getApplicationContext(),b,Toast.LENGTH_SHORT).show();
                    if(b.equals(a))
                        listData.add(l);
                }
                adapter= new Booking_adapter(getApplicationContext(),listData);
                rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"opps.....",Toast.LENGTH_SHORT).show();
            }
        });
    }
}