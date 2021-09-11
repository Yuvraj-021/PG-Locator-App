package com.dd.pgadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    String userid;
    ImageView image;
    private FirebaseAuth mAuth;
    FirebaseUser user;

    private TextView e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12,e13;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        getSupportActionBar().hide();

        image=findViewById(R.id.imageview1);
        e1=findViewById(R.id.pgname_view);
        e2=findViewById(R.id.address_view);
        e3=findViewById(R.id.description_view);
        e4=findViewById(R.id.mobileno_view);
        e5=findViewById(R.id.bhk_view);
        e6=findViewById(R.id.noofbeds_view);
        e7=findViewById(R.id.boysgirl_view);
        e8=findViewById(R.id.rent_view);
        e9=findViewById(R.id.deposit_view);
        e10=findViewById(R.id.ac_view);
        e11=findViewById(R.id.gym_view);
        e12=findViewById(R.id.furnofur_view);
        e13=findViewById(R.id.food_view);

        mAuth = FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        userid=user.getUid();



        final FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference myref = db.getReference("Nearbypgs");
        int id=0;
        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                //Toast.makeText(EditActivity.this,"adasdsdf",Toast.LENGTH_LONG).show();
                for (DataSnapshot npsnapshot : ds.getChildren()){

                    String pgname=ds.child(userid).child("pgname").getValue().toString();
                    String description=ds.child(userid).child("description").getValue().toString();
                    String address=ds.child(userid).child("address").getValue().toString();
                    String bhksize=ds.child(userid).child("bhksize").getValue().toString();
                    String noofbeds=ds.child(userid).child("no_of_beds").getValue().toString();
                    String rent=ds.child(userid).child("rent").getValue().toString();
                    String deposit=ds.child(userid).child("deposit").getValue().toString();
                    String boys_girls=ds.child(userid).child("boys_girls").getValue().toString();
                    String mobileno=ds.child(userid).child("contactno").getValue().toString();
                    String imageurl=ds.child(userid).child("pgimage").getValue().toString();
                    String acnoac=ds.child(userid).child("ac_noac").getValue().toString();
                    String gymnogym=ds.child(userid).child("gym_nogym").getValue().toString();
                    String foodnofood=ds.child(userid).child("food_nofood").getValue().toString();
                    String furnofur=ds.child(userid).child("fur_nofur").getValue().toString();

                    e1.setText(pgname);
                    e3.setText(description);
                    e2.setText(address);
                    e4.setText(mobileno);
                    e5.setText(bhksize);
                    e6.setText(noofbeds);
                    e7.setText(boys_girls);
                    e8.setText(rent);
                    e9.setText(deposit);
                    e10.setText(acnoac);
                    e11.setText(gymnogym);
                    e12.setText(furnofur);
                    e13.setText(foodnofood);

                    Picasso.with(ViewActivity.this)
                            .load(imageurl)
                            .fit()
                            .centerCrop()
                            .into(image);





                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ViewActivity.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
}