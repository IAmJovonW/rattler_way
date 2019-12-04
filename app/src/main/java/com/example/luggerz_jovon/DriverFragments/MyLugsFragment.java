package com.example.luggerz_jovon.DriverFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luggerz_jovon.DriverLugAdapter;
import com.example.luggerz_jovon.DriverMyLugAdapter;
import com.example.luggerz_jovon.Lugs;
import com.example.luggerz_jovon.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyLugsFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private DriverMyLugAdapter adapter;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance().getInstance();
    private CollectionReference lugsRef = db.collection("lugs");
    private String driverId = FirebaseAuth.getInstance().getCurrentUser().getUid();




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mylugs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String driverId = FirebaseAuth.getInstance().getCurrentUser().getUid();






        //Query query = lugsRef.whereEqualTo("status", "Accepted").whereEqualTo("driverId",driverId);
        Query query = lugsRef.whereEqualTo("driverId", driverId).whereIn("status", Arrays.asList("Open", "On the way", "Picked Up", "Delivering"));


        FirestoreRecyclerOptions<Lugs> options = new FirestoreRecyclerOptions.Builder<Lugs>().setQuery(query, Lugs.class).build();
        adapter = new DriverMyLugAdapter(options);

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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String item = adapterView.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
