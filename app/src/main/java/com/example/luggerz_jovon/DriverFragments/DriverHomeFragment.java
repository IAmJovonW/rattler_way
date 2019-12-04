package com.example.luggerz_jovon.DriverFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luggerz_jovon.CustomerLugAdapter;
import com.example.luggerz_jovon.DriverLugAdapter;
import com.example.luggerz_jovon.Lugs;
import com.example.luggerz_jovon.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class DriverHomeFragment extends Fragment {
    private DriverLugAdapter adapter;
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
        String driverId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Query query = lugsRef.whereEqualTo("status", "Open");

        FirestoreRecyclerOptions<Lugs> options = new FirestoreRecyclerOptions.Builder<Lugs>().setQuery(query, Lugs.class).build();
        adapter = new DriverLugAdapter(options);

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
