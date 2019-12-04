package com.example.luggerz_jovon;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class DriverMyLugAdapter extends   FirestoreRecyclerAdapter<Lugs, DriverMyLugAdapter.DriverMyLugHolder> implements AdapterView.OnItemSelectedListener {
    Context context;


    public DriverMyLugAdapter(@NonNull FirestoreRecyclerOptions<Lugs> options){
        super(options);
        }

@NonNull
@Override
public DriverMyLugAdapter.DriverMyLugHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mylugs, parent,false);
        return new DriverMyLugAdapter.DriverMyLugHolder(v);
        }

@Override
protected void onBindViewHolder(@NonNull DriverMyLugAdapter.DriverMyLugHolder holder, int position, @NonNull Lugs lugs) {

        holder.itemDescription.setText(lugs.getItemDescription());
        holder.date.setText(lugs.getDate());
        holder.time.setText(lugs.getTime());
        holder.pickupLocation.setText(lugs.getPickupLocation());
        holder.destination.setText(lugs.getDestination());
        holder.spinner.setVisibility(View.VISIBLE);
        holder.spinner.setOnItemSelectedListener(this);





// Create an ArrayAdapter using the string array and a default spinner layout
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.status_options, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
    holder.spinner.setAdapter(adapter);





        }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        String item = adapterView.getItemAtPosition(position).toString();



    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public class DriverMyLugHolder extends RecyclerView.ViewHolder  {
    private static final String TAG = "DriverMyLugAdapter" ;
    TextView itemDescription, date, time, pickupLocation, destination;

    Spinner spinner;
    Button btnAccept;
    FirebaseFirestore lugFirestore = FirebaseFirestore.getInstance();
    String driverId = FirebaseAuth.getInstance().getCurrentUser().getUid();








    public DriverMyLugHolder(@NonNull View itemView) {
        super(itemView);

        itemDescription = itemView.findViewById(R.id.list_itemDescription);
        date = itemView.findViewById(R.id.list_date);
        time = itemView.findViewById(R.id.list_time);
        pickupLocation = itemView.findViewById(R.id.list_pickupLocation);
        destination = itemView.findViewById(R.id.list_destination);
        spinner = (Spinner) itemView.findViewById(R.id.spinner);



    }



}
}
