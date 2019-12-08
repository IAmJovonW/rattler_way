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

import com.example.rattler_way.Adapters.CustomerRideHistoryAdapter;
import com.example.rattler_way.RideRequest;
import com.example.rattler_way.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Arrays;

public class CustomerHistoryFragment extends Fragment {
    private CustomerRideHistoryAdapter adapter;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance().getInstance();
    private CollectionReference lugsRef = db.collection("lugs");



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mylugs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String customerId = FirebaseAuth.getInstance().getCurrentUser().getUid();


        //Query query = lugsRef.whereEqualTo("customerId", customerId).whereEqualTo("status", "Open");
        Query query = lugsRef.whereEqualTo("customerId", customerId).whereIn("status", Arrays.asList("Completed", "Cancelled"));

        FirestoreRecyclerOptions<RideRequest> options = new FirestoreRecyclerOptions.Builder<RideRequest>().setQuery(query, RideRequest.class).build();
        adapter = new CustomerRideHistoryAdapter(options);

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
