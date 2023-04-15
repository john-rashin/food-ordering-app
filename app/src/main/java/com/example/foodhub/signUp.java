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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signUp extends AppCompatActivity {

    TextView signUpCreate;
    EditText emailLog, logPass, fName, lName, cellNumber, phoneNumber;
    Button  createAccount;
 UserInfo userInfo;
    // Initialize Firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users_info");

    // Initialize Firebase authentication
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null)
            actionBar.hide();

        emailLog =  findViewById(R.id.emailLog);
        logPass =  findViewById(R.id.LogPass);
        fName =  findViewById(R.id.fName);
        lName =  findViewById(R.id.lName);
        cellNumber =  findViewById(R.id.cellNumber);
        phoneNumber =  findViewById(R.id.phoneNumber);
        createAccount =  findViewById(R.id.createAccount);

        //Customers information

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailLog.getText().toString();
                String password = logPass.getText().toString();
                String firstName = fName.getText().toString();
                String lastName = lName.getText().toString();
                String cell = cellNumber.getText().toString();
                String phone = phoneNumber.getText().toString();

                // Create a new user with email and password
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    //Validating not to empty email and pass
                                    if (email.isEmpty() || password.isEmpty()) {
                                        emailLog.setError("Empty email field");
                                        emailLog.requestFocus();
                                        logPass.setError("Empty email field");
                                        logPass.requestFocus();

                                    }
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    // Store user information in Firebase database
                                    User userInfo = new User (firstName, lastName, cell, phone);
                                    myRef.child(user.getUid()).setValue(userInfo);

                                   // emailLog.setText("");
                                   // logPass.setText("");
                                    fName.setText("");
                                    lName.setText("");
                                    cellNumber.setText("");
                                    phoneNumber.setText("");
                                    Intent intent = new Intent(signUp.this, LoginActivity.class);
                                    intent.putExtra("EMAIL", email);
                                    intent.putExtra("PASSWORD", password);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(signUp.this, "Account Created Successfully!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Exception exception = task.getException();
                                    if (exception instanceof FirebaseAuthUserCollisionException) {
                                        // Email address already in use
                                        Toast.makeText(signUp.this, "Email address already in use.",
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        // Other failure (e.g. weak password)
                                        Toast.makeText(signUp.this, "There was an Error when Creating the Account.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                    Log.w(TAG, "createUserWithEmail:failure", exception);
                                }
                            }
                        });
            }
        });


        //Click go to log in
        signUpCreate=findViewById(R.id.signUpTxt);
        signUpCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity(new Intent(signUp.this, LoginActivity.class));
            }
        });

    }
}