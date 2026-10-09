package com.example.coen390_assignment1;

public class Settings {
    private String counter1Name;
    private String counter2Name;
    private String counter3Name;
    private int maximumCounts;

    public Settings(String counter1Name, String counter2Name, String counter3Name, int maximumCounts) {

        this.counter1Name = counter1Name;
        this.counter2Name = counter2Name;
        this.counter3Name = counter3Name;
        this.maximumCounts = maximumCounts;
    }
    // getter functions
    public String getCounter1Name() {
        return counter1Name;
    }

    public String getCounter2Name() {
        return counter2Name;
    }

    public String getCounter3Name() {
        return counter3Name;
    }

    public int getMaximumCounts() {
        return maximumCounts;
    }





}

