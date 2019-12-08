package com.example.rattler_way.CustomerFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rattler_way.Adapters.CustomerRideAdapter;
import com.example.rattler_way.RideRequest;
import com.example.rattler_way.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Arrays;

public class CustomerHomeFragment extends Fragment {

    private CustomerRideAdapter adapter;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance().getInstance();
    private CollectionReference rideRef = db.collection("Rides");



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_myrides, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String customerId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //Query query = lugsRef.whereEqualTo("customerId", customerId).whereEqualTo("status", "Open");
        Query query = rideRef.whereEqualTo("customerId", customerId).whereIn("status", Arrays.asList("Accepted","Open", "On the way", "Picked Up", "Driving"));

        FirestoreRecyclerOptions<RideRequest> options = new FirestoreRecyclerOptions.Builder<RideRequest>().setQuery(query, RideRequest.class).build();
        adapter = new CustomerRideAdapter(options);

        RecyclerView recyclerView = view.findViewById(R.id.list_myRides);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onStart(){
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
