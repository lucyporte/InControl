package com.example.incontrol;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;

import com.example.incontrol.databinding.FragmentSecondBinding;
import com.example.incontrol.databinding.FragmentSeventhBinding;
import com.example.incontrol.databinding.FragmentThirdBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class SeventhFragment extends Fragment {

    private FragmentSeventhBinding binding;
    public String textDisplay;
    public String newstring;
    public ValueEventListener listener;
    public DataSnapshot datasnap;
    public String myPostId;
    public String getname;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSeventhBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getParentFragmentManager().setFragmentResultListener("requestKey3", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                String result = bundle.getString("bundleKey3");
                myPostId = result;
                // Do something with the result
            }
        });

        //Try to add database stuff here.
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://in-control-b3e93-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference dataRef = database.getReference("users");
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String getloc = snapshot.child(myPostId+"/loc").getValue(String.class);
                String gettime = snapshot.child(myPostId+"/time").getValue(String.class);
                String getmood = snapshot.child(myPostId+"/mood").getValue(String.class);
                String getnote = snapshot.child(myPostId+"/note").getValue(String.class);
                String bigstring = getloc + "\n" + gettime + "\n" + getmood + "\n" + getnote;
                binding.textviewSeventh.setText(bigstring);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }
        });

        textDisplay = "hello";
        //Objects.requireNonNull(binding.textviewSeventh).setText(textDisplay);
        newstring = "Ooohh";

        //getname = datasnap.child(myPostId).child("name").getValue(String.class);

//        // Attach a listener to read the data at our posts reference
//        listener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//            }
//        };
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Post post = dataSnapshot.getValue(Post.class);
//                System.out.println(post);
//            }
//
//        DataSnapshot datasnap = new DataSnapshot();



        //binding.textviewSeventh.setText("Wahhh");
        //m = binding.buttonSecond.getText().toString().trim();

        //listener
        //view
        //adding the vars...

        binding.buttonSeventh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SeventhFragment.this)
                        .navigate(R.id.action_seventhFragment_to_FirstFragment);
            }
        });
    }
}




