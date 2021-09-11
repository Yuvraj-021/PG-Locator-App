package com.dd.pglocator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.muddzdev.styleabletoast.StyleableToast;

import org.jetbrains.annotations.NotNull;

public class LoginActivity2 extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton login;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    EditText e1,e2;
    Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        login = findViewById(R.id.fab);
        login.setOnClickListener(this);

        e1 = findViewById(R.id.editTextTextEmailAddress);
        e2 = findViewById(R.id.editTextTextPassword);

        register = findViewById(R.id.createAccount);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(LoginActivity2.this,RegisterActivity.class);
                startActivity(i1);
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        final DatabaseReference ref=database.getReference("Users");
        authStateListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull @NotNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // Toast.makeText(MainActivity.this, "User logged in ", Toast.LENGTH_SHORT).show();
                } else {
                    /*new StyleableToast.Builder(Loginpg.this)
                            .text("Login To Continue")
                            .font(R.font.merriweather)
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.RED)
                            .show();*/
                }
                new StyleableToast.Builder(LoginActivity2.this)
                        .text("Login To Continue")
                        .font(R.font.montserrat_bold)
                        .textColor(Color.WHITE)
                        .backgroundColor(Color.BLACK)
                        .show();

            }
        };
    }



    @Override
    public void onClick(View v) {
        if(v == login)
        {
           // startActivity(new Intent(LoginActivity2.this, MainActivity.class));

            final ProgressDialog mdialog=new ProgressDialog(LoginActivity2.this);
            mdialog.setMessage("Please Wait");
            mdialog.show();
            String userEmail = e1.getText().toString();
            String userPaswd = e2.getText().toString();
            if (userEmail.isEmpty() && userPaswd.isEmpty()) {
                Toast.makeText(LoginActivity2.this, "Please enter your email and password.", Toast.LENGTH_SHORT).show();
                mdialog.dismiss();
            } else if (userPaswd.isEmpty()) {
                e2.setError("password cannot be blank.");
                mdialog.dismiss();
                e2.requestFocus();
            } else if (userEmail.isEmpty()) {
                e1.setError("please enter your username.");
                mdialog.dismiss();
                e1.requestFocus();
            } else if (!(userEmail.isEmpty() && userPaswd.isEmpty())) {
                firebaseAuth.signInWithEmailAndPassword(userEmail, userPaswd).addOnCompleteListener(LoginActivity2.this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        mdialog.dismiss();
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity2.this, "Invalid email or password. Please try again.", Toast.LENGTH_SHORT).show();
                        } else {
                            //Toast.makeText(LoginActivity2.this, "Login successful.", Toast.LENGTH_SHORT).show();
                            new StyleableToast.Builder(LoginActivity2.this)
                                    .text("Login Successful")
                                    .font(R.font.montserrat_bold)
                                    .textColor(Color.WHITE)
                                    .backgroundColor(Color.BLACK)
                                    .show();
                            startActivity(new Intent(LoginActivity2.this, MainActivity.class));
                        }
                    }
                });
            } else {
                Toast.makeText(LoginActivity2.this, "Some error occured while logging in. Please try again.", Toast.LENGTH_SHORT).show();
            }

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
}