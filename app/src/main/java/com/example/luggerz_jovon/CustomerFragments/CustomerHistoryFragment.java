package com.example.luggerz_jovon.CustomerFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luggerz_jovon.Adapters.CustomerLugAdapter;
import com.example.luggerz_jovon.Adapters.CustomerLugHistoryAdapter;
import com.example.luggerz_jovon.Lugs;
import com.example.luggerz_jovon.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Arrays;

public class CustomerHistoryFragment extends Fragment {
    private CustomerLugHistoryAdapter adapter;
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

        FirestoreRecyclerOptions<Lugs> options = new FirestoreRecyclerOptions.Builder<Lugs>().setQuery(query, Lugs.class).build();
        adapter = new CustomerLugHistoryAdapter(options);

        RecyclerView recyclerView = view.findViewById(R.id.list_mylugs);
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
