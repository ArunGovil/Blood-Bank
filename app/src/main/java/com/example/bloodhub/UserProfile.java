package com.example.bloodhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {
    private TextView uName,uBlood,uPhone;
    private Button Logout;
    private DatabaseReference profileUserRef;
    private FirebaseAuth mAuth;
    private String currentUserId;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_user_profile);

        mAuth=FirebaseAuth.getInstance();
        currentUserId=mAuth.getCurrentUser().getUid();
        profileUserRef= FirebaseDatabase.getInstance().getReference().child("User").child(currentUserId);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.nav_profile:
                        startActivity(new Intent(getApplicationContext(), UserProfile.class));
                        break;

                    case R.id.nav_search:
                        startActivity(new Intent(getApplicationContext(), Search.class));
                        break;

                }
                return false;
            }
        });

        uName = (TextView) findViewById(R.id.tvName);
        uPhone = (TextView) findViewById(R.id.tvPhone);
        uBlood = (TextView) findViewById(R.id.tvBlood);
        Logout = (Button) findViewById(R.id.btnLogout);

        profileUserRef.addValueEventListener(new ValueEventListener() {
            String myName, myBlood, myPhone;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {
                    String myName = dataSnapshot.child("etName").getValue().toString();
                    String myBlood = dataSnapshot.child("etBlood").getValue().toString();
                    String myPhone = dataSnapshot.child("etPhone").getValue().toString();

                    uName.setText(myName);
                    uBlood.setText(myBlood);
                    uPhone.setText(myPhone);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Failed to read value", databaseError.toException());

            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(UserProfile.this, MainActivity.class));
            }
        });
    }
}
