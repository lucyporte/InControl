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
    public boolean isther;
    public String postId;
    public String isTherapist;
    public String therCheck1 = "no";
    public String therCheck2 = "no";
    public String isTherapist2;
    public boolean TherFlag;

    //Can only open each bundle once...

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

        //Menu page

//        EACH ONE ONLY USED ONCE!

        //Gets therapist flag

        getParentFragmentManager().setFragmentResultListener("requestKeyTh", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKeyTh, @NonNull Bundle bundle) {
                String resultTher = bundle.getString("bundleKeyTh");
                setTher(resultTher);
                //therCheck1 = resultTher;
            }
        });

//        if ((therCheck1 != null && therCheck1.equals("yes")) || (therCheck2 != null && therCheck2.equals("yes"))){
//            setTher("yes");
//        }

        //Check-in link

        binding.buttonEigth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((isTherapist != null) && isTherapist.equals("yes")){
                    NavHostFragment.findNavController(EigthFragment.this)
                            .navigate(R.id.action_eigthFragment_to_sixthFragment);
                    TherFlag = true;
                }
                else {
                    NavHostFragment.findNavController(EigthFragment.this)
                            .navigate(R.id.action_eigthFragment_to_secondFragment);
                    TherFlag = false;
                }
//                Bundle result2 = new Bundle();
//                result2.putString("bundleKey2", postId);
//                getParentFragmentManager().setFragmentResult("requestKey2", result2);
            }
        });

        //Stats link

        binding.buttonEigthAlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EigthFragment.this)
                        .navigate(R.id.action_eigthFragment_to_seventhFragment);
            }
        });

        //Messages link

        binding.buttonEigthAltAlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TherFlag == true){
                    NavHostFragment.findNavController(EigthFragment.this)
                            .navigate(R.id.action_eigthFragment_to_tenthFragment);
                }
                else {
                    NavHostFragment.findNavController(EigthFragment.this)
                            .navigate(R.id.action_eigthFragment_to_ninthFragment);
                }
            }
        });

        //Logout

        binding.buttonEigthAltAltAlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EigthFragment.this)
                        .navigate(R.id.action_eigthFragment_to_fifthFragment);
            }
        });

//        Logout bundle something

//        Bundle result7th = new Bundle();
//        result7th.putString("bundleKey7th", postId);
//        getParentFragmentManager().setFragmentResult("requestKey7th", result7th);
//
//        Bundle result9th = new Bundle();
//        result9th.putString("bundleKey9th", postId);
//        getParentFragmentManager().setFragmentResult("requestKey9th", result9th);

//        Bundle resultC = new Bundle();
//        resultC.putBoolean("bundleKeyC", isther);
//        getParentFragmentManager().setFragmentResult("requestKeyC", resultC);

//        Bundle therresult9th = new Bundle();
//        therresult9th.putBoolean("therbundleKey9th", isther);
//        getParentFragmentManager().setFragmentResult("therrequestKey9th", therresult9th);

    }

    /*** Sets val.** @param s the s*/
    public void setTher(String t) {this.isTherapist = t;}

    /*** Gets val.** @return val*/
    public String getTher() {
        return isTherapist;
    }

    /*** Sets val.** @param s the s*/
    public void setTher2(String t) {this.isTherapist2 = t;}

    /*** Gets val.** @return val*/
    public String getTher2() {
        return isTherapist2;
    }

//    public void setTher(boolean t) {
//        this.isther = t;
//    }
//
//    public boolean getTher() {
//        return isther;
//    }
//
//    public void setPost(String s){
//        this.postId = s;
//    }
//
//    public String getPost(){
//        return postId;
//    }
}