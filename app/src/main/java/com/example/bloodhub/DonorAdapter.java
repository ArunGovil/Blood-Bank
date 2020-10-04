package com.example.bloodhub;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.core.content.ContextCompat.startActivity;

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

        final Users user = UserList.get(position);
        holder.Name.setText(user.getEtName());
        holder.Phone.setText(user.getEtPhone());

        holder.Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobileNo = user.getEtPhone();
                String call = "tel:" +mobileNo.trim();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(call));
                mCtx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return UserList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder{

        public View Call;
        TextView Name, Phone;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.tvName);
            Phone = itemView.findViewById(R.id.tvPhone);
            Call = itemView.findViewById(R.id.btnCall);

        }
    }
}
