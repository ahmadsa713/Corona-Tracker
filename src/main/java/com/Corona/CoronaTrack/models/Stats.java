package com.Corona.CoronaTrack.models;

public class Stats {
    private String date;
    private String state;
    private String cases;
    private String deaths;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }


  /* @Override
    public String toString() {
        return "Stats{" +
                "date='" + date + '\'' +
                ", state='" + state + '\'' +
                ", cases='" + cases + '\'' +
                ", deaths='" + deaths + '\'' +
                '}';
    }*/
}
