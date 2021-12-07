package com.example.smartpoultry;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "egg_table")

public class Eggs {

    @PrimaryKey(autoGenerate = true)
    int id;
    String flockName;
    int numberOfEggs;
    String numberOfbadEggs;
    String dateCollected;





    public Eggs(int id, String flockName, int numberOfEggs, String numberOfbadEggs, String dateCollected) {
        this.id = id;
        this.flockName = flockName;
        this.numberOfEggs = numberOfEggs;
        this.numberOfbadEggs = numberOfbadEggs;
        this.dateCollected = dateCollected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlockName() {
        return flockName;
    }

    public void setFlockName(String flockName) {
        this.flockName = flockName;
    }

    public int getNumberOfEggs() {
        return numberOfEggs;
    }

    public void setNumberOfEggs(int numberOfEggs) {
        this.numberOfEggs = numberOfEggs;
    }

    public String getNumberOfbadEggs() {
        return numberOfbadEggs;
    }

    public void setNumberOfbadEggs(String numberOfbadEggs) {
        this.numberOfbadEggs = numberOfbadEggs;
    }

    public String getDateCollected() {
        return dateCollected;
    }

    public void setDateCollected(String dateCollected) {
        this.dateCollected = dateCollected;
    }
}



