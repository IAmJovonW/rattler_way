package com.example.luggerz_jovon;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerLugAdapter extends FirestoreRecyclerAdapter<Lugs, CustomerLugAdapter.CustomerLugHolder>{

    public CustomerLugAdapter(@NonNull FirestoreRecyclerOptions<Lugs> options){
        super(options);
    }

    @NonNull
    @Override
    public CustomerLugHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mylugs, parent,false);
        return new CustomerLugHolder(v);
    }

    @Override
    protected void onBindViewHolder(@NonNull CustomerLugHolder holder, int position, @NonNull Lugs lugs) {
        holder.itemDescription.setText(lugs.getItemDescription());
        holder.date.setText(lugs.getDate());
        holder.time.setText(lugs.getTime());
        holder.pickupLocation.setText(lugs.getPickupLocation());
        holder.destination.setText(lugs.getDestination());
        holder.btnCancel.setVisibility(View.VISIBLE);
        holder.onClick(position);


    }



    public class CustomerLugHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "CustomerLugAdapter" ;
        TextView itemDescription, date, time, pickupLocation, destination;
        Button btnCancel;
        FirebaseFirestore lugFirestore = FirebaseFirestore.getInstance();







        public CustomerLugHolder(@NonNull View itemView) {
            super(itemView);
            itemDescription = itemView.findViewById(R.id.list_itemDescription);
            date = itemView.findViewById(R.id.list_date);
            time = itemView.findViewById(R.id.list_time);
            pickupLocation = itemView.findViewById(R.id.list_pickupLocation);
            destination = itemView.findViewById(R.id.list_destination);
            btnCancel = itemView.findViewById(R.id.cancelLug);
        }

        public void onClick(final int position) {
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String lugId = getSnapshots().getSnapshot(position).getId();
                    DocumentReference lugRef = lugFirestore.collection("lugs").document(lugId);

                    lugRef.update("status", "Cancelled").addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "Document Snapshot successfully updated!");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error updating document", e);
                        }
                    });



                }
            });
        }
    }
}
