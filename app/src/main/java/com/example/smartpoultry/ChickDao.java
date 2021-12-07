package com.example.smartpoultry;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ChickDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertChick(Chick chick);

    @Query("SELECT * FROM chick_table ORDER BY id ASC")
    List<Chick> getAllChicks();


    // egg methods
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEggg(Eggs eggs);

    @Query("SELECT * FROM egg_table ORDER BY id ASC")
    List<Eggs> getAllEggs();


    // chicken methods
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertChicken(Chicken chicken);

    @Query("SELECT * FROM chicken_table ORDER BY id ASC")
    List<Chicken> getAllChicken();


}
