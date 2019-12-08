package com.example.rattler_way.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rattler_way.RideRequest;
import com.example.rattler_way.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DriverRideAdapter extends FirestoreRecyclerAdapter<RideRequest, DriverRideAdapter.DriverLugHolder> {

    public DriverRideAdapter(@NonNull FirestoreRecyclerOptions<RideRequest> options){
        super(options);
    }

    @NonNull
    @Override
    public DriverRideAdapter.DriverLugHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_my_rides, parent,false);
        return new DriverRideAdapter.DriverLugHolder(v);
    }

    @Override
    protected void onBindViewHolder(@NonNull DriverRideAdapter.DriverLugHolder holder, int position, @NonNull RideRequest lugs) {
        holder.date.setText(lugs.getDate());
        holder.time.setText(lugs.getTime());
        holder.pickupLocation.setText(lugs.getPickupLocation());
        holder.destination.setText(lugs.getDestination());
        holder.status.setText(lugs.getStatus());
        holder.btnAccept.setVisibility(View.VISIBLE);
        holder.onClick(position);


    }



    public class DriverLugHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "DriverRideAdapter" ;
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
