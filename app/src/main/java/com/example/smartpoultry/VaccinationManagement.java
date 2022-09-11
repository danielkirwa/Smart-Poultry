package com.example.smartpoultry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class VaccinationManagement extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DbHelper dbHelper ;
    VaccineAdapter vaccineAdapter;

Button opentable,openenroll,enrollflock;
LinearLayout enrollform,table;
Spinner chickflock,vaccine;
String flockname,vaccineName;
EditText editTextDate,vaccineNote;
String nextVaccine;
long nextdate;
int mareksdays = 0;
int testVaccine = 0;
int newcastleIB1 = 7;
int gumboro1 = 14;
int gumboro2 = 21;
int newcastleIB2 = 28;
int fawlpox = 42;
int fawltyphoid = 58;
int dewormer = 116;
int oneday = 86400;
int twomonths = 62;
Calendar newcalender;
    Date nextVaccinationdate;
TimeZone timeZone;
SimpleDateFormat simpleDateFormat;
    String selectedFlockName;

    RecyclerView recyclerView;
    ArrayList<String> enrollname,enrollvaccine,enrolldate,enrollnote;

    private static final String TAG = "VaccinationManagement";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination_management);

        dbHelper = new DbHelper(this);


        openenroll = findViewById(R.id.show_enroll);
        opentable = findViewById(R.id.show_timetable);
        enrollform = findViewById(R.id.add_vaccination);
        table = findViewById(R.id.vaccination_table);
        chickflock = findViewById(R.id.chick_flock_name_spinner);
        vaccine = findViewById(R.id.vaccine_name_spinner);
        editTextDate = findViewById(R.id.etdate);
        enrollflock = findViewById(R.id.btn_enroll_flock);
        vaccineNote = findViewById(R.id.txt_vaccine_note);

        newcalender = Calendar.getInstance();
        timeZone = newcalender.getTimeZone();
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy  hh:mm");
        nextVaccinationdate = new Date();

        enrollname = new ArrayList<>();
        enrollvaccine = new ArrayList<>();
        enrolldate = new ArrayList<>();
        enrollnote = new ArrayList<>();

        displayalAllEnrolls();

        recyclerView = findViewById(R.id.enrolled_recycler_view);
        vaccineAdapter = new VaccineAdapter(this, enrollname, enrollvaccine, enrolldate, enrollnote);
        recyclerView.setAdapter(vaccineAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        table.setVisibility(View.GONE);
        chickflock.setOnItemSelectedListener(this);
        loadSpinner();

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


        enrollflock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean accountCreated = dbHelper.insertVaccineRecord(selectedFlockName.toString(),nextVaccine.toString(),editTextDate.getText().toString(),vaccineNote.getText().toString());
                if(accountCreated == true){
                    showMessage("Vaccination  Success","Message : Flock enrolled ");
                }else{
                    showMessage("Vaccination  Error","Message :  Failed enroll try again");
                }


                createNotification(nextVaccinationdate.getTime());
            }
        });

        // spinner array vaccine

        final ArrayList<String> vaccineNameSpinner = new ArrayList<>();
        vaccineNameSpinner.add("Mareks");
        vaccineNameSpinner.add("TestVaccine");
        vaccineNameSpinner.add("Newcastle/IB 1");
        vaccineNameSpinner.add("Gumboro 1");
        vaccineNameSpinner.add("Gumboro 2");
        vaccineNameSpinner.add("Newcastle/IB 2");
        vaccineNameSpinner.add("Fowl Pox");
        vaccineNameSpinner.add("Fowl Typhoid");
        vaccineNameSpinner.add("Dewormer");
        vaccineNameSpinner.add("Newcastle/IB");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,vaccineNameSpinner);
        vaccine.setAdapter(adapter);
        vaccine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                long tslong = System.currentTimeMillis()/1000;
                vaccineName = vaccineNameSpinner.get(position);
                if(vaccineName == "TestVaccine" ){
                    getDate(tslong + (testVaccine*oneday));
                    nextVaccine = "Mareks";
                }else if(vaccineName == "Mareks" ){
                    getDate(tslong + (newcastleIB1*oneday));
                    nextVaccine = "Newcastle/IB 1";
                }else if(vaccineName == "Newcastle/IB 1" ){
                    getDate(tslong + (gumboro1*oneday));
                    nextVaccine = "Gumboro 1";
                }else if(vaccineName == "Gumboro 1" ){
                    getDate(tslong + (gumboro2*oneday));
                    nextVaccine = "Gumboro 2";
                }else if(vaccineName == "Gumboro 2" ){
                    getDate(tslong + (newcastleIB2));
                    nextVaccine = "Newcastle/IB 2";
                }else if(vaccineName == "Newcastle/IB 2" ){
                    getDate(tslong + (fawlpox));
                    nextVaccine = "Fowl Pox";
                }else if(vaccineName == "Fowl Pox" ){
                    getDate(tslong + (fawltyphoid*oneday));
                    nextVaccine = "Fowl Typhoid";
                }else if(vaccineName == "Fowl Typhoid" ){
                    getDate(tslong + (dewormer*oneday));
                    nextVaccine = "Dewormer";
                }else if(vaccineName == "Dewormer" ){
                    getDate(tslong + (twomonths*oneday));
                    nextVaccine = "Newcastle/IB";
                }else if(vaccineName == "Newcastle/IB" ){
                    getDate(tslong + (twomonths*oneday));
                    nextVaccine = "Newcastle/IB";
                }else{
                    Toast.makeText(VaccinationManagement.this, "Fail to Pick Vaccine", Toast.LENGTH_SHORT).show();
                }
                // get time stamp


               // Toast.makeText(VaccinationManagement.this, "Current vaccine : " + vaccineName + " Next Vaccine : " + nextVaccine + " On :" + nextVaccinationdate, Toast.LENGTH_SHORT).show();
                showMessage("Vaccination Reminder ","Current vaccine : " + vaccineName + "\n" + "Next vaccine : " + nextVaccine + "\n"  + "Date/Time : " + nextVaccinationdate);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // calender

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(VaccinationManagement.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month +1;
                        String date = day +"/"+month+"/"+year;
                        editTextDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });




    }

    private void createNotification(long timeInMillis) {
        AlarmManager alarmManager =  (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,PoultryAlarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);
        alarmManager.setRepeating(AlarmManager.RTC,timeInMillis,AlarmManager.INTERVAL_DAY,pendingIntent);
        Toast.makeText(this, "Vaccination Reminder set", Toast.LENGTH_SHORT).show();
        showMessage("Vaccination Reminder ","Reminder set : Successfully");


    }


    private void loadSpinner(){
        try {
            List<String> lables = dbHelper.getChickFlockList();
            // creating adapter for spinner
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lables);
            // drop down layout style list view radio button
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // attaching data to adapter to spinner
            chickflock.setAdapter(arrayAdapter);
        }catch (Exception ex){
            //Toast.makeText(this, "Error" + ex, Toast.LENGTH_SHORT).show();
            //showMessage("Vaccination Error","Message : "+  ex.getMessage());
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedFlockName = parent.getItemAtPosition(position).toString();
        ///Toast.makeText(this, "Flock name is :"  + selectedFlockName, Toast.LENGTH_SHORT).show();
        //showMessage("Vaccination","Flock selected : " + selectedFlockName );
        flockname = selectedFlockName;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public Date getDate(long time){
        simpleDateFormat.setTimeZone(timeZone);
        String localTime = simpleDateFormat.format(new Date(time*1000+120000));

        try{
            nextVaccinationdate = simpleDateFormat.parse(localTime);


        }catch (ParseException ex){
           // Toast.makeText(this, "Fail to parse date", Toast.LENGTH_SHORT).show();
            showMessage("Vaccination","Failed to get Date");
        }
        return nextVaccinationdate;

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

    private void displayalAllEnrolls() {
        try {
            Cursor cursor = dbHelper.getAllVaccineRecord();
            if (cursor.getCount() == 0){
                showMessage("Vaccination " ,"No flock enrolled yet");
                return;
            }else{
                while(cursor.moveToNext()){
                    enrollname.add(cursor.getString(1));
                    enrollvaccine.add(cursor.getString(2));
                    enrolldate.add(cursor.getString(3));
                    enrollnote.add(cursor.getString(4));
                }
            }
        }catch (Exception e){
            showMessage("Exception",e.getMessage());
        }

    }


}
