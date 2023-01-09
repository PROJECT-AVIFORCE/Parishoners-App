package com.example.parishoners;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    static final  float END_SCALE = 0.7f;

    RecyclerView featuredRecycler,mostViewedRecycler;
    RecyclerView.Adapter adapter;
    ImageView menuIcon;
    LinearLayout contentView;

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        ImageSlider imageSlider = findViewById(R.id.imageslider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.image2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image6, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image4, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image5, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image7, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image8, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image9, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);


        //Hooks

        featuredRecycler =findViewById(R.id.featured_recycler);
        mostViewedRecycler = findViewById(R.id.most_viewed_recycler);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);

         //menu hooks
        drawerLayout= findViewById(R.id.drawerlayout);
        navigationView= findViewById(R.id.navigationview);




        NavigationDrawer();

        //Functions will be executed automatically when this activity will be created
        featuredRecycler();
        mostViewedRecycler();


    }

    private void NavigationDrawer() {
        //Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.gallery);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();

    }

    private void animateNavigationDrawer() {
        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        //drawerLayout.setScrimColor(Color.TRANSPARENT);

//        drawerLayout.setScrimColor(getResources().getColor(R.color.Light_blue));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
        super.onBackPressed();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }

    private void mostViewedRecycler() {
        mostViewedRecycler.setHasFixedSize(true);
        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<MostViewedHelperClass> mostViewedFeatures = new ArrayList<>();
        mostViewedFeatures.add(new MostViewedHelperClass(R.drawable.loudspeaker_, "Announcements"));
        mostViewedFeatures.add(new MostViewedHelperClass(R.drawable.operation, "Events"));
        mostViewedFeatures.add(new MostViewedHelperClass(R.drawable.resume, "Profile"));
        mostViewedFeatures.add(new MostViewedHelperClass(R.drawable.operation, "Dues"));
        adapter = new MostViewedAdapter(mostViewedFeatures);
        mostViewedRecycler.setAdapter(adapter);
    }

    private void featuredRecycler() {

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<FeaturedHelperClass> features = new ArrayList<>();

        features.add(new FeaturedHelperClass(R.drawable.loudspeaker_,"Announcements","Notification and Announcements of the church is displayed here"));
        features.add(new FeaturedHelperClass(R.drawable.planner,"Events","Upcoming Events of the church is displayed here"));
        features.add(new FeaturedHelperClass(R.drawable.operation,"Dues","Pending Subscription and Dues of the members is displayed here"));
        features.add(new FeaturedHelperClass(R.drawable.resume,"Profile","Indivual Profile of the members is displayed here"));

        adapter = new FeaturedAdapter(features);
        featuredRecycler.setAdapter(adapter);


    }


}