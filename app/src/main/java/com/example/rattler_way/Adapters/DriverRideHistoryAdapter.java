package com.example.rattler_way.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rattler_way.RideRequest;
import com.example.rattler_way.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class DriverRideHistoryAdapter extends FirestoreRecyclerAdapter<RideRequest, DriverRideHistoryAdapter.DriverLugHistoryHolder> {

    public DriverRideHistoryAdapter(@NonNull FirestoreRecyclerOptions<RideRequest> options){
        super(options);
    }

    @NonNull
    @Override
    public DriverRideHistoryAdapter.DriverLugHistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_my_rides, parent,false);
        return new DriverRideHistoryAdapter.DriverLugHistoryHolder(v);
    }

    @Override
    protected void onBindViewHolder(@NonNull DriverRideHistoryAdapter.DriverLugHistoryHolder holder, int position, @NonNull RideRequest lugs) {
        holder.date.setText(lugs.getDate());
        holder.time.setText(lugs.getTime());
        holder.pickupLocation.setText(lugs.getPickupLocation());
        holder.destination.setText(lugs.getDestination());
        holder.status.setText(lugs.getStatus());


    }



    public class DriverLugHistoryHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "DriverRideAdapter" ;
        TextView itemDescription, date, time, pickupLocation, destination, status;
        FirebaseFirestore lugFirestore = FirebaseFirestore.getInstance();
        String driverId = FirebaseAuth.getInstance().getCurrentUser().getUid();








        public DriverLugHistoryHolder(@NonNull View itemView) {
            super(itemView);
            itemDescription = itemView.findViewById(R.id.list_itemDescription);
            date = itemView.findViewById(R.id.list_date);
            time = itemView.findViewById(R.id.list_time);
            pickupLocation = itemView.findViewById(R.id.list_pickupLocation);
            destination = itemView.findViewById(R.id.list_destination);
            status = itemView.findViewById(R.id.list_status);
        }

    }
}
