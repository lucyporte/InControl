package com.example.incontrol;

//import static androidx.core.app.AppOpsManagerCompat.Api29Impl.getSystemService;

import static android.content.Context.USAGE_STATS_SERVICE;
import static android.os.Parcelable.CONTENTS_FILE_DESCRIPTOR;
import static android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE;
import static androidx.core.app.AppOpsManagerCompat.*;
import static com.google.android.gms.location.LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY;

import androidx.appcompat.*;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;
import androidx.core.app.*;
import androidx.core.content.*;

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
import java.util.Map;
import java.util.Objects;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ValueEventListener;
import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;

//import android.support.annotation.NonNull;
import android.support.v4.app.*;
//import hugo.weaving.DebugLog;
//import AppOpsManagerCompat.getSystemService;
//import getSystemService;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.os.Parcel;
import java.util.SortedMap;
import java.util.TreeMap;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    public String m;
    public String tstring;
    public FusedLocationProviderClient fusedLocationClient;
    public String lstring;
    public CancellationToken token;
    public Task<Location> l;
    //public List<ActivityManager.RunningAppProcessInfo> getRunningAppProcesses;
    //public ActivityManager am;
    public String a;
    public ActivityManager.RunningAppProcessInfo amra;
    Context context = getActivity();
    public String postId;
    public String newPostId;
    public long databasenumber;
    public DatabaseReference newMoodRef;
    public DatabaseReference newTimeRef;
    public DatabaseReference newLocRef;
    public long dbnumber;
    public String dbnum;
    public DatabaseReference moodRef;
    public DatabaseReference timeRef;
    public DatabaseReference locRef;
    public String userloc;
    public String usertime;
    public static final Parcelable.Creator<ActivityManager.RecentTaskInfo> CREATOR = null;
    //public List<ActivityManager.RunningAppProcessInfo> getRunningAppProcesses ();
    public ActivityManager.RunningAppProcessInfo amrap;
    public int thingy;
    public DatabaseReference appsRef;
    public Parcel parc;
    public List<UsageStats> appList;
    public DatabaseReference mapRef;
    public SortedMap<String, UsageStats> mySortedMap;
    public String myString;
    public int i;
    public int myInt;
    public DatabaseReference myAppsRef;

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

//        List<ActivityManager.RunningAppProcessInfo> thingy = new List<RunningAppProcessInfo>() {
//        }
        //startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
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

//        ActivityManager.RunningAppProcessInfo amrap = new ActivityManager.RunningAppProcessInfo();
//        //parc = new Parcel();
//        parc = Parcel.obtain();
//        amrap.writeToParcel(parc, PARCELABLE_WRITE_RETURN_VALUE);
//        thingy = amrap.describeContents();

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
//                WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

//        ActivityManager am = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
//        List<ActivityManager.RunningAppProcessInfo> taskInfo = am.getRunningAppProcesses();

//        ActivityManager am = (ActivityManager) this;
//        List<RunningAppProcessInfo> info = am.getRunningAppProcesses();

        //val mActivityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        //val mRecentTasks: List<ActivityManager.RunningTaskInfo> = Objects.requireNonNull(mActivityManager).getRunningTasks(Int.MAX_VALUE)

        //ActivityManager am = null;
        //List<ActivityManager.RunningAppProcessInfo> appProcesses = am.getRunningAppProcesses(); // if null idk
        //List<RunningAppProcessInfo> getRunningAppProcesses = SecondFragment.this.getRunningAppProcesses;

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


//                Parcelable.Creator<RunningAppProcessInfo> creator = RunningAppProcessInfo.CREATOR;
//                Parcel parcel = ;
//                CREATOR.createFromParcel(parcel);

                    usersRef.addListenerForSingleValueEvent(new ValueEventListener() { //Close these?
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            long getchildrencount = snapshot.child(postId + "/mood").getChildrenCount();
                            long databasenumber = getchildrencount + 1;
                            newMoodRef = database.getReference("users/-NR00h0zLo7KfJoFP7M6/mood/" + databasenumber);
                            moodRef = database.getReference("users/" + postId + "/mood/" + databasenumber);
                            moodRef.setValue(m);
                            newMoodRef.setValue(m);

                            newTimeRef = database.getReference("users/-NR00h0zLo7KfJoFP7M6/time/" + databasenumber);
                            newTimeRef.setValue(tstring);

                            //appRef = database.getReference("users/" + postId + "/apps/" + databasenumber);
                            //appRef.setValue(appList);

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
                                                newLocRef = database.getReference("users/-NR00h0zLo7KfJoFP7M6/loc/" + databasenumber);
                                                newLocRef.setValue(lstring);
                                                locRef = database.getReference("users/" + postId + "/loc/" + databasenumber);
                                                if (!(userloc.equals(""))) {
                                                    locRef.setValue(userloc);
                                                } else {
                                                    locRef.setValue(lstring);
                                                }
                                            } else {
                                                lstring = "null";
                                                newLocRef = database.getReference("users/-NR00h0zLo7KfJoFP7M6/loc/" + databasenumber);
                                                newLocRef.setValue(lstring);
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

//                DatabaseReference aRef = database.getReference("Info/Apps");
//                a = taskInfo.toString();
//                if (a != null){
//                    aRef.setValue(a);
//                }
//                else{
//                    aRef.setValue("null");
//                }


                                                        //List<AndroidAppProcess> processes = AndroidProcesses.getRunningAppProcesses();

                                                        //List<ActivityManager.RunningAppProcessInfo> processes = AndroidProcesses.getRunningAppProcessInfo(context);

                                                        // ActivityManager.RunningAppProcessInfo am = new ActivityManager.RunningAppProcessInfo();
                                                        //List<ActivityManager.RunningAppProcessInfo> appProcesses = new List<ActivityManager.RunningAppProcessInfo>() {
                                                        //}.getRunningAppProcesses();


//                ActivityManager.getRunningAppProcesses
//                        .RunningTaskInfo
//                        .getRunningTasks
//                        .toString
//                        a = binding.buttonSecond.getText().toString().trim();
//                mRef.setValue(a);
//
//                am.getRunningAppProcesses;
//                List<ActivityManager.RunningAppProcessInfo> getRunningAppProcesses = new List<ActivityManager.RunningAppProcessInfo>() {
//                };
//                getRunningAppProcesses = am.getRunningAppProcesses();
//                ActivityManager.RunningAppProcessInfo.getRunningAppProcesses;
//                ActivityManager am;
//                List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();


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