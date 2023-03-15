package com.example.incontrol;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.incontrol.databinding.FragmentSecondBinding;
import com.example.incontrol.databinding.FragmentSeventhBinding;
import com.example.incontrol.databinding.FragmentThirdBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SeventhFragment extends Fragment {

    private FragmentSeventhBinding binding;
    public String textDisplay;
    public String newstring;

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

        //Try to add database stuff here.
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://in-control-b3e93-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference dataRef = database.getReference("Users");
        textDisplay = "hello";
        //Objects.requireNonNull(binding.textviewSeventh).setText(textDisplay);
        newstring = "Ooohh";
        binding.textviewSeventh.setText(newstring);
        //binding.textviewSeventh.setText("Wahhh");
        //m = binding.buttonSecond.getText().toString().trim();

        //listener
        //view
        //adding the vars...

        binding.buttonSeventhAlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SeventhFragment.this)
                        .navigate(R.id.action_seventhFragment_to_FirstFragment);
            }
        });
    }
}




