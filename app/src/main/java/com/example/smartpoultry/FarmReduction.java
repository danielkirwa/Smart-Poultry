package com.example.smartpoultry;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class FarmReduction extends AppCompatActivity {
    Spinner eggReduction,chickenReduction,chickReduction;
    String reductionReason;
    TextView updateDateChicken,updateDateChick;
    TextView updateDateEgg;
    ImageButton closeEggs,closeChicken,closeChick;
    LinearLayout popupEggs,popupChicken,popupChick;
    RadioGroup radioGroup;

    Calendar calendar = Calendar.getInstance();
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH);
    final int day = calendar.get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_reduction);


        eggReduction = findViewById(R.id.egg_reduction_spinner);
        updateDateChicken =  findViewById(R.id.update_date_chicken);
        updateDateChick =  findViewById(R.id.update_date_chick);
        chickenReduction = findViewById(R.id.chicken_reduction_spinner);
        chickReduction = findViewById(R.id.chick_reduction_spinner);
        updateDateEgg = findViewById(R.id.update_date_egg);
        closeEggs = findViewById(R.id.close_edit_eggs);
        closeChick = findViewById(R.id.close_edit_chick);
        closeChicken  = findViewById(R.id.close_edit_chicken);
        popupEggs = findViewById(R.id.pop_update_eggs);
        popupChick = findViewById(R.id.pop_update_chick);
        popupChicken = findViewById(R.id.pop_update_chicken);
        radioGroup = findViewById(R.id.radio_selected_reduction);


        hideAllPopUpUpdate();

        // spinner array egg reduction reasons

        final ArrayList<String> EggsReasons = new ArrayList<>();
        EggsReasons.add("Select Reason");
        EggsReasons.add("Sale");
        EggsReasons.add("Donation");
        EggsReasons.add("Consumption");
        EggsReasons.add("Destruction");
        EggsReasons.add("Stolen");

        ArrayAdapter<String> EggsReasonsAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,EggsReasons);
        eggReduction.setAdapter(EggsReasonsAdapter);
        eggReduction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                reductionReason = EggsReasons.get(position);
                //showMessage("Egg reduction","Selected"+reductionReason );

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // spinner array chicken reduction reasons

        final ArrayList<String> ChickenReasons = new ArrayList<>();
        ChickenReasons.add("Select Reason");
        ChickenReasons.add("Sale");
        ChickenReasons.add("Donation");
        ChickenReasons.add("Consumption");
        ChickenReasons.add("Death");
        ChickenReasons.add("Stolen");

        ArrayAdapter<String> chickenReasonsAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,ChickenReasons);
        chickenReduction.setAdapter(chickenReasonsAdapter);
        chickenReduction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                reductionReason = ChickenReasons.get(position);
               // showMessage("Egg reduction","Selected"+reductionReason );

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // spinner array chicken reduction reasons

        final ArrayList<String> ChickReasons = new ArrayList<>();
        ChickReasons.add("Select Reason");
        ChickReasons.add("Sale");
        ChickReasons.add("Donation");
        ChickReasons.add("Consumption");
        ChickReasons.add("Death");
        ChickReasons.add("Stolen");

        ArrayAdapter<String> chickReasonsAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,ChickReasons);
        chickReduction.setAdapter(chickReasonsAdapter);
        chickReduction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                reductionReason = ChickReasons.get(position);
                //showMessage("Egg reduction","Selected"+reductionReason );

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



          radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                  switch (checkedID){
                      case R.id.radio_update_egg:
                          hideAllPopUpUpdate();
                          popupEggs.setVisibility(View.VISIBLE);
                          break;
                      case R.id.radio_update_chick:
                          hideAllPopUpUpdate();
                          popupChick.setVisibility(View.VISIBLE);
                          break;
                      case R.id.radio_update_chicken:
                          hideAllPopUpUpdate();
                          popupChicken.setVisibility(View.VISIBLE);
                          break;
                  }
              }
          });


        // get date

        updateDateChicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecteDate(updateDateChicken);
            }
        });
        updateDateChick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecteDate(updateDateChick);
            }
        });
        updateDateEgg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecteDate(updateDateEgg);
            }
        });


   // close pop ups for editing
        closeEggs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radioGroup.clearCheck();
                popupEggs.setVisibility(View.GONE);
            }
        });
        closeChicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radioGroup.clearCheck();
                popupChicken.setVisibility(View.GONE);
            }
        });
        closeChick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radioGroup.clearCheck();
                popupChick.setVisibility(View.GONE);
            }
        });



    }



    public void showMessage(String title,String message){
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setIcon(getResources().getDrawable(R.drawable.chicken2));
        builder.setPositiveButton("OK",null);
        builder.show();
    }

    public void selecteDate(TextView dateholder) {

        DatePickerDialog datePickerDialog = new DatePickerDialog(FarmReduction.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month +1;
                String date = day +"/"+month+"/"+year;
                dateholder.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();
    }

    public void hideAllPopUpUpdate(){
        popupChick.setVisibility(View.GONE);
        popupChicken.setVisibility(View.GONE);
        popupEggs.setVisibility(View.GONE);
    }
}