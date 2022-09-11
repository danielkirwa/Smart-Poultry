package com.example.smartpoultry;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ChickenManagement extends AppCompatActivity {
DbHelper dbHelper;
ChickenAdapter chickenAdapter;
EditText etcheckenDate,flockname,flocknumber,flockmode,flocknote;
Spinner chickenspinner;
Button btnaddflock,btnviewchicken,btndeletechicken,togglechickenview;
TextView totalfocknumber, tvflockcount,totalnumber;
    String flockbread;
    LinearLayout availablechickenflock,addnewchickenflock;
    RecyclerView recyclerView;
    ArrayList<String> name,number,bread,date_acq,note;
DatePickerDialog.OnDateSetListener setListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicken_management);

         dbHelper = new DbHelper(this);

            availablechickenflock = findViewById(R.id.available_chicken_layout);
        addnewchickenflock = findViewById(R.id.add_new_chicken_flock);
        togglechickenview = findViewById(R.id.toggle_view_chicken);
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
        tvflockcount = findViewById(R.id.tv_chicken_flock_count);
        totalnumber = findViewById(R.id.tv_chicken_number);



        // initilize array list
        name = new ArrayList<>();
        number = new ArrayList<>();
        bread = new ArrayList<>();
        date_acq = new ArrayList<>();
        note = new ArrayList<>();
        recyclerView = findViewById(R.id.chicken_recycler_view);
        chickenAdapter = new ChickenAdapter(this,name,number,bread,date_acq,note);
        recyclerView.setAdapter(chickenAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayallChickenFlock();



       // hide add flock view by default
        addnewchickenflock.setVisibility(View.GONE);
        // display snapshot of sum of data in database
        try {
            totalfocknumber.setText(String.format(": %s",dbHelper.ChickenSum()));
            totalnumber.setText("" + dbHelper.ChickenSum());
            tvflockcount.setText("" + dbHelper.ChickenFlockCount());
            }catch(Exception ex){
            //Toast.makeText(this, "Error" + ex, Toast.LENGTH_SHORT).show();
            showMessage("Chicken Management Error","Message : "+ex.getMessage());
        }



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
                // add chicken
                try {
                    boolean isInserted = dbHelper.insertChicken(flockname.getText().toString(),flockbread.toString(),flocknumber.getText().toString(),flockmode.getText().toString(),etcheckenDate.getText().toString(),flocknote.getText().toString());
                    if (isInserted == true) {
                        //Toast.makeText(ChickenManagement.this, "Chicken flock added", Toast.LENGTH_SHORT).show();
                        showMessage("Chicken Management Success","Message : Chicken flock details added successfully");
                    } else {
                        //Toast.makeText(ChickenManagement.this, "Chicken flock Not added", Toast.LENGTH_SHORT).show();
                        showMessage("Chicken Management Error","Message :  Failed to save flock details try again");
                    }
                }catch(Exception ex){
                   // Toast.makeText(ChickenManagement.this, "Error" + ex, Toast.LENGTH_SHORT).show();
                    showMessage("Chicken Management Error","Message : "+ex.getMessage());
                }
            }
        });




        btnviewchicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // view chicken
            }
        });

       // set toggle to button
        togglechickenview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglechickenview();
            }
        });

    }

    private void displayallChickenFlock() {
        Cursor  cursor = dbHelper.getAllChickenData();
        if (cursor.getCount() == 0){
            //Toast.makeText(this, "No Flock yet", Toast.LENGTH_SHORT).show();
            showMessage("Chicken Management","No flock added yet");
            return;
        }else{
            while(cursor.moveToNext()){
                name.add(cursor.getString(1));
                number.add(cursor.getString(3));
                bread.add(cursor.getString(2));
                date_acq.add(cursor.getString(5));
                note.add(cursor.getString(6));

            }
        }
    }

    // toggle view in chicken activity
    public void togglechickenview(){
        if (addnewchickenflock.getVisibility() == View.GONE){
            availablechickenflock.setVisibility(View.GONE);
            addnewchickenflock.setVisibility(View.VISIBLE);
            togglechickenview.setText("View Flock");
            togglechickenview.setTextSize(16);
        }else{
            availablechickenflock.setVisibility(View.VISIBLE);
            addnewchickenflock.setVisibility(View.GONE);
            togglechickenview.setText("+");
            togglechickenview.setTextSize(32);

        }

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







}
