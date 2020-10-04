package com.example.bloodhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodhub.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Signup extends AppCompatActivity {
    private EditText Name,Dob,Phone,Password,Mail;
    private Button Sign;
    private Spinner Blood,Gender;
    private FirebaseAuth mAuth;
    private TextView Login;
    private DatePickerDialog.OnDateSetListener setListener;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_signup);

        Name=(EditText)findViewById(R.id.etName);
        Dob=(EditText)findViewById(R.id.etDob);
        Phone=(EditText)findViewById(R.id.etPhone);
        Password=(EditText)findViewById(R.id.etPassword);
        Sign=(Button)findViewById(R.id.btnSignup);
        Gender=(Spinner)findViewById(R.id.spGender);
        Blood=(Spinner)findViewById(R.id.spblood);
        Mail=(EditText)findViewById(R.id.etMail);
        Login=(TextView)findViewById(R.id.tvLogin);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        databaseReference = FirebaseDatabase.getInstance().getReference("User");
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser()!=null) {
            startActivity(new Intent(getApplicationContext(),UserProfile.class));
            finish();
        }

        Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String etName = Name.getText().toString().trim();
                final String etMail = Mail.getText().toString().trim();
                String etPassword = Password.getText().toString().trim();
                final String etPhone = Phone.getText().toString().trim();
                final String etDob = Dob.getText().toString().trim();
                final String etGender = Gender.getSelectedItem().toString().trim();
                final String etBlood = Blood.getSelectedItem().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Signup.this,android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth
                        ,setListener,year,month,day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();


                if (TextUtils.isEmpty(etName)) {
                    Name.setError("Name is required");
                    return;
                }
                if (TextUtils.isEmpty(etMail)) {
                    Mail.setError("Email is required");
                    return;
                }
                if (!etMail.matches(emailPattern)) {
                    Mail.setError("Invalid Email");
                    return;
                }
                if (etPassword.length() < 6) {
                    Password.setError("Password too Short");
                    return;
                }

                mAuth.createUserWithEmailAndPassword(etMail,etPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Users users = new Users(etName, etGender, etBlood, etPhone);

                                    FirebaseDatabase.getInstance().getReference("User")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(Signup.this, "User Created", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), UserProfile.class));
                                        }
                                    });
                                }
                            }
                        });

            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

        Dob.setFocusable(false);
        Dob.setKeyListener(null);

        Dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Signup.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month +1;
                        String date = day+"/"+month+"/"+year;
                        Dob.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

    }
}
