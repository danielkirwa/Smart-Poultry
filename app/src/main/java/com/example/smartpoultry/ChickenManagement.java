package com.example.smartpoultry;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


public class ChickenManagement extends AppCompatActivity {
    ChickDao chickDao;
EditText etcheckenDate,flockname,flocknumber,flockmode,flocknote;
Spinner chickenspinner;
Button btnaddflock,btnviewchicken,btndeletechicken;
TextView totalfocknumber;
    String flockbread;
DatePickerDialog.OnDateSetListener setListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicken_management);



          chickenspinner = findViewById(R.id.chicken_spinner);
          btnaddflock = findViewById(R.id.save_flock);
        flockname = findViewById(R.id.txt_flock_name);
        flocknumber = findViewById(R.id.txt_flock_number);
        flockmode = findViewById(R.id.txt_flock_modeacq);
        etcheckenDate = findViewById(R.id.etchickendate);
        flocknote = findViewById(R.id.txt_acquisition_note);
        totalfocknumber = findViewById(R.id.total_chicken_display);
        btnviewchicken = findViewById(R.id.btn_view_chicken);
        btndeletechicken = findViewById(R.id.btn_delete_chicken);

        ChickDatabase chickDatabase = ChickDatabase.getInstance(getApplicationContext());
        chickDao = chickDatabase.chickDao();


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        etcheckenDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ChickenManagement.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                         month = month +1;
                         String date = day +"/"+month+"/"+year;
                        etcheckenDate.setText(date);
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
        chickenspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                flockbread = chickenBreeds.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnaddflock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chicken chicken = new Chicken(0,flockname.getText().toString(),flockbread,flocknumber.getText().toString(),flockmode.getText().toString(),etcheckenDate.getText().toString(),flocknote.getText().toString());

                insertChicken(chicken);
            }
        });


        btnviewchicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("Chicks", chickDao.getAllChicken().get(0).flockNumber);
            }
        });



    }


    // my popup
    public  void showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    public void insertChicken(Chicken chicken){
        new InsertChickenAsynTask(chickDao).execute(chicken);
        Toast.makeText(getApplication(), "Added Chicken", Toast.LENGTH_SHORT).show();
    }
    private static class InsertChickenAsynTask extends AsyncTask<Chicken,Void,Void> {

        private ChickDao chickDao;

        private InsertChickenAsynTask(ChickDao chickDao){
            this.chickDao = chickDao;
        }

        @Override
        protected Void doInBackground(Chicken... chickens) {
            chickDao.insertChicken(chickens[0]);
            return null;
        }
    }

}
