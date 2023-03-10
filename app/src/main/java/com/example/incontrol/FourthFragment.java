package com.example.incontrol;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;

import com.example.incontrol.databinding.FragmentFourthBinding;
import com.example.incontrol.databinding.FragmentSecondBinding;
import com.example.incontrol.databinding.FragmentThirdBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FourthFragment extends Fragment {

    private FragmentFourthBinding binding;
    public String n;
    public String newPostId;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFourthBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getParentFragmentManager().setFragmentResultListener("requestKey2", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                String result = bundle.getString("bundleKey2");
                newPostId = result;
                // Do something with the result
            }
        });

        binding.buttonFourth.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   NavHostFragment.findNavController(FourthFragment.this)
                           .navigate(R.id.action_FourthFragment_to_ThirdFragment);
                   FirebaseDatabase database = FirebaseDatabase.getInstance("https://in-control-b3e93-default-rtdb.europe-west1.firebasedatabase.app/");
                   DatabaseReference nRef = database.getReference("Info/Note");
                   n = binding.edittextFourth.getText().toString().trim();
                   nRef.setValue(n);

                   //DatabaseReference usersRef = database.getReference("users");
                   //DatabaseReference pushRef = usersRef.push();
                   //String postId = pushRef.getKey();
                   DatabaseReference noteRef = database.getReference("users/" + newPostId + "/note");
                   noteRef.setValue(n);
               }
           }
        );

        binding.buttonFourthAlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FourthFragment.this)
                        .navigate(R.id.action_fourthFragment_to_SecondFragment);
            }
        }
        );
    }
}