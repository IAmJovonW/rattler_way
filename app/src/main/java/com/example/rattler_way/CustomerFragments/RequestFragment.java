package com.example.rattler_way.CustomerFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rattler_way.RideRequest;
import com.example.rattler_way.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class RequestFragment extends Fragment {

    private static final String TAG = "RequestFragment";


    private EditText etLugDate, etLugTime, etLugPickup, etLugDestination;
    private RadioGroup mRadioGroup;
    private Button btnSubmit;

    private String userID, requestService;
    private String pStatus = "Open";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private DatabaseReference rideDatabase;

    FirebaseFirestore rideFirestore = FirebaseFirestore.getInstance();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fragment_request, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TODO: Implement Image post feature
        etLugDate = view.findViewById(R.id.etRideDate);
        etLugTime = view.findViewById(R.id.etRideTime);
        etLugPickup = view.findViewById(R.id.etRidePickup);
        etLugDestination = view.findViewById(R.id.etRideDestination);
        btnSubmit = view.findViewById(R.id.btnSubmit);



        mRadioGroup = view.findViewById(R.id.radioGroup);
        mRadioGroup.check(R.id.RattlerX);


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
                String driverName = "";

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                String rideId = firebaseDatabase.getReference().child("rides").push().getKey();





                RideRequest ride = new RideRequest(
                        etLugDate.getText().toString(),
                        etLugTime.getText().toString(),
                        etLugPickup.getText().toString(),
                        etLugDestination.getText().toString(),
                        requestService = radioButton.getText().toString(),
                        pStatus, customerId, driverId, rideId);

                addRide(ride, rideId);

            }
        });

    }

    private void addRide(RideRequest rides, String rideId) {
        FirebaseDatabase lugDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = lugDatabase.getReference("Rides").push();

        //Firestore
        rideFirestore.collection("Rides").document(rideId).set(rides);

        //Firebase rtdb
        myRef.setValue(rides);
        myRef.child("rideId").setValue(myRef.getKey());


        Toast.makeText(getContext(), "Ride Requested!", Toast.LENGTH_SHORT).show();



    }

}
