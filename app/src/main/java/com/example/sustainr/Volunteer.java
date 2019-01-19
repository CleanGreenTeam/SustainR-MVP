package com.example.sustainr;



public class Volunteer {
    private String name;
    private String location;
    private int points;

    public Volunteer(String name, String location, int points) {
        this.name = name;
        this.location = location;
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int amnt) {
        points += amnt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }


}