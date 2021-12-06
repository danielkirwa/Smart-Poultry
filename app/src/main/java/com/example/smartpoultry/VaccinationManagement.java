package com.example.smartpoultry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class VaccinationManagement extends AppCompatActivity {
Button opentable,openenroll;
LinearLayout enrollform,table;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination_management);
        openenroll = findViewById(R.id.show_enroll);
        opentable = findViewById(R.id.show_timetable);
        enrollform = findViewById(R.id.add_vaccination);
        table = findViewById(R.id.vaccination_table);

        table.setVisibility(View.GONE);

        opentable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table.setVisibility(View.VISIBLE);
                enrollform.setVisibility(View.GONE);
            }
        });
        openenroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table.setVisibility(View.GONE);
                enrollform.setVisibility(View.VISIBLE);
            }
        });
    }
}
