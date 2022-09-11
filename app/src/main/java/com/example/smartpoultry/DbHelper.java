package com.example.smartpoultry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;
 private  static  final  String DATABASE_NAME = "smartpoultry.db";
    private  static  final  int DATABASE_VERSION = 1;
    private  static  final  String TABLE_CHICKEN = "tblchicken";
    private  static  final  String TABLE_CHICK = "tblchick";
    private  static  final  String TABLE_VACCINE = "tblvaccine";
    private  static  final  String TABLE_VACCINERECORD = "tblvaccinerecord";
    private  static  final  String TABLE_EGGRECORD = "tbleggrecord";
    private  static  final  String TABLE_DISEASE = "tbldisease";
    private  static  final  String TABLE_USERS = "tblusers";
    private  static  final  String TABLE_USERDETAILS = "tbluserdetails";

// TABLES  CHICKEN COLUMNS
    private  static  final  String CHICKEN_ID = "ID";
    private  static  final  String CHICKEN_NAME = "FLOCK_NAME";
    private  static  final  String CHICKEN_BREAD = "FLOCK_BREAD";
    private  static  final  String CHICKEN_NUMBER = "FLOCK_NUMBER";
    private  static  final  String CHICKEN_MODEACQ = "MODE_ACQ";
    private  static  final  String CHICKEN_DATE = "DATE_ACQ";
    private  static  final  String CHICKEN_NOTE = "FLOCK_NOTE";

    //TABLES  CHICKS COLUMNS
    private  static  final  String CHICK_ID = "ID";
    private  static  final  String CHICK_NAME = "CHICK_FLOCK_NAME";
    private  static  final  String CHICK_BREAD = "CHICK_FLOCK_BREAD";
    private  static  final  String CHICK_NUMBER = "CHICK_FLOCK_NUMBER";
    private  static  final  String CHICK_MODEACQ = "CHICK_MODE_ACQ";
    private  static  final  String CHICK_AGE = "CHICK_AGE";
    private  static  final  String CHICK_DATE = "CHICK_DATE_ACQ";
    private  static  final  String CHICK_NOTE = "CHICK_FLOCK_NOTE";

    //TABLES  vaccine COLUMNS
    private  static  final  String VACCINE_ID = "ID";
    private  static  final  String VACCINE_NAME = "VACCINE_NAME";
    private  static  final  String TARGET_AGE = "TARGET_AGE";
    private  static  final  String TARGET_DISEASE = "TARGET_DISEASE";
    private  static  final  String ADMISSION_MODE = "ADMISSION_MODE";
    private  static  final  String VACCINE_NOTE = "VACCINE_NOTE";

    //TABLES  vaccinerecord COLUMNS
    private  static  final  String VACCINERECORD_ID = "ID";
    private  static  final  String VCC_FLOCK_ID_FK = "FLOCK_ID_FK";
    private  static  final  String VACCINE_ID_FK = "VACCINE_ID_FK";
    private  static  final  String VACCINATION_DATE = "VACCINATION_DATE";
    private  static  final  String VACCINATION_NOTE = "VACCINATION_NOTE";

    //TABLES  eggrecord COLUMNS
    private  static  final  String EGGRECORD_ID = "ID";
    private  static  final  String EGG_FLOCK_ID_FK = "FLOCK_ID_FK";
    private  static  final  String GOODEGGS = "GOOD_EGGS";
    private  static  final  String BADEGGS = "BAD_EGGS";
    private  static  final  String COLLECTION_DATE = "COLLECTION_DATE";

    //TABLES  disease COLUMNS
    private  static  final  String DISEASE_ID = "ID";
    private  static  final  String DISEASE = "DISEASE";
    private  static  final  String DATE_REPORTED = "DISEASE_DATE";
    private  static  final  String DISEASE_NOTE = "DISEASE_NOTE";
    private  static  final  String AFFECTED_FLOCK = "DISEASED_FLOCK";

    //TABLES  user COLUMNS
    private  static  final  String USERNAME = "ID";
    private  static  final  String PASSWORD = "PASSWORD";
    private  static  final  String PRIVILEGE = "PRIVILLAGE";

    //TABLES  userdetails COLUMNS
    private  static  final  String EMAIL = "ID";
    private  static  final  String FIRST_NAME = "FIRST_NAME";
    private  static  final  String OTHER_NAME = "OTHER_NAME";
    private  static  final  String PHONE = "USER_PHONE";
    private  static  final  String DATE_CREATED = "CREATED_DATE";
    /*private  static  final  String REGION = "USER_REGION";
    private  static  final  String COUNTY = "USER_COUNTY";
    private  static  final  String CONSTITUENCY = "USER_CONSTITUENCY";
    private  static  final  String USER_IMAGE = "USER_IMAGE";*/




    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     //String Query_Table = " CREATE TABLE " +TABLE_CHICKEN+ "(" +CHICKEN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +CHICKEN_NAME+ " TEXT, " +CHICKEN_BREAD+ " TEXT, " +CHICKEN_NUMBER+ " TEXT, " +CHICKEN_MODEACQ+ " TEXT, " +CHICKEN_DATE+ " TEXT, " +CHICKEN_NOTE+ " TEXT);";

     String query = "CREATE TABLE IF NOT EXISTS " + TABLE_CHICKEN + " ("
                + CHICKEN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CHICKEN_NAME + " TEXT,"
                + CHICKEN_BREAD + " TEXT,"
                + CHICKEN_NUMBER + " TEXT,"
                + CHICKEN_MODEACQ + " TEXT,"
                + CHICKEN_DATE + " TEXT,"
                + CHICKEN_NOTE + " TEXT)";


        String querychicktable = "CREATE TABLE IF NOT EXISTS " + TABLE_CHICK + " ("
                + CHICK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CHICK_NAME + " TEXT,"
                + CHICK_BREAD + " TEXT,"
                + CHICK_NUMBER + " TEXT,"
                + CHICK_MODEACQ + " TEXT,"
                + CHICK_AGE + " TEXT,"
                + CHICK_DATE + " TEXT,"
                + CHICK_NOTE + " TEXT)";

        String queryvaccinetable = "CREATE TABLE IF NOT EXISTS " + TABLE_VACCINE + " ("
                + VACCINE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + VACCINE_NAME + " TEXT,"
                + TARGET_AGE + " TEXT,"
                + TARGET_DISEASE + " TEXT,"
                + ADMISSION_MODE + " TEXT,"
                + VACCINE_NOTE + " TEXT)";

        String queryvaccinationtable = "CREATE TABLE IF NOT EXISTS " + TABLE_VACCINERECORD + " ("
                + VACCINERECORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + VCC_FLOCK_ID_FK + " TEXT,"
                + VACCINE_ID_FK + " TEXT,"
                + VACCINATION_DATE + " TEXT,"
                + VACCINATION_NOTE + " TEXT)";

        String queryeggtable = "CREATE TABLE IF NOT EXISTS " + TABLE_EGGRECORD + " ("
                + EGGRECORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EGG_FLOCK_ID_FK + " TEXT,"
                + GOODEGGS + " TEXT,"
                + BADEGGS + " TEXT,"
                + COLLECTION_DATE + " TEXT)";

        String querydiseasetable = "CREATE TABLE IF NOT EXISTS " + TABLE_DISEASE + " ("
                + DISEASE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DISEASE + " TEXT,"
                + DATE_REPORTED + " TEXT,"
                + DISEASE_NOTE + " TEXT,"
                + AFFECTED_FLOCK + " TEXT)";

        String queryusertable = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + " ("
                + USERNAME + " TEXT PRIMARY KEY, "
                + PASSWORD + " TEXT,"
                + PRIVILEGE + " TEXT)";

        String queryuserdetailstable = "CREATE TABLE IF NOT EXISTS " + TABLE_USERDETAILS + " ("
                + EMAIL + " TEXT PRIMARY KEY, "
                + FIRST_NAME + " TEXT,"
                + OTHER_NAME + " TEXT,"
                + PHONE + " TEXT,"
                + DATE_CREATED + " TEXT)";




        db.execSQL(querychicktable);
        db.execSQL(query);
        db.execSQL(queryvaccinetable);
        db.execSQL(queryvaccinationtable);
        db.execSQL(queryeggtable);
        db.execSQL(querydiseasetable);
        db.execSQL(queryusertable);
        db.execSQL(queryuserdetailstable);

       // db.execSQL("create table " + TABLE_CHICKEN +" ("+CHICKEN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+CHICKEN_NAME+" TEXT,"+CHICKEN_NUMBER+" TEXT,"+CHICKEN_MODEACQ+"TEXT,"+CHICKEN_DATE+"TEXT,"+CHICKEN_NOTE+"TEXT,"+CHICKEN_BREAD+"TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_CHICKEN);
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_CHICK);
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_VACCINE);
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_VACCINERECORD);
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_EGGRECORD);
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_DISEASE);
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_USERDETAILS);
        onCreate(db);
    }

    // CREATE ACCOUNT
    public boolean createAccount(String email,String firstname,String othername,String phonenumber,String datecreated){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMAIL,email);
        contentValues.put(FIRST_NAME,firstname);
        contentValues.put(OTHER_NAME,othername);
        contentValues.put(PHONE,phonenumber);
        contentValues.put(DATE_CREATED,datecreated);



        long result = db.insert(TABLE_USERDETAILS,null, contentValues);

        if (result == -1){
            return false;
        }else{
            return true;
        }
    }
    public boolean createLoginAccount(String username,String password,String privillage){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME,username);
        contentValues.put(PASSWORD,password);
        contentValues.put(PRIVILEGE,privillage);

        long result = db.insert(TABLE_USERS,null, contentValues);

        if (result == -1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getAccountLogin(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor logins = db.rawQuery("select * from "+TABLE_USERS,null);
        return  logins;
    }
    // insert chicken
    public boolean insertChicken(String flockname,String flockbread,String flocknumber,String flockmodeacq,String flockdateacq,String flocknote){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CHICKEN_NAME,flockname);
        contentValues.put(CHICKEN_BREAD,flockbread);
        contentValues.put(CHICKEN_NUMBER,flocknumber);
        contentValues.put(CHICKEN_MODEACQ,flockmodeacq);
        contentValues.put(CHICKEN_DATE,flockdateacq);
        contentValues.put(CHICKEN_NOTE,flocknote);


        long result = db.insert(TABLE_CHICKEN,null, contentValues);

        if (result == -1){
            return false;
        }else{
            return true;
        }
    }
    // select chicken data
    public Cursor getAllChickenData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor chickenres = db.rawQuery("select * from "+TABLE_CHICKEN,null);
        return  chickenres;
    }

    // select chicken for sum
    public int ChickenSum(){
        SQLiteDatabase db = this.getReadableDatabase();
      int totalChicken;
      // query
        Cursor cursorTotal = db.rawQuery("select sum("+CHICKEN_NUMBER+")  from "+TABLE_CHICKEN,null);
        if(cursorTotal.moveToFirst()){
            totalChicken = cursorTotal.getInt(0);
        }else{
            totalChicken = 0;
        }
        cursorTotal.close();
        db.close();
        return totalChicken;
    }

    // select chicken for spinner
    public List<String> getChickenFlockList(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> chickenList = new ArrayList<String>();
        Cursor selectChickenList = db.rawQuery("select * from "+TABLE_CHICKEN,null);
        if(selectChickenList.moveToFirst()){
            do {
                chickenList.add(selectChickenList.getString(1));

            }while (selectChickenList.moveToNext());
        }
        selectChickenList.close();
        db.close();
        return chickenList;
    }
    // count chicken flock
    public long ChickenFlockCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        long ChickenCount = DatabaseUtils.queryNumEntries(db, TABLE_CHICKEN);
        db.close();
        return ChickenCount;
    }
   // delete chicken flock
    public void detelechickenflock(int chickenid){
        db = this.getWritableDatabase();
        db.delete(TABLE_CHICKEN,CHICKEN_ID + "=" + chickenid,null);
    }





    // insert chick
    public boolean insertChick(String chickname,String chickbread,String chicknumber,String chickmodeacq,String chickage,String chickdateacq,String chicknote){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CHICK_NAME,chickname);
        contentValues.put(CHICK_BREAD,chickbread);
        contentValues.put(CHICK_NUMBER,chicknumber);
        contentValues.put(CHICK_MODEACQ,chickmodeacq);
        contentValues.put(CHICK_AGE,chickage);
        contentValues.put(CHICK_DATE,chickdateacq);
        contentValues.put(CHICK_NOTE,chicknote);


        long chickResult = db.insert(TABLE_CHICK,null, contentValues);

        if (chickResult == -1){
            return false;
        }else{
            return true;
        }
    }
    // select chick data
    public Cursor getAllChickData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor chickres = db.rawQuery("select * from "+TABLE_CHICK,null);
        return  chickres;
    }
    // select chick for spinner
    public List<String> getChickFlockList(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> chickList = new ArrayList<String>();
        Cursor selectChickList = db.rawQuery("select * from "+TABLE_CHICK,null);
        if(selectChickList.moveToFirst()){
            do {
                chickList.add(selectChickList.getString(1));

            }while (selectChickList.moveToNext());
        }
        selectChickList.close();
        db.close();
        return chickList;
    }
    // select chick for sum
    public int ChickSum(){
        SQLiteDatabase db = this.getReadableDatabase();
        int totalChick;
        // query
        Cursor cursorTotal = db.rawQuery("select sum("+CHICK_NUMBER+")  from "+TABLE_CHICK,null);
        if(cursorTotal.moveToFirst()){
            totalChick = cursorTotal.getInt(0);
        }else{
            totalChick = 0;
        }
        cursorTotal.close();
        db.close();
        return totalChick;
    }
    // count chick flock
    public long ChickFlockCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        long ChickCount = DatabaseUtils.queryNumEntries(db, TABLE_CHICK);
        db.close();
        return ChickCount;
    }


    // insert eggs
    public boolean insertEggs(String flockname,String goodeggs,String badeggs,String collectiondate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EGG_FLOCK_ID_FK,flockname);
        contentValues.put(GOODEGGS,goodeggs);
        contentValues.put(BADEGGS,badeggs);
        contentValues.put(COLLECTION_DATE,collectiondate);



        long eggsResult = db.insert(TABLE_EGGRECORD,null, contentValues);

        if (eggsResult == -1){
            return false;
        }else{
            return true;
        }
    }
    // select EGG data
    public Cursor getAllEggsData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor eggsres = db.rawQuery("select * from "+TABLE_EGGRECORD,null);
        return  eggsres;
    }
    // select EGG for sum good only
    public int GoodEggSum(){
        SQLiteDatabase db = this.getReadableDatabase();
        int totalGoodEggs;
        // query
        Cursor cursorTotal = db.rawQuery("select sum("+GOODEGGS+")  from "+TABLE_EGGRECORD,null);
        if(cursorTotal.moveToFirst()){
            totalGoodEggs = cursorTotal.getInt(0);
        }else{
            totalGoodEggs = 0;
        }
        cursorTotal.close();
        db.close();
        return totalGoodEggs;
    }
    // select EGG for sum good only
    public int BadEggSum(){
        SQLiteDatabase db = this.getReadableDatabase();
        int totalBadEggs;
        // query
        Cursor cursorTotal = db.rawQuery("select sum("+BADEGGS+")  from "+TABLE_EGGRECORD,null);
        if(cursorTotal.moveToFirst()){
            totalBadEggs = cursorTotal.getInt(0);
        }else{
            totalBadEggs = 0;
        }
        cursorTotal.close();
        db.close();
        return totalBadEggs;
    }
    // count egg collection
    public long EggCollectionCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        long CollectionCount = DatabaseUtils.queryNumEntries(db, TABLE_EGGRECORD);
        db.close();
        return CollectionCount;
    }

    // vaccination

    // insert chick
    public boolean insertVaccineRecord(String flockname,String vaccinename,String vaccinationdate,String vaccinationnote){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VCC_FLOCK_ID_FK,flockname);
        contentValues.put(VACCINE_ID_FK,vaccinename);
        contentValues.put(VACCINATION_DATE,vaccinationdate);
        contentValues.put(VACCINATION_NOTE,vaccinationnote);



        long chickResult = db.insert(TABLE_VACCINERECORD,null, contentValues);

        if (chickResult == -1){
            return false;
        }else{
            return true;
        }
    }
    //select vaccine flock
    // select chicken data
    public Cursor getAllVaccineRecord(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor vaccineres = db.rawQuery("select * from "+TABLE_VACCINERECORD,null);
        return  vaccineres;
    }
}
