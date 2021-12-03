package com.example.smartpoultry;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity {
    CardView btncallvaccination,btncallmedication,btncallegg,btncallchicken,btncallchick,btncallrecords;
    private DrawerLayout drawer;


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
