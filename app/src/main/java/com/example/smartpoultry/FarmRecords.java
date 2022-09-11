package com.example.smartpoultry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.collect.Table;

public class FarmRecords extends AppCompatActivity {
    DbHelper dbHelper;
    TextView farmType,smallType,smallPercent,largeType,largePercent;
    TextView eggCollection,allEggs,goodEggs,badEggs,averageCollection,averageBad,damageRate;
    TextView allFlocks,chickenFlock,chickFlock,allPoultry,chickenNumber,chickNumber;
    int totalChicks,totalChicken,totalPoultry;
    int totalEggs,totalGoodEggs,totalBadEggs,totalCollection;
    int totalFlocks,totalChickenFlock,totalChickFlock;
    double chickPercent,chickenPercent;
    double averageEggs,averageBadEggs,eggDamage;

    // hack variables
    String eggCollectionCount;
    String hktotalChickenFlock,hktotalChickFlock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_records);

        dbHelper = new DbHelper(this);

        farmType = findViewById(R.id.rp_farm_type);
        smallType = findViewById(R.id.rp_small_type);
        smallPercent = findViewById(R.id.rp_small_percent);
        largeType = findViewById(R.id.rp_large_type);
        largePercent = findViewById(R.id.rp_large_percent);
        eggCollection = findViewById(R.id.rp_total_egg_collection);
        allEggs = findViewById(R.id.rp_total_egg_collected);
        goodEggs = findViewById(R.id.rp_total_good_eggs);
        badEggs = findViewById(R.id.rp_total_bad_eggs);
        averageCollection = findViewById(R.id.rp_average_collection);
        averageBad = findViewById(R.id.rp_average_bad_eggs);
        damageRate = findViewById(R.id.rp_egg_damage_rate);
        allFlocks = findViewById(R.id.rp_total_flocks);
        chickenFlock = findViewById(R.id.rp_total_chicken_flock);
        chickFlock = findViewById(R.id.rp_total_chick_flock);
        allPoultry = findViewById(R.id.rp_total_poultry_number);
        chickenNumber = findViewById(R.id.rp_total_chicken_number);
        chickNumber = findViewById(R.id.rp_total_chick_number);


        // compute farm type
        farmTypeDisplay();
        farmEggReport();
        farmPoultryReport();


    }

    private void farmPoultryReport() {
        try{

            totalChicks = dbHelper.ChickSum();
            totalChicken = dbHelper.ChickenSum();
            totalPoultry = totalChicken + totalChicks;
            hktotalChickFlock = ""+dbHelper.ChickFlockCount();
            hktotalChickenFlock = ""+ dbHelper.ChickenFlockCount();
            totalChickenFlock = Integer.parseInt(hktotalChickenFlock);
            totalChickFlock = Integer.parseInt(hktotalChickFlock);
            totalFlocks = totalChickenFlock + totalChickFlock;


            allFlocks.setText(""+totalFlocks);
            chickenFlock.setText(""+totalChickenFlock);
            chickFlock.setText(""+ totalChickFlock);
            allPoultry.setText(""+totalPoultry);
            chickenNumber.setText(""+totalChicken);
            chickNumber.setText(""+totalChicks);
        }catch(Exception ex){
            Toast.makeText(this, "Error" + ex, Toast.LENGTH_LONG).show();
        }

    }

    // egg report
    private void farmEggReport() {
        try {
            totalGoodEggs = dbHelper.GoodEggSum();
            totalBadEggs = dbHelper.BadEggSum();
            totalEggs = totalBadEggs + totalGoodEggs;
            eggCollectionCount = ""+dbHelper.EggCollectionCount();
            totalCollection = Integer.parseInt(eggCollectionCount);
            // compute
            averageEggs = totalEggs / totalCollection;
            averageBadEggs = totalBadEggs / totalCollection;
            eggDamage = (totalBadEggs * 100)/totalEggs;

            // display data
            eggCollection.setText(eggCollectionCount);
            allEggs.setText("" + totalEggs);
            goodEggs.setText("" + totalGoodEggs);
            badEggs.setText("" + totalBadEggs);
            averageCollection.setText("" + averageEggs);
            averageBad.setText("" + averageBadEggs);
            damageRate.setText("" + eggDamage + "%");


        }catch (Exception ex){
            Toast.makeText(this, "Error" + ex, Toast.LENGTH_LONG).show();
        }

    }

    // display snapshot of sum of data in database
    public void farmTypeDisplay(){

        try {
            totalChicks = dbHelper.ChickSum();
            totalChicken = dbHelper.ChickenSum();
            totalPoultry = totalChicken + totalChicks;

            if(totalChicken >= totalChicks){
                chickenPercent = (totalChicken*100)/totalPoultry;
                chickPercent = (totalChicks*100)/totalPoultry;
                farmType.setText("Chicken farm");
                largeType.setText("Chicken");
                smallType.setText("Chicks");
                largePercent.setText("" + chickenPercent + "%");

                smallPercent.setText("" + chickPercent + "%");
            }else{
                chickenPercent = (totalChicken*100)/totalPoultry;
                chickPercent = (totalChicks*100)/totalPoultry;
                farmType.setText("Chick farm");
                largeType.setText("Chick");
                smallType.setText("Chicken");
                largePercent.setText("" + chickPercent + "%");

                smallPercent.setText("" + chickenPercent + "%");

            }




        }catch(Exception ex){
            Toast.makeText(this, "Error" + ex, Toast.LENGTH_LONG).show();
        }
    }
}
