package com.dd.pglocator.nearbyScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dd.pglocator.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class PgdetailsActivity extends AppCompatActivity {

    Switch switch1;
    String getpgid, imageurl,getpgname,getuserid;
    String pgrent,pgdeposit;
    ImageView img_profile;
    TextView tpgname, taddress,tdesc,tcontact,tbhk,tbg,tnobeds,tpgrent,tpgdeposit;
    CardView c1,c2, c3,c4;
    String f1 = "AC Room";
    String f2 = "Gym";
    String f3 = "Furniture";
    String f4 = "Food";
    String ac,gym,fur,food;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pgdetails);

        getSupportActionBar().hide();

        getpgid=getIntent().getStringExtra("id");
        getpgname=getIntent().getStringExtra("name");
        getuserid=getIntent().getStringExtra("userid");

        Toast.makeText(getApplicationContext(), getuserid, Toast.LENGTH_SHORT).show();


        switch1 = (Switch) findViewById(R.id.booking_switch);

        img_profile = findViewById(R.id.imageView_pgdetails);

        tpgname = findViewById(R.id.pgname_txt);
        taddress = findViewById(R.id.pgaddresstext);
        tdesc = findViewById(R.id.pgdesc_txt);
        tcontact = findViewById(R.id.contact_txt);
        tbhk = findViewById(R.id.bhk_txt);
        tbg = findViewById(R.id.boystxt);
        tnobeds = findViewById(R.id.beds_txt);
        tpgrent = findViewById(R.id.rent_txt);
        tpgdeposit = findViewById(R.id.deposit_txt);

        c1 = findViewById(R.id.cardview_facilities1);
        c2 = findViewById(R.id.cardview_facilities2);
        c3 = findViewById(R.id.cardView_facilities3);
        c4 = findViewById(R.id.cardview_facilities4);


        //String temppg = "pg"+getpgid;

        DatabaseReference pgRef = FirebaseDatabase.getInstance().getReference("Nearbypgs");
       // DatabaseReference pgRefchild1 = pgRef.child(temppg);

        pgRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

               // for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                    //String name=dataSnapshot.child(userid).child("pgname").getValue().toString();
                    String name = dataSnapshot.child(getuserid).child("pgname").getValue(String.class);
                    String address = dataSnapshot.child(getuserid).child("address").getValue(String.class);
                    String desc = dataSnapshot.child(getuserid).child("description").getValue(String.class);
                    String contact = dataSnapshot.child(getuserid).child("pgcontact").getValue(String.class);
                    String bhk = dataSnapshot.child(getuserid).child("bhksize").getValue(String.class);
                    String b_g = dataSnapshot.child(getuserid).child("boys_girls").getValue(String.class);
                    String nobeds = dataSnapshot.child(getuserid).child("no_of_beds").getValue(String.class);
                    pgrent = dataSnapshot.child(getuserid).child("rent").getValue(String.class);
                    pgdeposit = dataSnapshot.child(getuserid).child("deposit").getValue(String.class);
                    imageurl = dataSnapshot.child(getuserid).child("pgimage").getValue(String.class);

                     ac = dataSnapshot.child(getuserid).child("ac_noac").getValue(String.class);
                     food = dataSnapshot.child(getuserid).child("food_nofood").getValue(String.class);
                     gym = dataSnapshot.child(getuserid).child("gym_nogym").getValue(String.class);
                     fur = dataSnapshot.child(getuserid).child("fur_nofur").getValue(String.class);


                   // Toast.makeText(getApplicationContext(),"qwe "+gym,Toast.LENGTH_SHORT).show();

                CardDisplay();

//                Picasso.with(PgdetailsActivity.this)
//                        .load(imageurl)
//                        .fit()
//                        .centerCrop()
//                        .into(img_profile);
                Glide.with(PgdetailsActivity.this).load(imageurl).into(img_profile);

                    tpgname.setText(name);
                    tdesc.setText(desc);
                    tcontact.setText(contact);
                    tbhk.setText(bhk);
                    tbg.setText(b_g);
                    tnobeds.setText(nobeds);
                    tpgrent.setText(pgrent);
                    tpgdeposit.setText(pgdeposit);


            }




            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"opps.....",Toast.LENGTH_SHORT).show();
            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                Intent i = new Intent(getApplicationContext(), PgBookingActivity.class);
                i.putExtra("id",getpgid);
                i.putExtra("name",getpgname);
                i.putExtra("rent",pgrent);
                i.putExtra("deposit",pgdeposit);
                startActivity(i);
            }
        });





    }

    public void CardDisplay(){
        if(!f1.equals(ac)) {
           // Toast.makeText(getApplicationContext(),"aa "+ac,Toast.LENGTH_SHORT).show();
            c1.setVisibility(View.GONE);
        }

        if(!f2.equals(gym)) {
            c4.setVisibility(View.GONE);
        }

        if(!f3.equals(fur)) {
            c3.setVisibility(View.GONE);
        }

        if(!f4.equals(food)) {
            c2.setVisibility(View.GONE);
        }

    }
}