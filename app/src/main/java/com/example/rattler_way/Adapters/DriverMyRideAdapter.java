package com.example.rattler_way.Adapters;

import android.content.Context;
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

import com.example.rattler_way.RideRequest;
import com.example.rattler_way.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DriverMyRideAdapter extends   FirestoreRecyclerAdapter<RideRequest, DriverMyRideAdapter.DriverMyRideHolder> implements AdapterView.OnItemSelectedListener {
    Context context;


    public DriverMyRideAdapter(@NonNull FirestoreRecyclerOptions<RideRequest> options){
        super(options);
        }

@NonNull
@Override
public DriverMyRideAdapter.DriverMyRideHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_my_rides, parent,false);
        return new DriverMyRideAdapter.DriverMyRideHolder(v);
        }

@Override
protected void onBindViewHolder(@NonNull DriverMyRideAdapter.DriverMyRideHolder holder, int position, @NonNull RideRequest lugs) {

        holder.date.setText(lugs.getDate());
        holder.time.setText(lugs.getTime());
        holder.pickupLocation.setText(lugs.getPickupLocation());
        holder.destination.setText(lugs.getDestination());
        holder.status.setText(lugs.getStatus());
        holder.btnUpdate.setVisibility(View.VISIBLE);
        holder.spinner.setVisibility(View.VISIBLE);
        holder.spinner.setOnItemSelectedListener(this);
        holder.onClick(position);




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


    public class DriverMyRideHolder extends RecyclerView.ViewHolder  {
    private static final String TAG = "DriverMyRideAdapter" ;
    TextView itemDescription, date, time, pickupLocation, destination, status;

    Spinner spinner;
    Button btnUpdate;
    FirebaseFirestore rideFirestore = FirebaseFirestore.getInstance();
    String driverId = FirebaseAuth.getInstance().getCurrentUser().getUid();








    public DriverMyRideHolder(@NonNull View itemView) {
        super(itemView);

        date = itemView.findViewById(R.id.list_date);
        time = itemView.findViewById(R.id.list_time);
        pickupLocation = itemView.findViewById(R.id.list_pickupLocation);
        destination = itemView.findViewById(R.id.list_destination);
        status = itemView.findViewById(R.id.list_status);
        spinner = (Spinner) itemView.findViewById(R.id.spinner);
        btnUpdate = itemView.findViewById(R.id.updateLug);



    }


        public void onClick(final int position) {
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String spinnerChoice = spinner.getSelectedItem().toString();
                    String rideId = getSnapshots().getSnapshot(position).getId();
                    DocumentReference rideRef = rideFirestore.collection("Rides").document(rideId);

                    rideRef.update("status", spinnerChoice).addOnSuccessListener(new OnSuccessListener<Void>() {
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
