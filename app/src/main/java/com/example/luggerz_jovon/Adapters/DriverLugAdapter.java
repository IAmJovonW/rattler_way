package com.example.luggerz_jovon.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luggerz_jovon.Lugs;
import com.example.luggerz_jovon.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DriverLugAdapter extends FirestoreRecyclerAdapter<Lugs, DriverLugAdapter.DriverLugHolder> {

    public DriverLugAdapter(@NonNull FirestoreRecyclerOptions<Lugs> options){
        super(options);
    }

    @NonNull
    @Override
    public DriverLugAdapter.DriverLugHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mylugs, parent,false);
        return new DriverLugAdapter.DriverLugHolder(v);
    }

    @Override
    protected void onBindViewHolder(@NonNull DriverLugAdapter.DriverLugHolder holder, int position, @NonNull Lugs lugs) {
        holder.itemDescription.setText(lugs.getItemDescription());
        holder.date.setText(lugs.getDate());
        holder.time.setText(lugs.getTime());
        holder.pickupLocation.setText(lugs.getPickupLocation());
        holder.destination.setText(lugs.getDestination());
        holder.status.setText(lugs.getStatus());
        holder.btnAccept.setVisibility(View.VISIBLE);
        holder.onClick(position);


    }



    public class DriverLugHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "DriverLugAdapter" ;
        TextView itemDescription, date, time, pickupLocation, destination, status;
        Button btnAccept;
        FirebaseFirestore lugFirestore = FirebaseFirestore.getInstance();
        String driverId = FirebaseAuth.getInstance().getCurrentUser().getUid();








        public DriverLugHolder(@NonNull View itemView) {
            super(itemView);
            itemDescription = itemView.findViewById(R.id.list_itemDescription);
            date = itemView.findViewById(R.id.list_date);
            time = itemView.findViewById(R.id.list_time);
            pickupLocation = itemView.findViewById(R.id.list_pickupLocation);
            destination = itemView.findViewById(R.id.list_destination);
            status = itemView.findViewById(R.id.list_status);
            btnAccept = itemView.findViewById(R.id.acceptLug);
        }

        public void onClick(final int position) {
            btnAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String lugId = getSnapshots().getSnapshot(position).getId();
                    DocumentReference lugRef = lugFirestore.collection("lugs").document(lugId);

                    //Attach driverId to Lug
                    lugRef.update("driverId", driverId).addOnSuccessListener(new OnSuccessListener<Void>() {
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


                    //Change Lug status to Accepted
                    lugRef.update("status", "Accepted").addOnSuccessListener(new OnSuccessListener<Void>() {
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
