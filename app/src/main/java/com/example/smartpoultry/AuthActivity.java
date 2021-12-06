package com.example.smartpoultry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AuthActivity extends AppCompatActivity {
    Button btmlogin,btncreateaccount,btnforgetpass,btncalllogin,btncallcreateaccount;
    LinearLayout logiform,registerform;
    Spinner regionspinner,countyspinner;
    final ArrayList<String> regionloaction= new ArrayList<>();
    final ArrayList<String> countyloaction= new ArrayList<>();
    int regionnumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        registerform = findViewById(R.id.registration_layout);
        logiform = findViewById(R.id.login_layout);
        btncallcreateaccount = findViewById(R.id.call_registration);
        btncalllogin = findViewById(R.id.call_login);
        btmlogin = findViewById(R.id.btn_login);
        regionspinner = findViewById(R.id.region_spinner);
        countyspinner = findViewById(R.id.county_spinner);

        btncallcreateaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logiform.setVisibility(View.GONE);
                registerform.setVisibility(View.VISIBLE);
            }
        });
        btncalllogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerform.setVisibility(View.GONE);
                logiform.setVisibility(View.VISIBLE);
            }
        });
        btmlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(AuthActivity.this,Dashboard.class);
               startActivity(intent);
            }
        });


        registerform.setVisibility(View.GONE);



        // spinner region
        // spinner array
        regionloaction.add("Select your region");
        regionloaction.add("Central");
        regionloaction.add("Coast");
        regionloaction.add("Eastern");
        regionloaction.add("Nairobi");
        regionloaction.add("North Eastern");
        regionloaction.add("Nyanza");
        regionloaction.add("Rift Valley");
        regionloaction.add("Western");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,regionloaction);
        regionspinner.setAdapter(adapter);
        regionspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                regionnumber = regionloaction.indexOf(regionloaction.get(position));

                counties(regionnumber);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void  counties(int countynumber){
            if(countynumber == 1){
                countyloaction.clear();
                countyloaction.add("Select your County");
                countyloaction.add("Nyandarua");
                countyloaction.add("Nyeri");
                countyloaction.add("Kirinyaga");
                countyloaction.add("Murang'a");
                countyloaction.add("Kiambu");
                ArrayAdapter<String> countyadapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,countyloaction);
                countyspinner.setAdapter(countyadapter);
            }else if(countynumber ==2){
                countyloaction.clear();
                countyloaction.add("Select your County");
                countyloaction.add("Mombasa");
                countyloaction.add("Kwale");
                countyloaction.add("Kilifi");
                countyloaction.add("Tana River");
                countyloaction.add("Lamu");
                countyloaction.add("Taita Taveta");
                ArrayAdapter<String> countyadapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,countyloaction);
                countyspinner.setAdapter(countyadapter);
            }else if(countynumber ==3){

            }else if(countynumber ==4){

            }else if(countynumber ==5){

            }else if(countynumber ==6){

            }else if(countynumber ==7){

            }else if(countynumber ==8){

            }else{
                countyloaction.clear();
                countyloaction.add("Select your County");
            }
    }
}
