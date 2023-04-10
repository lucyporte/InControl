package com.example.incontrol;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.appcompat.app.AppCompatActivity;
import com.example.incontrol.databinding.FragmentFifthBinding;
import com.example.incontrol.databinding.FragmentFourthBinding;
import com.example.incontrol.databinding.FragmentSecondBinding;
import com.example.incontrol.databinding.FragmentThirdBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

/**
 * The type Fifth fragment.
 */
public class FifthFragment extends Fragment {

    /*** The Binding.*/
    public FragmentFifthBinding binding;
    /*** The Uname.*/
    public String uname = "Hi";
    /*** The Pword.*/
    public String pword;
    /*** The Post id.*/
    public String postId;
    /*** The Value.*/
    public String value;
    public String isTher = "no";

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFifthBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setTher("no");

        binding.buttonFifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FifthFragment.this)
                        .navigate(R.id.action_fifthFragment_to_eigthFragment);
                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance("https://in-control-b3e93-default-rtdb.europe-west1.firebasedatabase.app/");
                uname = binding.edittextFifth.getText().toString().trim();
                pword = binding.edittextFifthAlt.getText().toString().trim();


                DatabaseReference usersRef = database.getReference("users");
                usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot datakey : snapshot.getChildren()) {
                            if(datakey.child("name").getValue() != null && datakey.child("name").getValue().equals(uname) && datakey.child("pass").getValue()!=null && datakey.child("pass").getValue().equals(pword)){
                                value = datakey.getKey();
                                setVal(value);
                                break;
                            }
                            else{
                                DatabaseReference pushRef = usersRef.push();
                                value = pushRef.getKey();
                                setVal(value);

                            }
                        }

                        postId = getVal();
                        Bundle result = new Bundle();
                        result.putString("bundleKey", postId);
                        getParentFragmentManager().setFragmentResult("requestKey", result);

                        //Create two bundles one for second one for sixth...

                        DatabaseReference nameRef = database.getReference("users/" + postId + "/name");
                        nameRef.setValue(uname);
                        //usersRef.push().setValue(s);
                        DatabaseReference passRef = database.getReference("users/" + postId + "/pass");
                        passRef.setValue(pword);

                        isTher = getTher();
                        Bundle resultTh = new Bundle();
                        resultTh.putString("bundleKeyTh", isTher);
                        getParentFragmentManager().setFragmentResult("requestKeyTh", resultTh);


//                        long moodcount = snapshot.child("-NR00h0zLo7KfJoFP7M6/mood").getChildrenCount();
//                        String a = "Data:\n\n";
//                        for (long i = moodcount; i-- > moodcount-3;) {
//                            String getloc = snapshot.child("-NR00h0zLo7KfJoFP7M6/loc/" + i).getValue(String.class);
//                            String gettime = snapshot.child("-NR00h0zLo7KfJoFP7M6/time/" + i).getValue(String.class);
//                            String getmood = snapshot.child("-NR00h0zLo7KfJoFP7M6/mood/" + i).getValue(String.class);
//                            String getnote = snapshot.child("-NR00h0zLo7KfJoFP7M6/note/" + i).getValue(String.class);
//                            a = a + getloc + " " + gettime + " " + getmood + " " + getnote + "\n\n";
////                String bigstring = getloc + "\n" + gettime + "\n" + getmood + "\n" + getnote;
//                        }
//                        binding.textviewSeventh.setText(a);
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        System.out.println("The read failed: " + error.getCode());
//                    }
//                });





            }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        System.out.println("The read failed: " + error.getCode());
                    }
        });
    }
    });

                //Causing problems?


                binding.buttonFifthAltAlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTher("yes");
            }
        }
        );

    }

    /*** Sets val.** @param s the s*/
    public void setVal(String s) {
        this.postId = s;
    }

    /*** Gets val.** @return val*/
    public String getVal() {
        return postId;
    }

    /*** Sets val.** @param s the s*/
    public void setTher(String t) {this.isTher = t;}

    /*** Gets val.** @return val*/
    public String getTher() {
        return isTher;
    }

}







//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }

//super.onCreate(savedInstanceState);
//setContentView(R.layout.fragment_fifth);
//setContentView(R.layout.fragment_fifth)
//edittext_fifth  = (EditText) findViewById(R.id.edittext_fifth);
//edittext_fifth = (EditText) text_fifthEdt.findViewById(R.id.edittext_fifth);
//text_fifthEdt = (EditText) findViewById(R.id.edittext_fifth);
//text_fifthEdt = text_fifthEdt.findViewById(this.edittext_fifth);

//s = edittext_fifth.getText().toString().trim();

//String name = editTextName.getText().toString().trim();
//String id = databaseArtists.push().getKey();
//databaseArtists.child(id).setValue(artist);

//Attempt to invoke virtual method 'android.text.Editable android.widget.EditText.getText()'
// on a null object reference