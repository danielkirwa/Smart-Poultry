package com.example.smartpoultry;

public class FlockNumbersGraph {
    String flockNames;
    int flockNumberCount;

    public FlockNumbersGraph(String flockNames, int flockNumberCount) {
        this.flockNames = flockNames;
        this.flockNumberCount = flockNumberCount;
    }

    public String getFlockNames() {
        return flockNames;
    }

    public void setFlockNames(String flockNames) {
        this.flockNames = flockNames;
    }

    public int getFlockNumberCount() {
        return flockNumberCount;
    }

    public void setFlockNumberCount(int flockNumberCount) {
        this.flockNumberCount = flockNumberCount;
    }
}
