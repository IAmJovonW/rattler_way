package com.example.luggerz_jovon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyLugAdapter extends RecyclerView.Adapter<MyLugAdapter.MyLugViewHolder> {
    Context context;
    ArrayList<Lugs> lugs;
    public MyLugAdapter(Context c, ArrayList<Lugs> l){
        context = c;
        lugs = l;
    }


    @NonNull
    @Override
    public MyLugViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyLugViewHolder(LayoutInflater.from(context).inflate(R.layout.list_mylugs,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyLugViewHolder holder, int position) {
        holder.itemDescription.setText(lugs.get(position).getItemDescription());
        holder.date.setText(lugs.get(position).getDate());
        holder.time.setText(lugs.get(position).getTime());
        holder.pickupLocation.setText(lugs.get(position).getPickupLocation());
        holder.destination.setText(lugs.get(position).getDestination());
    }

    @Override
    public int getItemCount() {
        return lugs.size();
    }

    class MyLugViewHolder extends RecyclerView.ViewHolder{
        TextView itemDescription, date, time, pickupLocation, destination;

        public MyLugViewHolder(View itemView){
            super(itemView);
            itemDescription = itemView.findViewById(R.id.list_itemDescription);
            date = itemView.findViewById(R.id.list_date);
            time = itemView.findViewById(R.id.list_time);
            pickupLocation = itemView.findViewById(R.id.list_pickupLocation);
            destination = itemView.findViewById(R.id.list_destination);

        }
    }
}