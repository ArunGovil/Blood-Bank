package com.example.bloodhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class Search extends AppCompatActivity {
    private TextView head;
    private Button Ap, An, Bp, Bn, Op, On, ABp, ABn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_search);

        head = (TextView) findViewById(R.id.tvHead);
        Ap = (Button) findViewById(R.id.btnAp);
        An = (Button) findViewById(R.id.btnAn);
        Bp = (Button) findViewById(R.id.btnBp);
        Bn = (Button) findViewById(R.id.btnBn);
        Op = (Button) findViewById(R.id.btnOp);
        On = (Button) findViewById(R.id.btnOn);
        ABp = (Button) findViewById(R.id.btnABp);
        ABn = (Button) findViewById(R.id.btnABn);

        Ap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DonorList.class));
            }
        });

    }
}
