package com.example.smartpoultry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;
 private  static  final  String DATABASE_NAME = "smartpoultry.db";
    private  static  final  int DATABASE_VERSION = 1;
    private  static  final  String TABLE_CHICKEN = "tblchiecken";
    private  static  final  String TABLE_CHICK = "tblchick";

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

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     //String Query_Table = " CREATE TABLE " +TABLE_CHICKEN+ "(" +CHICKEN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +CHICKEN_NAME+ " TEXT, " +CHICKEN_BREAD+ " TEXT, " +CHICKEN_NUMBER+ " TEXT, " +CHICKEN_MODEACQ+ " TEXT, " +CHICKEN_DATE+ " TEXT, " +CHICKEN_NOTE+ " TEXT);";

     String query = "CREATE TABLE " + TABLE_CHICKEN + " ("
                + CHICKEN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CHICKEN_NAME + " TEXT,"
                + CHICKEN_BREAD + " TEXT,"
                + CHICKEN_NUMBER + " TEXT,"
                + CHICKEN_MODEACQ + " TEXT,"
                + CHICKEN_DATE + " TEXT,"
                + CHICKEN_NOTE + " TEXT)";


        String querychicktable = "CREATE TABLE " + TABLE_CHICK + " ("
                + CHICK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CHICK_NAME + " TEXT,"
                + CHICK_BREAD + " TEXT,"
                + CHICK_NUMBER + " TEXT,"
                + CHICK_MODEACQ + " TEXT,"
                + CHICK_AGE + " TEXT,"
                + CHICK_DATE + " TEXT,"
                + CHICK_NOTE + " TEXT)";



        db.execSQL(query);
        db.execSQL(querychicktable);


       // db.execSQL("create table " + TABLE_CHICKEN +" ("+CHICKEN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+CHICKEN_NAME+" TEXT,"+CHICKEN_NUMBER+" TEXT,"+CHICKEN_MODEACQ+"TEXT,"+CHICKEN_DATE+"TEXT,"+CHICKEN_NOTE+"TEXT,"+CHICKEN_BREAD+"TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_CHICKEN);
        onCreate(db);
    }

    // insert chicken
    public boolean inserflock(String flockname,String flockbread,String flocknumber,String flockmodeacq,String flockdateacq,String flocknote){
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
   // delete chicken flock
    public void detelechickenflock(int chickenid){
        db = this.getWritableDatabase();
        db.delete(TABLE_CHICKEN,CHICKEN_ID + "=" + chickenid,null);
    }





    // insert chicken
    public boolean inserchickflock(String chickname,String chickbread,String chicknumber,String chickmodeacq,String chickage,String chickdateacq,String chicknote){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CHICK_NAME,chickname);
        contentValues.put(CHICK_BREAD,chickbread);
        contentValues.put(CHICK_NUMBER,chicknumber);
        contentValues.put(CHICK_MODEACQ,chickmodeacq);
        contentValues.put(CHICK_AGE,chickage);
        contentValues.put(CHICK_DATE,chickdateacq);
        contentValues.put(CHICK_NOTE,chicknote);


        long chickresult = db.insert(TABLE_CHICK,null, contentValues);

        if (chickresult == -1){
            return false;
        }else{
            return true;
        }
    }

}
