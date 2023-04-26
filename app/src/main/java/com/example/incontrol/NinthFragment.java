package com.example.incontrol;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;

import com.example.incontrol.databinding.FragmentNinthBinding;
import com.example.incontrol.databinding.FragmentSecondBinding;
import com.example.incontrol.databinding.FragmentThirdBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NinthFragment extends Fragment {

    private FragmentNinthBinding binding;
    public String thePostId;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentNinthBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    //Messages page

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getParentFragmentManager().setFragmentResultListener("requestKey4", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                String result = bundle.getString("bundleKey4");
                thePostId = result;
            }
        });

        //Try to add database stuff here.
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://in-control-b3e93-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference dataRef = database.getReference("users");
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            //Reads from the database and gets number of last database entry
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String getmess = snapshot.child(thePostId + "/mess").getValue(String.class);
                //Displays the data to the user
                binding.textviewNinth.setText(getmess);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }
        });

//        getParentFragmentManager().setFragmentResultListener("requestKey5", this, new FragmentResultListener() {
//            @Override
//            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
//                String result = bundle.getString("bundleKey5");
//                thePostId = result;
//            }
//        });

        binding.buttonNinth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(NinthFragment.this)
                        .navigate(R.id.action_ninthFragment_to_eigthFragment);
            }
        });

//        binding.buttonThirdAlt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(ThirdFragment.this)
//                        .navigate(R.id.action_ThirdFragment_to_FirstFragment);
//            }
//        });
    }
}