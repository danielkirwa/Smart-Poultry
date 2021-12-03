package com.example.smartpoultry;

import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;


public class ChickenManagement extends AppCompatActivity {
EditText etDate;
Spinner chickenspinner;
DatePickerDialog.OnDateSetListener setListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicken_management);


          etDate = findViewById(R.id.etdate);
          chickenspinner = findViewById(R.id.chicken_spinner);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ChickenManagement.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                         month = month +1;
                         String date = day +"/"+month+"/"+year;
                         etDate.setText(date);
                    }
                },year,month,day);
            datePickerDialog.show();
            }
        });


        // spinner array

        final ArrayList<String> chickenBreeds = new ArrayList<>();
        chickenBreeds.add("Select breed");
        chickenBreeds.add("Kenbro");
        chickenBreeds.add("Kuroiler");
        chickenBreeds.add("Rainbow rooster");
        chickenBreeds.add("Sasso");
        chickenBreeds.add("Kari");
        chickenBreeds.add("Kari kienyeji");
        chickenBreeds.add("Cornish cross");
        chickenBreeds.add("Chantecler");
        chickenBreeds.add("Other/local");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,chickenBreeds);
        chickenspinner.setAdapter(adapter);



    }


}
