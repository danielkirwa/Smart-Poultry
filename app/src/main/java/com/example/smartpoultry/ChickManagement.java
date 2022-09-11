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

public class ChickManagement extends AppCompatActivity {
     DbHelper dbHelper;
     ChickAdapter chickAdapter;
    Spinner chickspinner,chickagespinner;
    EditText etchickDate,chickname,chicknumber,txtmodeacq,txtdateacq,txtchicknote;
    String chickbread,chickage;
    Button btnaddchick,btnviewchick,togglechickview;
    LinearLayout addchickview,availablechicks;
    RecyclerView recyclerView;
    ArrayList<String> name,number,bread,date_added,age;
    TextView tvtotalChick,tvflockcount,tvtotalChick2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chick_management);

        dbHelper = new DbHelper(this);

        addchickview = findViewById(R.id.add_new_chick_flock);
        availablechicks = findViewById(R.id.available_chick_layout);
        togglechickview = findViewById(R.id.toggle_view_chick);
        chickspinner = findViewById(R.id.chick_spinner);
        chickagespinner = findViewById(R.id.chick_age_spinner);
        btnaddchick = findViewById(R.id.btn_add_chick);
        chickname = findViewById(R.id.txt_chick_name);
        chicknumber = findViewById(R.id.txt_chick_number);
        etchickDate = findViewById(R.id.etchickdate);
        txtmodeacq = findViewById(R.id.txt_chick_modeacq);
        txtdateacq = findViewById(R.id.etchickdate);
        txtchicknote = findViewById(R.id.txt_chick_note);
        btnviewchick = findViewById(R.id.btn_view_chick);
        tvtotalChick = findViewById(R.id.tv_total_chick);
        tvflockcount = findViewById(R.id.tv_chick_flock_count);
        tvtotalChick2 = findViewById(R.id.tv_total_chick_2);


        addchickview.setVisibility(View.GONE);
        // display snapshot of sum of data in database
        try {
            tvtotalChick.setText(String.format(": %s",dbHelper.ChickSum()));
            tvtotalChick2.setText(String.format("Total Chicks: %s",dbHelper.ChickSum()));
            tvflockcount.setText("Total flocks : " + dbHelper.ChickFlockCount());
        }catch(Exception ex){
            //Toast.makeText(this, "Error" + ex, Toast.LENGTH_SHORT).show();
            showMessage("Chick Management Error ","Message : "+ ex.getMessage());
        }

         /// initialize array

        name = new ArrayList<>();
        number = new ArrayList<>();
        bread = new ArrayList<>();
        date_added = new ArrayList<>();
        age = new ArrayList<>();
        recyclerView = findViewById(R.id.chick_recycler_view);
        chickAdapter = new ChickAdapter(this, name,number,bread,date_added,age);
        recyclerView.setAdapter(chickAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayAllChickFlock();


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


         togglechickview.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 togglechickenview();
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

        //insert chick to database
        btnaddchick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    boolean isInserted = dbHelper.insertChick(chickname.getText().toString(), chickbread,
                            chicknumber.getText().toString(), txtmodeacq.getText().toString(),
                            chickage, txtdateacq.getText().toString(), txtchicknote.getText().toString());

                    if (isInserted == true) {
                        //Toast.makeText(ChickManagement.this, "Chick flock added", Toast.LENGTH_SHORT).show();
                        showMessage("Chick Management Success","Message : Chick flock details save successfully");
                        resetForm();
                    } else {
                        //Toast.makeText(ChickManagement.this, "Chick flock not added", Toast.LENGTH_SHORT).show();
                        showMessage("Chick Management Fail","Message : Failed to add chick flock details");
                        resetForm();
                    }
                }catch (Exception ex){
                    //Toast.makeText(ChickManagement.this, "Error" +  ex.getMessage(), Toast.LENGTH_SHORT).show();
                    showMessage("Chicken Management Error","Message : " + ex.getMessage());
                }

            }
        });

        btnviewchick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // view chick

            }
        });




    }

    private void displayAllChickFlock() {
        Cursor cursor = dbHelper.getAllChickData();
        if (cursor.getCount() == 0)
        {
            // Toast.makeText(this, "No Chick Flock yet", Toast.LENGTH_SHORT).show();
            showMessage("Chick Management","No flock added yet");
        }else{
            while (cursor.moveToNext()){
                name.add(cursor.getString(1));
                bread.add(cursor.getString(2));
                number.add(cursor.getString(3));
                date_added.add(cursor.getString(6));
                // compute age here
                age.add(cursor.getString(5));


            }

        }
    }

    // toggle view in chick activity
    public void togglechickenview(){
        if (addchickview.getVisibility() == View.GONE){
            availablechicks.setVisibility(View.GONE);
            addchickview.setVisibility(View.VISIBLE);
            togglechickview.setText("View Flock");
            togglechickview.setTextSize(16);
        }else{
            availablechicks.setVisibility(View.VISIBLE);
            addchickview.setVisibility(View.GONE);
            togglechickview.setText("+");
            togglechickview.setTextSize(32);

        }

    }


    public void showMessage(String title,String message){
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setIcon(getResources().getDrawable(R.drawable.chicks2));
        builder.setPositiveButton("OK",null);
        builder.show();
    }
    public  void  resetForm(){
        chickname.setText("");
        chicknumber.setText("");
        txtmodeacq.setText("");
        txtchicknote.setText("");
    }






}
