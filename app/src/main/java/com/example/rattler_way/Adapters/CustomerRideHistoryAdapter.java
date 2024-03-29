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
import com.google.firebase.firestore.FirebaseFirestore;

public class CustomerRideHistoryAdapter extends FirestoreRecyclerAdapter<RideRequest, CustomerRideHistoryAdapter.CustomerRideHistoryHolder> {

    public CustomerRideHistoryAdapter(@NonNull FirestoreRecyclerOptions<RideRequest> options){
        super(options);
    }

    @NonNull
    @Override
    public CustomerRideHistoryAdapter.CustomerRideHistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_my_rides, parent,false);
        return new CustomerRideHistoryAdapter.CustomerRideHistoryHolder(v);
    }

    @Override
    protected void onBindViewHolder(@NonNull CustomerRideHistoryAdapter.CustomerRideHistoryHolder holder, int position, @NonNull RideRequest lugs) {
        holder.date.setText(lugs.getDate());
        holder.time.setText(lugs.getTime());
        holder.pickupLocation.setText(lugs.getPickupLocation());
        holder.destination.setText(lugs.getDestination());
        holder.status.setText(lugs.getStatus());


    }



    public class CustomerRideHistoryHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "CustomerRideAdapter" ;
        TextView itemDescription, date, time, pickupLocation, destination, status;
        FirebaseFirestore rideFirestore = FirebaseFirestore.getInstance();







        public CustomerRideHistoryHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.list_date);
            time = itemView.findViewById(R.id.list_time);
            pickupLocation = itemView.findViewById(R.id.list_pickupLocation);
            destination = itemView.findViewById(R.id.list_destination);
            status = itemView.findViewById(R.id.list_status);
        }


    }
}
