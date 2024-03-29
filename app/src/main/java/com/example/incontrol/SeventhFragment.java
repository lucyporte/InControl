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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SeventhFragment extends Fragment {

    private FragmentSeventhBinding binding;
    public String textDisplay;
    public String newstring;
    public ValueEventListener listener;
    public DataSnapshot datasnap;
    public String myPostId;
    public String getname;
    public List<String> myList;
    public String postId;
    public String thisPostId;

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

        //Gets user ID

        getParentFragmentManager().setFragmentResultListener("requestKey3", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                String result = bundle.getString("bundleKey3");
                myPostId = result;
            }
        });

        //Try to add database stuff here.
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://in-control-b3e93-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference dataRef = database.getReference("users");
        myList = new ArrayList<>();
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            //Reads from the database and gets number of last database entry
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long moodcount = snapshot.child(myPostId + "/mood").getChildrenCount();
                long appcount = snapshot.child(myPostId + "/apps/" + moodcount).getChildrenCount();
                String a = "Data:\n\n";
                //Gets last 3 values of location, time, mood and note
                for (long i = moodcount+1; i-- > moodcount-2;) { //Dont know why this works
                    String getloc = snapshot.child(myPostId + "/loc/" + i).getValue(String.class);
                    String gettime = snapshot.child(myPostId + "/time/" + i).getValue(String.class);
                    String getmood = snapshot.child(myPostId + "/mood/" + i).getValue(String.class);
                    String getnote = snapshot.child(myPostId + "/note/" + i).getValue(String.class);
                    String b = "";
                    //Gets list of most used apps' name and time spent on
                    for (DataSnapshot ds: snapshot.child(myPostId + "/apps/" + i).getChildren()){
                        myList.add(ds.child("packageName").getValue().toString());
                        myList.add(ds.child("totalTimeInForeground").getValue().toString());
                    }
                    for (int k = myList.size(); k-- > myList.size() - 6;) {
                        b = b + myList.get(k);
                    }
//                    for (long j = appcount; j-- > appcount-3;) {
//                        String getapp = snapshot.child(myPostId + "/apps/" + i + "/" + j + "/totalTimeInForeground").getValue() + "";
//                        String getpackage = snapshot.child(myPostId + "/apps/" + i + "/" + j + "/packageName").getValue(String.class);
//                        b = b + getapp + " " + getpackage + " ";
//                    }
                    String getapps = snapshot.child(myPostId + "/apps/ " + i).getValue(String.class);
                    a = a + getloc + " " + gettime + " " + getmood + " " + getnote + b + "\n\n";
//                String bigstring = getloc + "\n" + gettime + "\n" + getmood + "\n" + getnote;
                }
                //Displays the data to the user
                binding.textviewSeventh.setText(a);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }
        });
        //Objects.requireNonNull(binding.textviewSeventh).setText(textDisplay);

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
                        .navigate(R.id.action_seventhFragment_to_eigthFragment);

                //Sends user ID
                thisPostId = myPostId;
                Bundle result4 = new Bundle();
                result4.putString("bundleKey4", thisPostId);
                getParentFragmentManager().setFragmentResult("requestKey4", result4);

//                postId = myPostId;
//                Bundle result5 = new Bundle();
//                result5.putString("bundleKey5", postId);
//                getParentFragmentManager().setFragmentResult("requestKey5", result5);
            }
        });
    }
}




