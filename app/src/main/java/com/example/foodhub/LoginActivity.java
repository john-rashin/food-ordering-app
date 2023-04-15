package com.example.foodhub;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
        TextView gotoCreate,reset;
        EditText emailLog,passLog;
        Button login;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null)
            actionBar.hide();


        emailLog=findViewById(R.id.emailTxt);
        passLog=findViewById(R.id.passTxt);
        gotoCreate=findViewById(R.id.signupTxt);
        reset=findViewById(R.id.resetPass);
//Get email and pass from registration
        String emailValue = getIntent().getStringExtra("EMAIL");
        String passwordValue = getIntent().getStringExtra("PASSWORD");
        emailLog.setText(emailValue);
        passLog.setText(passwordValue);

        //go to create account
        gotoCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,signUp.class));
            }
        });


        //Login to dashboard
        login= findViewById(R.id.loginBtn);
        mAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailLog.getText().toString();
                String password = passLog.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    emailLog.setError("Empty field");
                    emailLog.requestFocus();
                    passLog.setError("Empty  field");
                    passLog.requestFocus();
                    return;
                }

                //Firebase

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Login was successful, start a new activity or do any other desired action
                                    Intent intent = new Intent(LoginActivity.this, Dashboard.class);
                                    startActivity(intent);
                                    Toast.makeText(LoginActivity.this, "WELCOME TO FOODHUB", Toast.LENGTH_SHORT).show();
                                } else {
                                    // Login was unsuccessful, display an error message to the user
                                    Toast.makeText(LoginActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
                //Reset password
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailLog.getText().toString();
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (email.isEmpty()) {
                                    emailLog.setError("Required Email address to reset");
                                    emailLog.requestFocus();
                                }else{
                                    task.isSuccessful();
                                    Toast.makeText(LoginActivity.this, "Check your Gmail to change your Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

}