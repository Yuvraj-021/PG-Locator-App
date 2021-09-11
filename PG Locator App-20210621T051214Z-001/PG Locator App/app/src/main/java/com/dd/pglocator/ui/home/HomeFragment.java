package com.dd.pglocator.ui.home;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Fade;
import androidx.transition.Slide;
import androidx.transition.Transition;

import com.dd.pglocator.PaymentActivity;
import com.dd.pglocator.R;
import com.dd.pglocator.databinding.FragmentHomeBinding;


import com.dd.pglocator.nearbyScreen.PgdetailsActivity;
import com.dd.pglocator.nearbyScreen.SearchActivity;
import com.dd.pglocator.nearbyScreen.model;
import com.dd.pglocator.nearbyScreen.myadapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    //private List<ListData>listData;
    ArrayList<model>listData;
    private RecyclerView rv;
    myadapter adapter;
    FloatingActionButton fab;
    RecyclerView recview;
    FirebaseDatabase fb;
    FirebaseAuth auth;
    model model1;
    SearchView sv;
    ImageView i1;
    private DatabaseReference mUserDatabase;
    //public static int posi;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        BottomNavigationView navBar = getActivity().findViewById(R.id.nav_view);

        recview = (RecyclerView) root.findViewById(R.id.recyclerview);
        i1=root.findViewById(R.id.imageView1_home);

        mUserDatabase = FirebaseDatabase.getInstance().getReference("Nearbypgs"); //.child("UserProfile");
        recview.setHasFixedSize(true);
        recview.setLayoutManager(new LinearLayoutManager(container.getContext()));
        //recview.addItemDecoration(new SpacesItemDecoration(mMargin));
        listData=new ArrayList<model>();

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"fsf",Toast.LENGTH_LONG).show();
                Intent i = new Intent(getActivity(), SearchActivity.class);
                startActivity(i);
            }
        });

        fb = FirebaseDatabase.getInstance();

        auth = FirebaseAuth.getInstance();
        //img.setBackgroundResource(images[b]);
        model1=new model();
        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference().child("Nearbypgs");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                    model l=npsnapshot.getValue(model.class);
                    listData.add(l);

                }
                adapter=new myadapter(getActivity(),listData);
                recview.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(),"opps.....",Toast.LENGTH_SHORT).show();
            }
        });


        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}