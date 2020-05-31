package com.example.bloodhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodhub.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class Signup extends AppCompatActivity {
    private EditText Name,Dob,Phone,Password,Mail,Gender,Blood;
    private Button Sign;
    private FirebaseAuth mAuth;
    private TextView Login;

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
        Gender=(EditText)findViewById(R.id.etGender);
        Blood=(EditText)findViewById(R.id.etBlood);
        Mail=(EditText)findViewById(R.id.etMail);
        Login=(TextView)findViewById(R.id.tvLogin);

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
                final String etGender = Gender.getText().toString().trim();
                final String etBlood = Blood.getText().toString().trim();

                if (TextUtils.isEmpty(etName)) {
                    Name.setError("Name is required");
                    return;
                }
                if (TextUtils.isEmpty(etMail)) {
                    Mail.setError("Email is required");
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
    }
}
