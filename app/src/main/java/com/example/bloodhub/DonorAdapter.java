package com.example.bloodhub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DonorAdapter extends RecyclerView.Adapter<DonorAdapter.UserViewHolder> {

    private Context mCtx;
    private List<Users> UserList;

    public DonorAdapter(Context mCtx, List<Users> userList) {
        this.mCtx = mCtx;
        this.UserList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.listdesign, parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        Users user = UserList.get(position);
        holder.Name.setText(user.getEtName());
        holder.Phone.setText(user.getEtPhone());

    }

    @Override
    public int getItemCount() {
        return UserList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        TextView Name, Phone;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.tvName);
            Phone = itemView.findViewById(R.id.tvPhone);

        }
    }
}
