package com.example.smartpoultry;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.database.Cursor;
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
    DbHelper myDb;
EditText etcheckenDate,flockname,flocknumber,flockmode,flocknote;
Spinner chickenspinner;
Button btnaddflock,btnviewchicken,btndeletechicken;
TextView totalfocknumber;
    String flockbread;
    String deletechickenkey;
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
        myDb  = new DbHelper(this);


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
        //insert checken to database
        btnaddflock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(ChickenManagement.this, "Inserted" + flocknote.getText().toString(), Toast.LENGTH_SHORT).show();

               boolean isinserted =  myDb.inserflock(flockname.getText().toString(),flockbread,flocknumber.getText().toString(),flockmode.getText().toString(),etcheckenDate.getText().toString(),flocknote.getText().toString());

                if (isinserted == true){
                    showMessage("Success","New flock added");

                }else{
                    showMessage("Error","fail to add flock");
                }
            }
        });

//  select all flocks

            btnviewchicken.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cursor chickenres = myDb.getAllChickenData();

                    if(chickenres.getCount() == 0){
                        // error message
                        showMessage("Information","No flock added not");
                        return;
                    }else{
                        StringBuffer buffer = new StringBuffer();

                        while (chickenres.moveToNext()){
                            buffer.append("Id :" + chickenres.getString(0)+"\n");
                            buffer.append("Name :" + chickenres.getString(1)+"\n");
                            buffer.append("Bread :" + chickenres.getString(2)+"\n");
                            buffer.append("Number :" + chickenres.getString(3)+"\n\n");
                            buffer.append("Mode :" + chickenres.getString(4)+"\n");
                            buffer.append("Date :" + chickenres.getString(5)+"\n\n");
                            buffer.append("Note :" + chickenres.getString(6)+"\n");

                        }
                        // show all data
                        showMessage("Flocks",buffer.toString());
                    }

                }
            });
            // delete chicken flock
        btndeletechicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletechickenkey = flockname.getText().toString();
                if(deletechickenkey.equals("")){
                    showMessage("Error","Enter flock id");
                }else{
                    myDb.detelechickenflock(Integer.parseInt(deletechickenkey));
                    showMessage("Information","flock deleted");
                }
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

}
