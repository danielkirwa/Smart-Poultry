package com.example.smartpoultry;

public class EggGraph {
    String pieEggName;
    int pieEggNumber;

    public EggGraph(String pieEggName, int pieEggNumber) {
        this.pieEggName = pieEggName;
        this.pieEggNumber = pieEggNumber;
    }

    public String getPieEggName() {
        return pieEggName;
    }

    public void setPieEggName(String pieEggName) {
        this.pieEggName = pieEggName;
    }

    public int getPieEggNumber() {
        return pieEggNumber;
    }

    public void setPieEggNumber(int pieEggNumber) {
        this.pieEggNumber = pieEggNumber;
    }
}
