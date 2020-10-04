package com.example.bloodhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private ImageView Logo;
    private EditText Mail, Password;
    private Button Login;
    private TextView Signup;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_login);

        Logo = (ImageView) findViewById(R.id.ivLogo);
        Mail = (EditText) findViewById(R.id.etMail);
        Password = (EditText) findViewById(R.id.etPassword);
        Login = (Button) findViewById(R.id.btnLogin);
        Signup = (TextView) findViewById(R.id.tvSignup);
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser()!=null) {
            startActivity(new Intent(getApplicationContext(),UserProfile.class));
            finish();
        }

        Login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                String etMail = Mail.getText().toString().trim();
                String etPassword = Password.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (TextUtils.isEmpty(etMail)) {
                    Mail.setError("Email is required");
                    return;
                }
                if (!etMail.matches(emailPattern)) {
                    Mail.setError("Invalid Email");
                    return;
                }
                if (TextUtils.isEmpty(etPassword)) {
                    Password.setError("Password is required");
                    return;
                }
                if (etPassword.length() < 6) {
                    Password.setError("Password too short");
                    return;
                }

                mAuth.signInWithEmailAndPassword(etMail,etPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),UserProfile.class));
                        }
                        else {
                            Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Signup.class));
            }
        });
    }
}
