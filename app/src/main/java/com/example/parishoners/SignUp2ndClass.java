package com.example.parishoners;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SignUp2ndClass extends AppCompatActivity {
    ImageView backBtn,logo;
    Button next,login;
    TextView titletxt,slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2nd_class);

        //Hooks
        backBtn = findViewById(R.id.signup_back_button);
        next = findViewById(R.id.signup_next_btn);
        logo = findViewById(R.id.logo);
        login = findViewById(R.id.login);
        titletxt = findViewById(R.id.title_text);
        slogan = findViewById(R.id.slogan);
    }



    public void callNextSignupScreen(View view){
        Intent intent = new Intent(getApplicationContext(), SignUp3rdClass.class);

        //Add transition
        Pair[]pairs = new Pair[6];

        pairs[0]= new Pair<View,String>(backBtn,"transition_back_arrow_btn");
        pairs[1]= new Pair<View,String>(next,"transition_next_btn");
        pairs[2]= new Pair<View,String>(logo,"transition_logo_image");
        pairs[3]= new Pair<View,String>(login,"transition_login_btn");
        pairs[4]= new Pair<View,String>(titletxt,"transition_title_text");
        pairs[5]= new Pair<View,String>(slogan,"transition_slogan_text");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp2ndClass.this,pairs);
        startActivity(intent,options.toBundle());

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUp2ndClass.super.onBackPressed();
            }
        });
    }
}
