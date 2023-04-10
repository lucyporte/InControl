package com.example.incontrol;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.incontrol.databinding.FragmentTenthBinding;
import com.example.incontrol.databinding.FragmentTenthBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * The type Sixth fragment.
 */
public class TenthFragment extends Fragment {

    private FragmentTenthBinding binding;
    /**
     * The Uname.
     */
    public String uname;
    /**
     * The Value.
     */
    public String value;
    /**
     * The Post id.
     */
    public String postId;
    /**
     * The Is therapist.
     */
    public boolean isTherapist;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentTenthBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        binding.buttonSixth.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(SixthFragment.this)
//                        .navigate(R.id.action_sixthFragment_to_eigthFragment);
//
//                FirebaseDatabase database = FirebaseDatabase.getInstance("https://in-control-b3e93-default-rtdb.europe-west1.firebasedatabase.app/");
//                uname = binding.edittextSixth.getText().toString().trim();
//
//                DatabaseReference usersRef = database.getReference("users");
//                usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for (DataSnapshot datakey : snapshot.getChildren()) {
//                            if (datakey.child("name").getValue() != null && datakey.child("name").getValue().equals(uname)) {
//                                value = datakey.getKey();
//                                setVal(value);
//                                break;
//                            } else {
//                                DatabaseReference pushRef = usersRef.push();
//                                value = pushRef.getKey();
//                                setVal(value);
//
//                            }
//                        }
//
//                        postId = getVal();
//                        Bundle result6 = new Bundle();
//                        result6.putString("bundleKey6", postId);
//                        getParentFragmentManager().setFragmentResult("requestKey6", result6);
//
//                        isTherapist = true;
//                        Bundle resultB = new Bundle();
//                        resultB.putBoolean("bundleKeyB", isTherapist);
//                        getParentFragmentManager().setFragmentResult("requestKeyB", resultB);
//
//
//                    }
//
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        System.out.println("The read failed: " + error.getCode());
//                    }

//        binding.buttonThirdAlt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(ThirdFragment.this)
//                        .navigate(R.id.action_ThirdFragment_to_FirstFragment);
//            }
//        });
//                });
//
//            }
//        });
    }


//    /**
//     * Sets val.
//     *
//     * @param s the s
//     */
//    public void setVal(String s) {
//        this.postId = s;
//    }
//
//    /**
//     * Gets val.
//     *
//     * @return the val
//     */
//    public String getVal() {
//        return postId;
//    }

}