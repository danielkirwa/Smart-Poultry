package com.example.smartpoultry;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class ChickManagement extends AppCompatActivity {
    DbHelper myDb;
    Spinner chickspinner,chickagespinner;
    EditText etchickDate,chickname,chicknumber,txtmodeacq,txtdateacq,txtchicknote;
    String chickbread,chickage;
    Button btnaddchick;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chick_management);
        chickspinner = findViewById(R.id.chick_spinner);
        chickagespinner = findViewById(R.id.chick_age_spinner);
        btnaddchick = findViewById(R.id.btn_add_chick);
        chickname = findViewById(R.id.txt_chick_name);
        chicknumber = findViewById(R.id.txt_chick_number);
        etchickDate = findViewById(R.id.etchickdate);
        txtmodeacq = findViewById(R.id.txt_chick_modeacq);
        txtdateacq = findViewById(R.id.etchickdate);
        txtchicknote = findViewById(R.id.txt_chick_note);

        myDb  = new DbHelper(this);


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        etchickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ChickManagement.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month +1;
                        String date = day +"/"+month+"/"+year;
                        etchickDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });




        // spinner array breads

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
        chickspinner.setAdapter(adapter);
        chickspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                chickbread = chickenBreeds.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // spinner chick age

        final ArrayList<String> chickAge = new ArrayList<>();
        chickAge.add("Select Chick age");
        chickAge.add("0 - 7 days");
        chickAge.add("7 - 14 days");
        chickAge.add("2 - 4 weeks");
        chickAge.add("1 - 2 months");
        chickAge.add("2 - 3 months");
        chickAge.add("3 - 4 months");
        chickAge.add("4 - 6 months");
        ArrayAdapter<String> ageadapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,chickAge);
        chickagespinner.setAdapter(ageadapter);
        chickagespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                chickage = chickAge.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //insert check to database
        btnaddchick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                boolean isinserted =  myDb.inserchickflock(chickname.getText().toString(),chickbread,chicknumber.getText().toString(),
                        txtmodeacq.getText().toString(),chickage,etchickDate.getText().toString(),txtchicknote.getText().toString());

                if (isinserted == true){
                    showMessage("Success","New chick flock added");

                }else{
                    showMessage("Warning","Fail to add flock" );
                }
            }
        });




    }

    // custom popup
    public  void showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
