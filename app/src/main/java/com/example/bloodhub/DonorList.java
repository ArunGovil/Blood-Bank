package com.example.bloodhub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DonorList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DonorAdapter adapter;
    private List<Users> userList;
    private Button call;
    private int pressedButtonNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }
        setContentView(R.layout.activity_donor_list);

        userList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.rvList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DonorAdapter(this, userList);
        recyclerView.setAdapter(adapter);
        call = (Button) findViewById(R.id.btnCall);

        pressedButtonNumber = getIntent().getExtras().getInt("buttonNumber");
        switch(pressedButtonNumber){
            case 1:
                Query queryRef = FirebaseDatabase.getInstance().getReference("User")
                        .orderByChild("etBlood")
                        .equalTo("A+ve");

                queryRef.addListenerForSingleValueEvent(valueEventListener);
                break;
            case 2:
                Query queryRef2 = FirebaseDatabase.getInstance().getReference("User")
                        .orderByChild("etBlood")
                        .equalTo("A-ve");

                queryRef2.addListenerForSingleValueEvent(valueEventListener);
                break;
            case 3:
                Query queryRef3 = FirebaseDatabase.getInstance().getReference("User")
                        .orderByChild("etBlood")
                        .equalTo("B+ve");

                queryRef3.addListenerForSingleValueEvent(valueEventListener);
                break;
            case 4:
                Query queryRef4 = FirebaseDatabase.getInstance().getReference("User")
                        .orderByChild("etBlood")
                        .equalTo("B-ve");

                queryRef4.addListenerForSingleValueEvent(valueEventListener);
                break;
            case 5:
                Query queryRef5 = FirebaseDatabase.getInstance().getReference("User")
                        .orderByChild("etBlood")
                        .equalTo("O+ve");

                queryRef5.addListenerForSingleValueEvent(valueEventListener);
                break;
            case 6:
                Query queryRef6 = FirebaseDatabase.getInstance().getReference("User")
                        .orderByChild("etBlood")
                        .equalTo("O-ve");

                queryRef6.addListenerForSingleValueEvent(valueEventListener);
                break;
            case 7:
                Query queryRef7 = FirebaseDatabase.getInstance().getReference("User")
                        .orderByChild("etBlood")
                        .equalTo("AB+ve");

                queryRef7.addListenerForSingleValueEvent(valueEventListener);
                break;
            case 8:
                Query queryRef8 = FirebaseDatabase.getInstance().getReference("User")
                        .orderByChild("etBlood")
                        .equalTo("AB-ve");

                queryRef8.addListenerForSingleValueEvent(valueEventListener);
                break;
        }

    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            userList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Users user = snapshot.getValue(Users.class);
                    userList.add(user);
                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}

