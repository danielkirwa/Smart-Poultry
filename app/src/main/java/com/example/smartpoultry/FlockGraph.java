package com.example.smartpoultry;

public class FlockGraph {
    String flock;
    int flockNumbers;

    public FlockGraph(String flock, int flockNumbers) {
        this.flock = flock;
        this.flockNumbers = flockNumbers;
    }

    public String getFlock() {
        return flock;
    }

    public void setFlock(String flock) {
        this.flock = flock;
    }

    public int getFlockNumbers() {
        return flockNumbers;
    }

    public void setFlockNumbers(int flockNumbers) {
        this.flockNumbers = flockNumbers;
    }
}
