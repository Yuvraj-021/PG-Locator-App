package com.dd.pglocator.nearbyScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.dd.pglocator.R;
import com.dd.pglocator.searchadapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    ArrayList<model> list;
    private RecyclerView rv;
    SearchView sv;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        rv = findViewById(R.id.recyclerview_search);
        sv = findViewById(R.id.searchView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(SearchActivity.this));

        ref = FirebaseDatabase.getInstance().getReference().child("Nearbypgs");
    }


    @Override
    public void onStart(){
        super.onStart();
        if (ref != null) {
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        list = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            list.add(ds.getValue(model.class));
                        }
                        searchadapter adapterClass = new searchadapter(SearchActivity.this,list);
                        rv.setAdapter(adapterClass);
                        rv.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(SearchActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (sv != null) {
            sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }
    }


    private void search(String str) {
        ArrayList<model> mylist = new ArrayList<>();
        for (model object : list) {
            if (object.getPgname().contains(str) ||object.getAddress().contains(str) ||object.getRent().contains(str)) {
                mylist.add(object);
            }

        }
        searchadapter adapterClass = new searchadapter(SearchActivity.this,mylist);
        rv.setAdapter(adapterClass);
    }
}