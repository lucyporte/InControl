package com.example.incontrol;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;

import com.example.incontrol.databinding.FragmentSecondBinding;
import com.example.incontrol.databinding.FragmentSixthBinding;
import com.example.incontrol.databinding.FragmentThirdBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * The type Sixth fragment.
 */
public class SixthFragment extends Fragment {

    private FragmentSixthBinding binding;
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
    public String otherPostId;
    public boolean isTherapist;
    public String isATher;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSixthBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    //Check-in page

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createNotificationChannel();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "channelId")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Check-in alert!")
                .setContentText("Daily check-in reminder")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(requireContext());
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, builder.build());

        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                String result = bundle.getString("bundleKey");
                postId = result;
            }
        });

        binding.buttonSixth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        NavHostFragment.findNavController(SixthFragment.this)
                                .navigate(R.id.action_sixthFragment_to_eigthFragment);

                        //Sends user ID
                        otherPostId = postId;
                        Bundle result3 = new Bundle();
                        result3.putString("bundleKey3", otherPostId);
                        getParentFragmentManager().setFragmentResult("requestKey3", result3);

                        //Bundle used to pass therapist flag
                        isATher = "yes";
                        Bundle resultTh2 = new Bundle();
                        resultTh2.putString("bundleKeyTh2", isATher);
                        getParentFragmentManager().setFragmentResult("requestKeyTh2", resultTh2);
                    }
                }
        );

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



        private void createNotificationChannel() {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = "myChannel";
                String description = "notifChannel";
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel("channelId", name, importance);
                channel.setDescription(description);
                // Register the channel with the system; you can't change the importance
                // or other notification behaviors after this
                NotificationManager notificationManager = requireContext().getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
            }
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