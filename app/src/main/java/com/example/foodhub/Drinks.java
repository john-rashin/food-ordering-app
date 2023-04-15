package com.example.foodhub;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Drinks extends AppCompatActivity {
    EditText cashAmount;
    TextView totalTxt,change;
    Button payBtn;
    double totalPayable=0,cashPay=0,setChange=0;
    private double subTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks);
        ActionBar ac = getSupportActionBar();
        ac.hide();

        totalTxt=findViewById(R.id.subTotal);
        cashAmount=findViewById(R.id.cashAmountTxt);
        change=findViewById(R.id.changeTxt);
        payBtn=findViewById(R.id.paymentBtn);
        Intent intent = getIntent();

        String value = intent.getStringExtra("key");
        if(value != null) {
            subTotal = Double.parseDouble(value);
        }
        totalTxt.setText(subTotal+"");

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalPayable=Double.parseDouble(totalTxt.getText().toString());
                cashPay=Double.parseDouble(cashAmount.getText().toString());

                if(cashPay>=totalPayable){
                    setChange=cashPay-totalPayable;
                    cashAmount.setText("");
                    change.setText(setChange+" ");
                    Toast.makeText(Drinks.this, "Purchase Successful \n Come Again ", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Drinks.this, "Insufficient Balance", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void order(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.coke:
                if (checked) {
                    subTotal += 80;
                } else {
                    subTotal -=  80;
                }
                break;
            case R.id.pepsi:
                if (checked) {
                    subTotal +=  78;
                } else {
                    subTotal -=   78;
                }
                break;
            case R.id.nestie:
                if (checked) {
                    subTotal += 95;
                } else {
                    subTotal -=   95;
                }
                break;
            case R.id.water:
                if (checked) {
                    subTotal += 49;
                } else {
                    subTotal -= 49;
                }
                break;
        }
        totalTxt.setText(subTotal+"");
    }
public void service(View view){
    boolean checked=((RadioButton)view).isChecked();

    switch (view.getId()){
        case R.id.pickUp:
            subTotal -=500;
            Toast.makeText(this, "Please get your order in the counter", Toast.LENGTH_LONG).show();
      break;
        case R.id.delivery:
           if(checked){
               subTotal +=500;
               Toast.makeText(this, "Delivery payment is added to your amount payable", Toast.LENGTH_LONG).show();
           }else{
               subTotal -=500;
           }
            totalTxt.setText(subTotal+"");
        default:
            return;
    }

}
}