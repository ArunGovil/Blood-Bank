package com.example.bloodhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Search extends AppCompatActivity {
    private TextView head;
    private Button Ap, An, Bp, Bn, Op, On, ABp, ABn;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_search);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.nav_notification:
                        startActivity(new Intent(getApplicationContext(), Notification.class));
                        break;

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


        head = (TextView) findViewById(R.id.tvHead);
        Ap = (Button) findViewById(R.id.btnAp);
        An = (Button) findViewById(R.id.btnAn);
        Bp = (Button) findViewById(R.id.btnBp);
        Bn = (Button) findViewById(R.id.btnBn);
        Op = (Button) findViewById(R.id.btnOp);
        On = (Button) findViewById(R.id.btnOn);
        ABp = (Button) findViewById(R.id.btnABp);
        ABn = (Button) findViewById(R.id.btnABn);

        Op.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DonorList.class));
            }
        });

    }
}
