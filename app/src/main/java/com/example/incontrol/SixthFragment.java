package com.example.incontrol;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.incontrol.databinding.FragmentSecondBinding;
import com.example.incontrol.databinding.FragmentSixthBinding;
import com.example.incontrol.databinding.FragmentThirdBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SixthFragment extends Fragment {

    private FragmentSixthBinding binding;
    public String uname;
    public String value;
    public String postId;
    public Boolean isTherapist;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSixthBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSixth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SixthFragment.this)
                        .navigate(R.id.action_sixthFragment_to_eigthFragment);

                FirebaseDatabase database = FirebaseDatabase.getInstance("https://in-control-b3e93-default-rtdb.europe-west1.firebasedatabase.app/");
                uname = binding.edittextSixth.getText().toString().trim();

                DatabaseReference usersRef = database.getReference("users");
                usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot datakey : snapshot.getChildren()) {
                            if (datakey.child("name").getValue() != null && datakey.child("name").getValue().equals(uname)) {
                                value = datakey.getKey();
                                setVal(value);
                                break;
                            } else {
                                DatabaseReference pushRef = usersRef.push();
                                value = pushRef.getKey();
                                setVal(value);

                            }
                        }

                        postId = getVal();
                        Bundle result = new Bundle();
                        result.putString("bundleKeyyy", postId);
                        getParentFragmentManager().setFragmentResult("requestKeyyy", result);

                        isTherapist = true;
                        Bundle therresult = new Bundle();
                        therresult.putBoolean("therbundleKey", isTherapist);
                        getParentFragmentManager().setFragmentResult("therrequestKey", therresult);


                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        System.out.println("The read failed: " + error.getCode());
                    }

//        binding.buttonThirdAlt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(ThirdFragment.this)
//                        .navigate(R.id.action_ThirdFragment_to_FirstFragment);
//            }
//        });
                });

            }
        });
    }


    public void setVal(String s) {
        this.postId = s;
    }

    public String getVal() {
        return postId;
    }

    }