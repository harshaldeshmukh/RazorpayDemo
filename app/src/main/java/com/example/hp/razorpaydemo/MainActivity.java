package com.example.hp.razorpaydemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {
  EditText amount;
  Button pay;
  int payamount=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount=(EditText)findViewById(R.id.amount);

        pay=(Button)findViewById(R.id.pay);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });
    }

    private void startPayment() {

        payamount=Integer.parseInt(amount.getText().toString());
        Checkout checkout = new Checkout();
        checkout.setImage(R.mipmap.ic_launcher);
        final Activity activity=this;
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("description","Order #123");
            jsonObject.put("currency","INR");
            jsonObject.put("amount",payamount*100);
            checkout.open(activity,jsonObject);

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(getApplicationContext(),"Sucess",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
    }
}
