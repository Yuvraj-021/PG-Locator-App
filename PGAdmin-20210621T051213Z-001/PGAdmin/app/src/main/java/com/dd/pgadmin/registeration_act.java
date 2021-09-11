package com.dd.pgadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class registeration_act extends AppCompatActivity {

    EditText e1,e2,e3,e4,e5,e6,e7;
    Button b1;
    FloatingActionButton fab;

    private FirebaseAuth mAuth;
    FirebaseUser user;   // user of our app
    Pgowner_model pgowner_model;
    AwesomeValidation awesomeValidation;
    int id=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        getSupportActionBar().hide();

        e1=findViewById(R.id.reg_nametxt);
        e2=findViewById(R.id.reg_emailtext);
        e3=findViewById(R.id.reg_mobtext);
        e4=findViewById(R.id.reg_pgnametext);
        e5=findViewById(R.id.reg_addresstext);
        e6=findViewById(R.id.reg_passtext);
        e7=findViewById(R.id.reg_cpasstext);


        mAuth = FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        pgowner_model=new Pgowner_model();


        fab = findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registeruser();
//                Intent i = new Intent(registeration_act.this,MainActivity.class);
//                startActivity(i);
            }
        });
    }
    public void registeruser()
    {
        final String name = e1.getText().toString().trim();
        final String email = e2.getText().toString().trim();
        final String mobileno = e3.getText().toString().trim();
        final String pgname = e4.getText().toString().trim();
        final String address = e5.getText().toString().trim();
        final String password = e6.getText().toString().trim();
        final String con_password = e7.getText().toString().trim();

        if (name.isEmpty()) {
            e1.setError(getString(R.string.input_error_name));
            e1.requestFocus();
            return;
        }
        if (pgname.isEmpty()) {
            e4.setError(getString(R.string.input_error_Pgname));
            e4.requestFocus();
            return;
        }
        if (address.isEmpty()) {
            e5.setError(getString(R.string.input_error_address));
            e5.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            e2.setError(getString(R.string.input_error_email));
            e2.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            e2.setError(getString(R.string.input_error_email_invalid));
            e2.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            e6.setError(getString(R.string.input_error_password));
            e6.requestFocus();
            return;
        }

        if (password.length() < 6) {
            e6.setError(getString(R.string.input_error_password_length));
            e6.requestFocus();
            return;
        }

        if (mobileno.isEmpty()) {
            e3.setError(getString(R.string.input_error_phone));
            e3.requestFocus();
            return;
        }

        if (mobileno.length() != 10) {
            e3.setError(getString(R.string.input_error_phone_invalid));
            e3.requestFocus();
            return;
        }
        if (password.length() != con_password.length()) {
            e7.setError(getString(R.string.input_error_correct_password));
            e7.requestFocus();
            return;
        }
        if (con_password.length() < 6) {
            e7.setError(getString(R.string.input_error_password_length));
            e7.requestFocus();
            return;
        }
        if (con_password.isEmpty()) {
            e7.setError(getString(R.string.input_error_password));
            e7.requestFocus();
            return;
        }


        final ProgressDialog mdialog=new ProgressDialog(registeration_act.this);
        mdialog.setMessage("Please Wait");
        mdialog.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Pgowner_model user = new Pgowner_model(name,email,address,mobileno,pgname,password,con_password);
                            FirebaseDatabase.getInstance().getReference("Pgowner")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    mdialog.dismiss();
                                    if (task.isSuccessful()) {
                                        Toast.makeText(registeration_act.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                    } else {
                                        //display a failure message
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(registeration_act.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}