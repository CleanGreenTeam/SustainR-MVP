package com.example.sustainr;

import android.view.ViewOutlineProvider;

import java.util.List;

public class Volunteer {
    String name;
    String email;
    String password;
    String id;
    private int age;
    private int points;
    private String location;
    private List<String> interests;



    public Volunteer(String name, String email, String password, String id, int age, int points, String location, List<String> interests){
        this.name = name;
        this.email = email;
        this.password = password;
        this.id = id;
        this.points = points;
        this.age = age;
        this.location = location;
        this.interests = interests;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    public void giveReward(int amount){
        this.points+=amount;
    }
}
