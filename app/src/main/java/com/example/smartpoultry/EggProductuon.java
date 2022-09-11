package com.example.smartpoultry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EggProductuon extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DbHelper dbHelper;
    EggsAdapter eggsAdapter;
 Spinner chickflockname;
 String flockname;
 Button btnaddegg,btnviewegg,toggleeggview;
 EditText numberottray,numberofnottray,numberbadegg,datecollected;
 LinearLayout addegg,availableegg;
    RecyclerView recyclerView;
    ArrayList<String> name,goodeggs,badeggs,dateeggs;
    TextView totaleggs,totalgoodeggs,totalbadeggs,totalcollection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egg_productuon);

        dbHelper = new DbHelper(this);


        toggleeggview = findViewById(R.id.toggle_view_egg);
        addegg = findViewById(R.id.add_new_egg);
        availableegg = findViewById(R.id.available_egg_layout);
        chickflockname = findViewById(R.id.chicken_flock_name_spinner);
        numberottray = findViewById(R.id.txt_eggs_tray);
        numberofnottray = findViewById(R.id.txt_egg_not_tray);
        numberbadegg = findViewById(R.id.txt_bad_eggs);
        btnaddegg = findViewById(R.id.btn_add_eggs);
        btnviewegg = findViewById(R.id.btn_view_eggs);
        datecollected = findViewById(R.id.txt_egg_date);
        totaleggs = findViewById(R.id.tv_total_eggs);
        totalgoodeggs = findViewById(R.id.tv_total_good_egg);
        totalbadeggs = findViewById(R.id.tv_total_bad_egg);
        totalcollection = findViewById(R.id.tv_total_egg_collection);

        // hide add egg view by default
        addegg.setVisibility(View.GONE);
        // display snapshot of sum of data in database
        try {
            int totalNumberOfEggs = dbHelper.BadEggSum() + dbHelper.GoodEggSum();
            totaleggs.setText(""+ totalNumberOfEggs);
            totalgoodeggs.setText(String.format("Total Good Eggs: %s",dbHelper.GoodEggSum()));
            totalbadeggs.setText("Total Bad Eggs : " + dbHelper.BadEggSum());
            totalcollection.setText("Collections : " + dbHelper.EggCollectionCount());
        }catch(Exception ex){
            //Toast.makeText(this, "Error" + ex, Toast.LENGTH_SHORT).show();
            showMessage("Egg Producton Error " , "Message : "+ ex.getMessage());
        }


        chickflockname.setOnItemSelectedListener(this);
        loadSpinner();

        toggleeggview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleeggview();
            }
        });


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
                        numberofnottray.setText("");
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        // initilize array list
        name = new ArrayList<>();
        goodeggs = new ArrayList<>();
        badeggs = new ArrayList<>();
        dateeggs = new ArrayList<>();

        recyclerView = findViewById(R.id.egg_recycler_view);
        eggsAdapter = new EggsAdapter(this,name,goodeggs,badeggs,dateeggs);
        recyclerView.setAdapter(eggsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayalleggcollection();


        btnaddegg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int totaleggs = 0;
                totaleggs = (Integer.parseInt(numberottray.getText().toString())*30) + Integer.parseInt(numberofnottray.getText().toString());

                try {
                    boolean isInserted = dbHelper.insertEggs(flockname,totaleggs+"",numberbadegg.getText().toString(),datecollected.getText().toString());

                    if (isInserted == true) {
                        //Toast.makeText(EggProductuon.this, "Egg collection added", Toast.LENGTH_SHORT).show();
                        showMessage("Egg Production Success " , "Message :Egg details save successfully ");
                        resetForm();
                    } else {
                        //Toast.makeText(EggProductuon.this, "Egg collection not added", Toast.LENGTH_SHORT).show();
                        showMessage("Egg Production Error " , "Message : Failed to save egg collection details try again ");
                        resetForm();
                    }
                }catch (Exception ex){
                    //Toast.makeText(EggProductuon.this, "Error" +  ex.getMessage(), Toast.LENGTH_SHORT).show();
                    showMessage("Egg Production Error " , "Message : " + ex.getMessage());
                }
            }
        });


        // select eggs

        btnviewegg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             // view gg

            }
        });



    }

    private void displayalleggcollection() {
        Cursor cursor = dbHelper.getAllEggsData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No Egg collected yet", Toast.LENGTH_SHORT).show();
            return;
        }else{
            while(cursor.moveToNext()){
                name.add(cursor.getString(1));
                goodeggs.add(cursor.getString(2));
                badeggs.add(cursor.getString(3));
                dateeggs.add(cursor.getString(4));

            }
        }
    }

    // toggle view in egg activity
    public void toggleeggview(){
        if (addegg.getVisibility() == View.GONE){
            availableegg.setVisibility(View.GONE);
            addegg.setVisibility(View.VISIBLE);
            toggleeggview.setText("View Eggs");
            toggleeggview.setTextSize(16);
        }else{
            availableegg.setVisibility(View.VISIBLE);
            addegg.setVisibility(View.GONE);
            toggleeggview.setText("+");
            toggleeggview.setTextSize(32);

        }

    }

    // custom popup


    private void loadSpinner(){
        try {
            List<String> lables = dbHelper.getChickenFlockList();
            // creating adapter for spinner
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lables);
            // drop down layout style list view radio button
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // attaching data to adapter to spinner
            chickflockname.setAdapter(arrayAdapter);
        }catch (Exception ex){
            Toast.makeText(this, "Error" + ex, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedFlockName = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, "Flock name is :"  + selectedFlockName, Toast.LENGTH_SHORT).show();
        flockname = selectedFlockName;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void showMessage(String title,String message){
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setIcon(getResources().getDrawable(R.drawable.chicks2));
        builder.setPositiveButton("OK",null);
        builder.show();
    }
    public  void  resetForm(){
        numberottray.setText("");
        numberofnottray.setText("");
        numberbadegg.setText("");datecollected.setText("");
    }

}
