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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FifthFragment extends Fragment {

    public FragmentFifthBinding binding;
    public EditText nametext;
    public EditText passtext;
    public TextView t;
    public Button b;
    public String s = "Hi";
    public String strpass;
    public String postId;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFifthBinding.inflate(inflater, container, false);
        return binding.getRoot();
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_fifth);
        //setContentView(R.layout.fragment_fifth)
        //edittext_fifth  = (EditText) findViewById(R.id.edittext_fifth);
        //edittext_fifth = (EditText) text_fifthEdt.findViewById(R.id.edittext_fifth);
        //text_fifthEdt = (EditText) findViewById(R.id.edittext_fifth);
        //text_fifthEdt = text_fifthEdt.findViewById(this.edittext_fifth);

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FifthFragment.this)
                        .navigate(R.id.action_fifthFragment_to_SecondFragment);
                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance("https://in-control-b3e93-default-rtdb.europe-west1.firebasedatabase.app/");
                DatabaseReference myRef = database.getReference("Info");
                myRef.setValue("Info");
                DatabaseReference uRef = database.getReference("Info/Username");
                DatabaseReference pRef = database.getReference("Info/Password");
                s = binding.edittextFifth.getText().toString().trim();
                strpass = binding.edittextFifthAlt.getText().toString().trim();
                uRef.setValue(s);
                pRef.setValue(strpass);

                DatabaseReference usersRef = database.getReference("users");
                DatabaseReference pushRef = usersRef.push();
                String postId = pushRef.getKey();
                DatabaseReference nameRef = database.getReference("users/" + postId + "/name");
                nameRef.setValue(s);
                //usersRef.push().setValue(s);
                DatabaseReference passRef = database.getReference("users/" + postId + "/pass");
                passRef.setValue(strpass);

                Bundle result = new Bundle();
                result.putString("bundleKey", postId);
                getParentFragmentManager().setFragmentResult("requestKey", result);


                //s = edittext_fifth.getText().toString().trim();

                //String name = editTextName.getText().toString().trim();
                //String id = databaseArtists.push().getKey();
                //databaseArtists.child(id).setValue(artist);

                //Attempt to invoke virtual method 'android.text.Editable android.widget.EditText.getText()'
                // on a null object reference
            }
        });
    }
}