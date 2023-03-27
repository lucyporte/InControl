package com.example.incontrol;

import static java.lang.Integer.valueOf;

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
    public String otherPostId;
    public String dbnumber;
    public int dbnum;
    public DatabaseReference newNoteRef;

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

        getParentFragmentManager().setFragmentResultListener("requestKey3", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                String result = bundle.getString("bundleKey3");
                newPostId = result;
            }
        });

        getParentFragmentManager().setFragmentResultListener("requestKeyA", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                String resulttt = bundle.getString("bundleKeyA");
                dbnumber = resulttt;
            }
        });

        binding.buttonFourth.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   NavHostFragment.findNavController(FourthFragment.this)
                           .navigate(R.id.action_fourthFragment_to_thirdFragment);
                   FirebaseDatabase database = FirebaseDatabase.getInstance("https://in-control-b3e93-default-rtdb.europe-west1.firebasedatabase.app/");
                   DatabaseReference nRef = database.getReference("Info/Note");
                   n = binding.edittextFourth.getText().toString().trim();
                   nRef.setValue(n);

                   //DatabaseReference usersRef = database.getReference("users");
                   //DatabaseReference pushRef = usersRef.push();
                   //String postId = pushRef.getKey();
                   DatabaseReference noteRef = database.getReference("users/" + newPostId + "/note/" + dbnumber);
                   noteRef.setValue(n);

                   newNoteRef = database.getReference("users/-NR00h0zLo7KfJoFP7M6/note/" + dbnumber);
                   newNoteRef.setValue(n);

                   otherPostId = newPostId;
                   Bundle result4 = new Bundle();
                   result4.putString("bundleKey4", otherPostId);
                   getParentFragmentManager().setFragmentResult("requestKey4", result4);
               }
           }
        );

//        binding.buttonFourthAlt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(FourthFragment.this)
//                        .navigate(R.id.action_fourthFragment_to_SecondFragment);
//            }
//        }
        //);
    }
}