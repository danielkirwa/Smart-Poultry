package com.example.smartpoultry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class ChickManagement extends AppCompatActivity {
    Spinner chickenspinner;
    EditText etDate;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chick_management);
        chickenspinner = findViewById(R.id.chicken_spinner);


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
