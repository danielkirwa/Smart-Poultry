package com.example.smartpoultry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity {
    CardView btncallvaccination,btncallmedication,btncallegg,btncallchicken,btncallchick,btncallrecords;
    private DrawerLayout drawer;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btncallvaccination = findViewById(R.id.callvaccination);
        btncallmedication = findViewById(R.id.callmedication);
        btncallegg = findViewById(R.id.calleggproduction);
        btncallchicken = findViewById(R.id.callchickenmanagement);
        btncallchick = findViewById(R.id.callchickmanagement);
        btncallrecords = findViewById(R.id.callfarmrecords);

        Toolbar toolbar = findViewById(R.id.toobar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        //open vaccination
        btncallvaccination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,VaccinationManagement.class);
                startActivity(intent);
                //finish();
            }
        });

        // call chicken management
        btncallchicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,ChickenManagement.class);
                startActivity(intent);
                //finish();
            }
        });
        // call chick management
        btncallchick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,ChickManagement.class);
                startActivity(intent);
                //finish();
            }
        });
        // call egg production
        btncallegg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,EggProductuon.class);
                startActivity(intent);
                //finish();
            }
        });

        // call chick management
        btncallrecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,FarmRecords.class);
                startActivity(intent);
                //finish();
            }
        });
        // call disease
        btncallmedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,DiseaseMedication.class);
                startActivity(intent);
                //finish();
            }
        });

         navigationView = findViewById(R.id.nav_view);
         navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 switch (item.getItemId()){
                     case R.id.menu_housing:
                         Intent intenthousing = new Intent(Dashboard.this,PoultryHousing.class);
                         startActivity(intenthousing);
                         return true;
                     case R.id.menu_feeds:
                         Intent intentfeeds = new Intent(Dashboard.this,FeedFormulation.class);
                         startActivity(intentfeeds);
                         return true;
                     case R.id.menu_development:
                         Intent intentdevelopment = new Intent(Dashboard.this,PoultryDevelopment.class);
                         startActivity(intentdevelopment);
                         return true;
                     case R.id.menu_guide:
                         Intent intentappguide = new Intent(Dashboard.this,AppGuide.class);
                         startActivity(intentappguide);
                         return true;
                     case R.id.menu_reduction:
                         Intent intentfarmreduction = new Intent(Dashboard.this,FarmReduction.class);
                         startActivity(intentfarmreduction);
                         return true;
                     case R.id.menu_logout:
                         Intent intentlogout = new Intent(Dashboard.this,AuthActivity.class);
                         startActivity(intentlogout);
                         return true;

                 }
                 return true;
             }
         });



    }


    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }

}
