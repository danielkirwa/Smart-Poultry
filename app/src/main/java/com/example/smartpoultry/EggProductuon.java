package com.example.smartpoultry;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class EggProductuon extends AppCompatActivity {
 Spinner chickflockname;
 String flockname;
 ChickDao chickDao;
 Button btnaddegg,btnviewegg;
 EditText numberottray,numberofnottray,numberbadegg,datecollected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egg_productuon);

        chickflockname = findViewById(R.id.chicken_flock_name_spinner);
        numberottray = findViewById(R.id.txt_eggs_tray);
        numberofnottray = findViewById(R.id.txt_egg_not_tray);
        numberbadegg = findViewById(R.id.txt_bad_eggs);
        btnaddegg = findViewById(R.id.btn_add_eggs);
        btnviewegg = findViewById(R.id.btn_view_eggs);
        datecollected = findViewById(R.id.txt_egg_date);
        ChickDatabase chickDatabase = ChickDatabase.getInstance(getApplicationContext());
        chickDao = chickDatabase.chickDao();


        // add date picker
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        datecollected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(EggProductuon.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month +1;
                        String date = day +"/"+month+"/"+year;
                        datecollected.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        numberofnottray.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                int max = 30;


                if (numberofnottray.getText().toString().equals("")){

                }else{
                    if (Integer.parseInt(numberofnottray.getText().toString()) < max){

                    }else{
                        showMessage("Tray contains 30 eggs","Value should be less than 30");
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        // spinner array breads

        final ArrayList<String> chickenName = new ArrayList<>();
        chickenName.add("Select Flock Name");
        chickenName.add("Flock one");
        chickenName.add("Flock two");
        chickenName.add("Flock  three");
        chickenName.add("Flock four");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,chickenName);
        chickflockname.setAdapter(adapter);
        chickflockname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                flockname = chickenName.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnaddegg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int totaleggs = 0;
                totaleggs = (Integer.parseInt(numberottray.getText().toString())*30) + Integer.parseInt(numberofnottray.getText().toString());

                Eggs eggs = new Eggs(0,flockname,totaleggs,numberbadegg.getText().toString(),datecollected.getText().toString());

                insertEgg(eggs);
            }
        });


        // select eggs

        btnviewegg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                showMessage("Eggs", chickDao.getAllEggs().get(0).flockName);



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

    public void insertEgg(Eggs eggs){
        new InsertEggAsynTask(chickDao).execute(eggs);
        Toast.makeText(getApplication(), "Added eggs", Toast.LENGTH_SHORT).show();
    }
    private static class InsertEggAsynTask extends AsyncTask<Eggs,Void,Void> {

        private ChickDao chickDao;

        private InsertEggAsynTask(ChickDao chickDao){
            this.chickDao = chickDao;
        }

        @Override
        protected Void doInBackground(Eggs... eggs) {
            chickDao.insertEggg(eggs[0]);
            return null;
        }
    }


}
