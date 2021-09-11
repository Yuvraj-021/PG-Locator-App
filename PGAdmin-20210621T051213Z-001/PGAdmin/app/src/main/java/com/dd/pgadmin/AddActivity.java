package com.dd.pgadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class AddActivity extends AppCompatActivity {

   EditText e1,e2,e3,e4,e5,e6,e7,e8,e9,e10;
   RadioButton r1,r2,r3,r4;
   Button b1,b2;
   RadioGroup rg1,rg2,rg3,rg4;

    private FirebaseAuth mAuth;
    FirebaseUser user;
    private static final int CHOOSE_IMAGE = 1;
    private Uri imgUrl;
    private StorageTask mUploadTask;
    public  String Imageurl;
    StorageReference storageReference;
    FirebaseStorage storage;
    String userid;

    PgDetailsmodel pgDetailsmodel;
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().hide();

        e1=findViewById(R.id.pgname);
        e2=findViewById(R.id.pgdescription);
        e3=findViewById(R.id.pgaddress);
        e4=findViewById(R.id.pgcontact);
        e5=findViewById(R.id.bhk);
        e6=findViewById(R.id.noofbeds);
        e7=findViewById(R.id.boysorgirls);
        e8=findViewById(R.id.montlyrent);
        e9=findViewById(R.id.deposit);

        pgDetailsmodel=new PgDetailsmodel();

//        r1=findViewById(R.id.rbac);
//        r2=findViewById(R.id.rbnac);
//        r3=findViewById(R.id.rbgym);
//        r4=findViewById(R.id.rbngym);
//        r5=findViewById(R.id.rbfur);
//        r6=findViewById(R.id.rbnfur);
//        r7=findViewById(R.id.rbfood);
//        r8=findViewById(R.id.rbnfood);

        rg1=findViewById(R.id.acnonac);
        rg2=findViewById(R.id.gymnogym);
        rg3=findViewById(R.id.foodnofood);
        rg4=findViewById(R.id.furnofur);

        b1=findViewById(R.id.button);
        b2=findViewById(R.id.button2);

        mAuth = FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        userid=user.getUid();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void chooseImage() {
        Intent i1 = new Intent();
        i1.setType("image/*");
        i1.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i1, CHOOSE_IMAGE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imgUrl = data.getData();
           // Picasso.with(this).load(imgUrl).into(imageView1);
        }
    }
    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }


    private void registerUser() {

        if (imgUrl != null) {
            final ProgressDialog mdialog=new ProgressDialog(AddActivity.this);
            mdialog.setMessage("Please Wait");
            mdialog.show();
            final StorageReference ref = storageReference.child("Pgimages/" + System.currentTimeMillis() + "." + GetFileExtension(imgUrl));
            mUploadTask = ref.putFile(imgUrl)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //Toast.makeText(getApplicationContext(),"sdfdsfsdfsdf",Toast.LENGTH_LONG).show();
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Imageurl = uri.toString();

                                    int a = rg1.getCheckedRadioButtonId();
                                    r1 = (RadioButton) findViewById(a);
                                    //Toast.makeText(getApplicationContext(),String.valueOf(r1.getText()),Toast.LENGTH_SHORT).show();
                                    int b = rg2.getCheckedRadioButtonId();
                                    r2 = (RadioButton) findViewById(b);
                                   // Toast.makeText(getApplicationContext(),String.valueOf(r2.getText()),Toast.LENGTH_SHORT).show();
                                    int c = rg3.getCheckedRadioButtonId();
                                    r3 = (RadioButton) findViewById(c);
                                    int d = rg4.getCheckedRadioButtonId();
                                    r4 = (RadioButton) findViewById(d);



                                    final String pgname = e1.getText().toString().trim();
                                    final String pgdescription = e2.getText().toString().trim();
                                    final String pgaddress = e3.getText().toString().trim();
                                    final String contactno = e4.getText().toString().trim();
                                    final String bhksize = e5.getText().toString().trim();
                                    final String nofbeds = e6.getText().toString().trim();
                                    final String boysorgirl = e7.getText().toString().trim();
                                    final String rent = e8.getText().toString().trim();
                                    final String deposit = e9.getText().toString().trim();
                                    final String acnoac = r1.getText().toString();
                                    final String gymnogym = r2.getText().toString();
                                    final String foodnofood = r3.getText().toString();
                                    final String furnofur = r4.getText().toString();

                                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    final DatabaseReference myref = database.getReference("Nearbypgs");
                                    pgDetailsmodel.setPgname(pgname);
                                    pgDetailsmodel.setDescription(pgdescription);
                                    pgDetailsmodel.setAddress(pgaddress);
                                    pgDetailsmodel.setContactno(contactno);
                                    pgDetailsmodel.setBhksize(bhksize);
                                    pgDetailsmodel.setNo_of_beds(nofbeds);
                                    pgDetailsmodel.setBoys_girls(boysorgirl);
                                    pgDetailsmodel.setRent(rent);
                                    pgDetailsmodel.setDeposit(deposit);
                                    pgDetailsmodel.setAc_noac(acnoac);
                                    pgDetailsmodel.setGym_nogym(gymnogym);
                                    pgDetailsmodel.setFood_nofood(foodnofood);
                                    pgDetailsmodel.setFur_nofur(furnofur);
                                    pgDetailsmodel.setUserid(userid);
                                    pgDetailsmodel.setPgimage(Imageurl);

                                    myref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(pgDetailsmodel);
                                    mdialog.dismiss();
                                    Toast.makeText(getApplicationContext(),"Added Successfully",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    })


                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        }


    }
}