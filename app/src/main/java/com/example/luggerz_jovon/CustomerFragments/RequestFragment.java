package com.example.luggerz_jovon.CustomerFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.luggerz_jovon.Lugs;
import com.example.luggerz_jovon.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RequestFragment extends Fragment {

    private static final String TAG = "RequestFragment";


    private ImageView itemImage;
    private EditText etLugDate, etLugTime, etLugPickup, etLugDestination, etLugItemDescription;
    private RadioGroup mRadioGroup;
    private Button btnSubmit;

    private String userID, requestService;
    private String pStatus = "Open";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private DatabaseReference lugDatabase, historyLugId;

    FirebaseFirestore lugFirestore = FirebaseFirestore.getInstance();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fragment_request, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TODO: Implement Image post feature
        itemImage = view.findViewById(R.id.itemImage);
        etLugItemDescription = view.findViewById(R.id.etLugItemDescription);
        etLugDate = view.findViewById(R.id.etLugDate);
        etLugTime = view.findViewById(R.id.etLugTime);
        etLugPickup = view.findViewById(R.id.etLugPickup);
        etLugDestination = view.findViewById(R.id.etLugDestination);
        btnSubmit = view.findViewById(R.id.btnSubmit);



        mRadioGroup = view.findViewById(R.id.radioGroup);
        mRadioGroup.check(R.id.LuggerX);


        int selectId = mRadioGroup.getCheckedRadioButtonId();

        final RadioButton radioButton = view.findViewById(selectId);




        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (radioButton.getText() == null){
                    return;
                }


                String customerId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                String driverId = "";

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                String lugId = firebaseDatabase.getReference().child("lugs").push().getKey();





                Lugs lugs = new Lugs(
                        etLugItemDescription.getText().toString(),
                        etLugDate.getText().toString(),
                        etLugTime.getText().toString(),
                        etLugPickup.getText().toString(),
                        etLugDestination.getText().toString(),
                        requestService = radioButton.getText().toString(),
                        pStatus, customerId, driverId, lugId);

                addLug(lugs, lugId);

            }
        });

    }

    private void addLug(Lugs lugs, String lugId) {
        FirebaseDatabase lugDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = lugDatabase.getReference("lugs").push();

        //Firestore
        lugFirestore.collection("lugs").document(lugId).set(lugs);

        //Firebase rtdb
        myRef.setValue(lugs);
        myRef.child("lugId").setValue(myRef.getKey());


        Toast.makeText(getContext(), "Lug Requested!", Toast.LENGTH_SHORT).show();



    }

}
