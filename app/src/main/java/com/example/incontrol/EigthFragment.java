package com.example.incontrol;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;

import com.example.incontrol.databinding.FragmentEigthBinding;
import com.example.incontrol.databinding.FragmentSecondBinding;
import com.example.incontrol.databinding.FragmentThirdBinding;

public class EigthFragment extends Fragment {

    private FragmentEigthBinding binding;
    public Boolean isth;
    public String postIdyy;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentEigthBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        EACH ONE ONLY USED ONCE!

        getParentFragmentManager().setFragmentResultListener("therrequestkey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                Boolean thresult = bundle.getBoolean("therbundlekey");
                isth = thresult;
                // Do something with the result
            }
        });

        getParentFragmentManager().setFragmentResultListener("requestKeyyy", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                String resultyy = bundle.getString("bundleKeyyy");
                postIdyy = resultyy;
                // Do something with the result
            }
        });

        binding.buttonEigth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EigthFragment.this)
                        .navigate(R.id.action_eigthFragment_to_secondFragment);
            }
        });

        binding.buttonEigthAlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EigthFragment.this)
                        .navigate(R.id.action_eigthFragment_to_seventhFragment);
            }
        });

        binding.buttonEigthAltAlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EigthFragment.this)
                        .navigate(R.id.action_eigthFragment_to_ninthFragment);
            }
        });

//        Logout bundle something

        binding.buttonEigthAltAltAlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EigthFragment.this)
                        .navigate(R.id.action_eigthFragment_to_fifthFragment);
            }
        });

    }
}