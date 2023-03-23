package com.example.incontrol;

import static android.content.Context.USAGE_STATS_SERVICE;
import static com.google.android.gms.location.LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;

import com.example.incontrol.databinding.FragmentSecondBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ValueEventListener;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;

import android.app.ActivityManager.RunningAppProcessInfo;
import java.util.SortedMap;
import java.util.TreeMap;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    public String m;
    public String tstring;
    public FusedLocationProviderClient fusedLocationClient;
    public String lstring;
    public CancellationToken token;
    public String a;
    public String postId;
    public String newPostId;
    public long dbnumber;
    public DatabaseReference moodRef;
    public DatabaseReference timeRef;
    public DatabaseReference locRef;
    public String userloc;
    public String usertime;
    public ActivityManager.RunningAppProcessInfo amrap;
    public DatabaseReference appsRef;
    public List<UsageStats> appList;
    public SortedMap<String, UsageStats> mySortedMap;
    public String myString;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                String result = bundle.getString("bundleKey");
                postId = result;
                // Do something with the result
            }
        });

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity());

        startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
        UsageStatsManager usm = (UsageStatsManager) this.requireContext().getSystemService(USAGE_STATS_SERVICE);
        appList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, System.currentTimeMillis() - 1000 * 60, System.currentTimeMillis());
        if (appList != null && appList.size() > 0) {
            mySortedMap = new TreeMap<>();
            for (UsageStats usageStats : appList) {
                myString = usageStats.getTotalTimeInForeground() + "";
                mySortedMap.put(myString, usageStats);
            }
        }
//        myInt = 1;
//        FirebaseDatabase database = FirebaseDatabase.getInstance("https://in-control-b3e93-default-rtdb.europe-west1.firebasedatabase.app/");
//        for (Map.Entry<String, UsageStats>
//                entry : mySortedMap.entrySet()){
//            myAppsRef = database.getReference("users/" + postId + "/apps/" + databasenumber + "/" + myInt);
//            myAppsRef.setValue(entry.getValue());
//            myInt ++;

        //UsageStatsManager usm = null;
        //if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
        //    usm = (UsageStatsManager) this.requireContext().getSystemService(USAGE_STATS_SERVICE); //Activity
        //}


        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavHostFragment.findNavController(SecondFragment.this)
                            .navigate(R.id.action_SecondFragment_to_FourthFragment);
                    FirebaseDatabase database = FirebaseDatabase.getInstance("https://in-control-b3e93-default-rtdb.europe-west1.firebasedatabase.app/");

                    DatabaseReference usersRef = database.getReference("users");

                    m = binding.buttonSecond.getText().toString().trim();

                    Date t = Calendar.getInstance().getTime();
                    tstring = t.toString();

                    userloc = binding.edittextSecond.getText().toString().trim();
                    usertime = binding.edittextSecondAlt.getText().toString().trim();

                    usersRef.addListenerForSingleValueEvent(new ValueEventListener() { //Close these?
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            long getchildrencount = snapshot.child(postId + "/mood").getChildrenCount();
                            long databasenumber = getchildrencount + 1;
                            moodRef = database.getReference("users/" + postId + "/mood/" + databasenumber);
                            moodRef.setValue(m);

                            appsRef = database.getReference("users/" + postId + "/apps/" + databasenumber);
                            //appsRef.setValue(appList);
                            appsRef.setValue(mySortedMap);

                            timeRef = database.getReference("users/" + postId + "/time/" + databasenumber);
                            if (!(usertime.equals(""))) {
                                timeRef.setValue(usertime);
                            } else {
                                timeRef.setValue(tstring);
                            }
                            setnumber(databasenumber);

                            String dbnum = databasenumber + "";
                            Bundle resulttt = new Bundle();
                            resulttt.putString("bundleKeyA", dbnum);
                            getParentFragmentManager().setFragmentResult("requestKeyA", resulttt);


                            @SuppressLint("MissingPermission") Task<Location> l = fusedLocationClient.getCurrentLocation(PRIORITY_BALANCED_POWER_ACCURACY, token)
                                    .addOnSuccessListener(requireActivity(), new OnSuccessListener<Location>() {
                                        @Override
                                        public void onSuccess(Location location) {
                                            if (location != null) {
                                                lstring = location.toString();
                                                locRef = database.getReference("users/" + postId + "/loc/" + databasenumber);
                                                if (!(userloc.equals(""))) {
                                                    locRef.setValue(userloc);
                                                } else {
                                                    locRef.setValue(lstring);
                                                }
                                            } else {
                                                lstring = "null";
                                                locRef = database.getReference("users/" + postId + "/loc/" + databasenumber);
                                                if (!(userloc.equals(""))) {
                                                    locRef.setValue(userloc);
                                                } else {
                                                    locRef.setValue(lstring);
                                                }
                                            }
                                        }
                                    });


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            System.out.println("The read failed: " + error.getCode());
                        }
                    });


                    newPostId = postId;
                    Bundle result = new Bundle();
                    result.putString("bundleKey2", newPostId);
                    getParentFragmentManager().setFragmentResult("requestKey2", result);


            }
        }
);

        binding.buttonSecondAlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FourthFragment);
                FirebaseDatabase database = FirebaseDatabase.getInstance("https://in-control-b3e93-default-rtdb.europe-west1.firebasedatabase.app/");
                DatabaseReference mRef = database.getReference("Info/Mood");
                m = binding.buttonSecondAlt.getText().toString().trim();
                mRef.setValue(m);
                DatabaseReference tRef = database.getReference("Info/Time");
                Date t = Calendar.getInstance().getTime();
                tstring = t.toString();
                tRef.setValue(tstring);
            }
        });

    }

        public void setnumber (long n){
            this.dbnumber = n;
        }

        public long getnumber () {
            return dbnumber;
        }

        @Override
        public void onDestroyView () {
            super.onDestroyView();
            binding = null;
        }

        public RunningAppProcessInfo getAamm () {
            return amrap;
        }

        public void setAamm (RunningAppProcessInfo amrap){
            this.amrap = amrap;
        }
}




//        private FusedLocationProviderClient fusedLocationClient;
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//        }

//task.isSuccessful

//LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);



//        binding.buttonSecond.setOnClickListener(view1 -> NavHostFragment.findNavController(SecondFragment.this)
//                .navigate(R.id.action_SecondFragment_to_FourthFragment));
//        m = binding.buttonSecond.getText().toString().trim();
//        mRef.setValue(m);
//        binding.buttonSecondAlt.setOnClickListener(view1 -> NavHostFragment.findNavController(SecondFragment.this)
//                .navigate(R.id.action_SecondFragment_to_FourthFragment));
//        n = binding.buttonSecondAlt.getText().toString().trim();
//        mRef.setValue(n);


//                DatabaseReference moodRef = database.getReference("users/-NR00h0zLo7KfJoFP7M6/mood");
//                        DatabaseReference newLocRef = database.getReference("users/-NR00h0zLo7KfJoFP7M6/loc" + databasenumber);
//                        newLocRef.setValue(lstring);

//                        newPostId = postId;


//                                    DatabaseReference lRef = database.getReference("Info/Location");
//                                    lRef.setValue(lstring);
//                                            DatabaseReference locRef = database.getReference("users/" + postId + "/loc");
//                                            locRef.setValue(lstring);
//                                    DatabaseReference lRef = database.getReference("Info/Location");
//                                    lRef.setValue(lstring);
//                                            DatabaseReference locRef = database.getReference("users/" + postId + "/loc");
//                                            locRef.setValue(lstring);

//                DatabaseReference mRef = database.getReference("Info/Mood");

//                mRef.setValue(m);

//                DatabaseReference tRef = database.getReference("Info/Time");

//                tRef.setValue(tstring);