package com.Corona.CoronaTrack.models;

public class Stats2 {

    private String TotalAmountOfCases;
    private String TotalAmountOfDeaths;

    @Override
    public String toString() {
        return "Stats2{" +
                "TotalAmountOfCases='" + TotalAmountOfCases + '\'' +
                ", TotalAmountOfDeaths='" + TotalAmountOfDeaths + '\'' +
                '}';
    }

    public String getTotalAmountOfCases() {
        return TotalAmountOfCases;
    }

    public void setTotalAmountOfCases(String totalAmountOfCases) {
        TotalAmountOfCases = totalAmountOfCases;
    }

    public String getTotalAmountOfDeaths() {
        return TotalAmountOfDeaths;
    }

    public void setTotalAmountOfDeaths(String totalAmountOfDeaths) {
        TotalAmountOfDeaths = totalAmountOfDeaths;
    }
}
