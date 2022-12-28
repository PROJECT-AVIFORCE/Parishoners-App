package com.example.parishoners;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class home extends AppCompatActivity implements View.OnClickListener{


    private CardView card1,card2,card3,card4,card5,card6;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        card1 = (CardView) findViewById(R.id.c1);
        card2 = (CardView) findViewById(R.id.c2);
        card3 = (CardView) findViewById(R.id.c3);
        card4 = (CardView) findViewById(R.id.c4);
        card5 = (CardView) findViewById(R.id.c5);
        card6 = (CardView) findViewById(R.id.c6);

        drawerLayout = findViewById(R.id.drawlayout);
        navigationView = findViewById(R.id.navigationview);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);
        card6.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.c1:
                i=new Intent(this, bible.class);
                startActivity(i);
                break;

            case R.id.c2:
                i=new Intent(this, announcement.class);
                startActivity(i);
                break;

            case R.id.c3:
                i=new Intent(this, event.class);
                startActivity(i);
                break;

            case R.id.c4:
                i=new Intent(this, dues.class);
                startActivity(i);
                break;

            case R.id.c5:
                i=new Intent(this, organisation.class);
                startActivity(i);
                break;

            case R.id.c6:
                i=new Intent(this, profile.class);
                startActivity(i);
                break;
        }

    }
}