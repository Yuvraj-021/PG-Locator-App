package com.dd.pglocator.nearbyScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.pglocator.LoginActivity2;
import com.dd.pglocator.MainActivity;
import com.dd.pglocator.PaymentActivity;
import com.dd.pglocator.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.muddzdev.styleabletoast.StyleableToast;

public class PgBookingActivity extends AppCompatActivity {

    EditText e1, e2,e3,e4;
    TextView t1;
    int rentt, monthst, depositt;

    public String s1,s2,s5,s3,s4,pgid,pgname;
    public String rent,deposit;
    Integer is2;
    DatabaseReference myRef, refg;
    FirebaseAuth auth;
    FirebaseUser user;
    PgBookingModel pgBooking;
    long id=0;
    Switch switch1;
    String userid,custname,mobilenos,email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg_booking);
        getSupportActionBar().hide();

        e1 = findViewById(R.id.editText_guests);
        e2 = findViewById(R.id.editTextmonth);
        e3 = findViewById(R.id.editTextDate1);
        e4 = findViewById(R.id.peditTextDate2);
        t1 = findViewById(R.id.deposit_txt);

        switch1=findViewById(R.id.booking_switch);



       // Toast.makeText(getApplicationContext(),s2,Toast.LENGTH_LONG).show();

        pgid=getIntent().getStringExtra("id");
        pgname=getIntent().getStringExtra("name");
        rent=getIntent().getStringExtra("rent");
        deposit=getIntent().getStringExtra("deposit");

        TextView pgname_txt = findViewById(R.id.pgname_txt);
        pgname_txt.setText(pgname);

        auth= FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        userid=user.getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("PgBookings");

        pgBooking=new PgBookingModel();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    id=(dataSnapshot.getChildrenCount());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference myref = db.getReference("Customers");
        int id=0;
        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                //Toast.makeText(EditActivity.this,"adasdsdf",Toast.LENGTH_LONG).show();
                for (DataSnapshot npsnapshot : ds.getChildren()){

                    custname=ds.child(userid).child("customername").getValue().toString();
                    mobilenos=ds.child(userid).child("mobileno").getValue().toString();
                    email=ds.child(userid).child("email").getValue().toString();


                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(PgBookingActivity.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                savedata();
                startActivity(new Intent(PgBookingActivity.this, PaymentActivity.class));
            }
        });
    }


    public void savedata()
    {
        try {

            final ProgressDialog progressDialog = new ProgressDialog(PgBookingActivity.this);
            progressDialog.setTitle("Saving...");
            progressDialog.show();

           // Toast.makeText(getApplicationContext(), s2, Toast.LENGTH_LONG).show();

            s1 = e1.getText().toString();
            s2 = e2.getText().toString();
            s3 = e3.getText().toString();
            s4 = e4.getText().toString();
            s5 = t1.getText().toString();

             monthst = Integer.parseInt(s2);
            rentt = Integer.parseInt(rent);

            depositt = Integer.parseInt(deposit);

           // Toast.makeText(getApplicationContext(),rentnew, Toast.LENGTH_LONG).show();
            int total = 0;
            total = (rentt * monthst) + depositt;
           // total = total * Integer.parseInt(s1);
            t1.setText(String.valueOf(total)+" ₹");


            pgBooking.setPgid(pgid);
            pgBooking.setPgname(pgname);
            pgBooking.setNoofguests(s1);
            pgBooking.setPrice(String.valueOf(total));
            pgBooking.setCheckindate(s3);
            pgBooking.setCheckoutdate(s4);
            pgBooking.setCustomername(custname);
            pgBooking.setCust_mobileno(mobilenos);
            pgBooking.setCust_email(email);

            myRef.child(String.valueOf(id + 1)).setValue(pgBooking);
            progressDialog.dismiss();
           // Toast.makeText(getApplicationContext(), "Booking at PG Successfull "+total, Toast.LENGTH_SHORT).show();
            new StyleableToast.Builder(PgBookingActivity.this)
                    .text("Booking at PG Successfull")
                    .font(R.font.montserrat_bold)
                    .textColor(Color.WHITE)
                    .backgroundColor(Color.BLACK)
                    .show();


        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), "Error :"+e, Toast.LENGTH_SHORT).show();

        }

    }
}