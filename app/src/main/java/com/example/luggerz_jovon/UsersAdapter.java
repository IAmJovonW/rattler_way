package com.example.luggerz_jovon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {
    DatabaseReference lugReference;
    DatabaseReference driverReference;
    Context context;
    ArrayList<AllUsers> users;
    public UsersAdapter(Context c, ArrayList<AllUsers> l){
        context = c;
        users = l;
    }


    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UsersViewHolder(LayoutInflater.from(context).inflate(R.layout.list_users,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        holder.userName.setText(users.get(position).getName());
        holder.userPhone.setText(users.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder{
        TextView userName, userPhone;

        public UsersViewHolder(View itemView){
            super(itemView);
            userName = itemView.findViewById(R.id.list_userName);
            userPhone = itemView.findViewById(R.id.list_userPhone);

            lugReference = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers");
            driverReference = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers");

//            ban.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    final boolean isBanned = true;
//                    Map userInfo = new HashMap();
//                    userInfo.put("isBanned", isBanned);
//                    lugReference.updateChildren(userInfo);
//                    driverReference.updateChildren(userInfo);
//                }
//            });

        }
    }


}