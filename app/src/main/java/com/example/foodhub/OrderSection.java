package com.example.foodhub;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class OrderSection extends AppCompatActivity {
    double subtotal=0;
    TextView orderTxt;
    Button proceed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_section);
        ActionBar ac = getSupportActionBar();
        ac.hide();

           orderTxt=(TextView) findViewById(R.id.subTotal);
           proceed=findViewById(R.id.proceedBtn);

           proceed.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(OrderSection.this, Drinks.class);
                   intent.putExtra("key", orderTxt.getText().toString());
                   startActivity(intent);
                   finish();
               }
           });


    }
    //For checked box condition
    public void order(View view){
        boolean checked = ((CheckBox)view).isChecked();

        switch (view.getId()){
            case R.id.inasal:
                if(checked){
                    subtotal +=360;
                }else {
                    subtotal -=360;
                }
                break;
            case R.id.mechado:
                if(checked){
                    subtotal+=355;
                }else {
                    subtotal -=355;
                }
                break;
            case R.id.empada:
                if(checked){
                    subtotal +=410;
                }else {
                    subtotal -=410;
                }
                break;
            case R.id.adobo:
            case R.id.humba:
                if(checked){
                    subtotal +=450;
                }else {
                    subtotal -=450;
                }
                break;
            case R.id.bopis:
                if(checked){
                    subtotal +=310;
                }else {
                    subtotal -=310;
                }
                break;
            case R.id.lobster:
                if(checked){
                    subtotal +=350;
                }else {
                    subtotal -=350;
                }
                break;
            case R.id.bangus:
                if(checked){
                    subtotal+=490;
                }else {
                    subtotal -=490;
                }
                break;
            case R.id.grilled:
                if(checked){
                    subtotal +=390;
                }else {
                    subtotal -=390;
                }
                break;
        }

        orderTxt.setText(subtotal+"");
    }
}