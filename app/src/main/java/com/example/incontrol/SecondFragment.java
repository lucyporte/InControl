package com.example.incontrol;

//import static androidx.core.app.AppOpsManagerCompat.Api29Impl.getSystemService;

import static com.google.android.gms.location.LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
//import androidx.core.app;
//import androidx.core.content;

import com.example.incontrol.databinding.FragmentSecondBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.CancellationToken;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

import com.google.android.gms.tasks.Task;

//import android.support.annotation.NonNull;
//import android.support.v4.app.Fragment;
//import hugo.weaving.DebugLog;
//import AppOpsManagerCompat.getSystemService;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    public String m;
    //public Location l;
    public String tstring;
    public FusedLocationProviderClient fusedLocationClient;
    public String lstring;
    public CancellationToken token;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

//        private FusedLocationProviderClient fusedLocationClient;
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//        }
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FourthFragment);
                FirebaseDatabase database = FirebaseDatabase.getInstance("https://in-control-b3e93-default-rtdb.europe-west1.firebasedatabase.app/");
                DatabaseReference mRef = database.getReference("Info/Mood");
                m = binding.buttonSecond.getText().toString().trim();
                mRef.setValue(m);
                DatabaseReference tRef = database.getReference("Info/Time");
                Date t = Calendar.getInstance().getTime();
                tstring = t.toString();
                tRef.setValue(tstring);
                @SuppressLint("MissingPermission") Task<Location> l = fusedLocationClient.getCurrentLocation(PRIORITY_BALANCED_POWER_ACCURACY, token);
                DatabaseReference lRef = database.getReference("Info/Location");
                lstring = l.toString();
                lRef.setValue(lstring);

                //task.isSuccessful

                //LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

//                Location currentLocation;
//                LocationManager locationmanager;
//                locationmanager = (LocationManager) Context.getSystemService(Context.LOCATION_SERVICE);
//                LocationManager lm;
//                Location l = lm.getLastKnownLocation()
//                LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//                LocationManager lm = (LocationManager) LocationManager.getSystemService(Context.LOCATION_SERVICE);
//                Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                LocationManager lm = LocationManager.GPS_PROVIDER;
//                location location = lm.getLastKnownLocation()
//                Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
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
            }
        );
    }
//        binding.buttonSecond.setOnClickListener(view1 -> NavHostFragment.findNavController(SecondFragment.this)
//                .navigate(R.id.action_SecondFragment_to_FourthFragment));
//        m = binding.buttonSecond.getText().toString().trim();
//        mRef.setValue(m);
//        binding.buttonSecondAlt.setOnClickListener(view1 -> NavHostFragment.findNavController(SecondFragment.this)
//                .navigate(R.id.action_SecondFragment_to_FourthFragment));
//        n = binding.buttonSecondAlt.getText().toString().trim();
//        mRef.setValue(n);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}