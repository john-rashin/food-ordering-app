package com.example.foodhub;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

public class Dashboard extends AppCompatActivity {

    ImageButton chickenPic,porkPic,softDrinkPic,seaFoodPic;
TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ActionBar ac = getSupportActionBar();
        ac.hide();
          name=findViewById(R.id.fullName);
        chickenPic= findViewById(R.id.chicken);
        porkPic=findViewById(R.id.pork);
        softDrinkPic=findViewById(R.id.softDrinks);
        seaFoodPic=findViewById(R.id.seaFoods);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();

        DatabaseReference userRef = database.getReference("users_info/" + userId);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String firstName = dataSnapshot.child("firstName").getValue(String.class);
                String lastName = dataSnapshot.child("lastName").getValue(String.class);
                // do something with the data
                name.setText(firstName + " " + lastName);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        chickenPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // code to execute when chickenPic is clicked
                startActivity(new Intent(Dashboard.this,OrderSection.class));
            }
        });

        porkPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // code to execute when porkPic is clicked
                startActivity(new Intent(Dashboard.this,OrderSection.class));
            }
        });


        softDrinkPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // code to execute when softDrinkPic is clicked
                startActivity(new Intent(Dashboard.this,OrderSection.class));
            }
        });


        seaFoodPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // code to execute when seaFoodPic is clicked
                startActivity(new Intent(Dashboard.this,OrderSection.class));
            }
        });


    }

}