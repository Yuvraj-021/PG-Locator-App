package com.dd.pgadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b1,b2,b3,b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        b1 = findViewById(R.id.add);
        b2 = findViewById(R.id.edit);
        b3 = findViewById(R.id.delete);
        b4 = findViewById(R.id.view);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v == b1)
        {
            Intent i = new Intent(MainActivity.this, AddActivity.class);
            startActivity(i);
        }
        if(v == b2)
        {
            Intent i = new Intent(MainActivity.this, EditActivity.class);
            startActivity(i);
        }
        if(v == b3)
        {
            Intent i = new Intent(MainActivity.this, BookingDetails.class);
            startActivity(i);
        }
        if(v == b4)
        {
            Intent i = new Intent(MainActivity.this, ViewActivity.class);
            startActivity(i);
        }

    }
}