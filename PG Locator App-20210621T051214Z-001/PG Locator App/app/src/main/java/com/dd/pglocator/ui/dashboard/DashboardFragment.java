package com.dd.pglocator.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dd.pglocator.R;
import com.dd.pglocator.dashboard_adapter;
import com.dd.pglocator.databinding.FragmentDashboardBinding;
import com.dd.pglocator.nearbyScreen.PgBookingModel;
import com.dd.pglocator.nearbyScreen.model;
import com.dd.pglocator.nearbyScreen.myadapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    String emailg;
    public String userid;

    ArrayList<PgBookingModel>listData;
    private RecyclerView rv;
    dashboard_adapter adapter1;
    FloatingActionButton fab;
    RecyclerView recview;
    FirebaseDatabase fb;
    FirebaseAuth auth;
    PgBookingModel pgBookingModel;

    TextView t1,t2,t3,t4,t5,t6;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recview = (RecyclerView) root.findViewById(R.id.recyclerview213);
        recview.setHasFixedSize(true);
        recview.setLayoutManager(new LinearLayoutManager(container.getContext()));
        listData=new ArrayList<PgBookingModel>();

        fb = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        userid=user.getUid();
        //Toast.makeText(getActivity(),userid,Toast.LENGTH_LONG).show();
        pgBookingModel=new PgBookingModel();

        final DatabaseReference ref= fb.getReference().child("Customers");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                    emailg=dataSnapshot.child(userid).child("email").getValue().toString();
                    Toast.makeText(getActivity(),emailg,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(),"opps.....",Toast.LENGTH_SHORT).show();
            }
        });
        //Toast.makeText(getActivity(),emailg,Toast.LENGTH_LONG).show();

        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference().child("PgBookings");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                    PgBookingModel l=npsnapshot.getValue(PgBookingModel.class);
                    //Toast.makeText(getActivity(),emailg,Toast.LENGTH_LONG).show();
                    String cust_email=l.getCust_email();
                    if(cust_email.equals(emailg))
                    {
                        listData.add(l);
                    }
                }
                adapter1=new dashboard_adapter(getActivity(),listData);
                recview.setAdapter(adapter1);
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