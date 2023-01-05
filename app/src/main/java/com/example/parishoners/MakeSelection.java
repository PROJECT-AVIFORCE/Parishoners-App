package com.example.parishoners;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MakeSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_selection);

        Button otp=findViewById(R.id.otp_btn);
        Button eotp=findViewById(R.id.email_otp);

        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MakeSelection.this, OTP.class);
                startActivity(i);
            }
        });

        eotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MakeSelection.this,ForgetPassword.class);
                startActivity(i);
            }
        });
    }
}