package com.example.smartpoultry;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "chick_table")

public class Chick {

    @PrimaryKey(autoGenerate = true)
    int id;
    String flockName;
    String flockBreed;
    String flockNumber;
    String modeOfAcquision;
    String flockAge;
    String dateOfAcquision;
    String note;



    public Chick(int id, String flockName, String flockBreed, String flockNumber, String modeOfAcquision, String flockAge, String dateOfAcquision, String note) {
        this.id = id;
        this.flockName = flockName;
        this.flockBreed = flockBreed;
        this.flockNumber = flockNumber;
        this.modeOfAcquision = modeOfAcquision;
        this.flockAge = flockAge;
        this.dateOfAcquision = dateOfAcquision;
        this.note = note;
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

    public String getFlockBreed() {
        return flockBreed;
    }

    public void setFlockBreed(String flockBreed) {
        this.flockBreed = flockBreed;
    }

    public String getFlockNumber() {
        return flockNumber;
    }

    public void setFlockNumber(String flockNumber) {
        this.flockNumber = flockNumber;
    }

    public String getModeOfAcquision() {
        return modeOfAcquision;
    }

    public void setModeOfAcquision(String modeOfAcquision) {
        this.modeOfAcquision = modeOfAcquision;
    }

    public String getFlockAge() {
        return flockAge;
    }

    public void setFlockAge(String flockAge) {
        this.flockAge = flockAge;
    }

    public String getDateOfAcquision() {
        return dateOfAcquision;
    }

    public void setDateOfAcquision(String dateOfAcquision) {
        this.dateOfAcquision = dateOfAcquision;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}



