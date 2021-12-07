package com.example.smartpoultry;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Chick.class,Eggs.class,Chicken.class}, version = 3, exportSchema = false)
public abstract class ChickDatabase extends RoomDatabase {

    /** We make it static so that the class can be singleton,
     means we cannot make multiple instance of the class
     which means we use the same instance of the class everywhere
     **/
    private static ChickDatabase instance;

    /** this method has no method body
     this will be used to access the note_item DAO **/
    public abstract ChickDao chickDao();

    /** Synchronized means that a single thread at time can access the class **/
    public static synchronized ChickDatabase getInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ChickDatabase.class, "chick_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return  instance;
    }
}
