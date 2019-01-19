package com.example.sustainr;

import android.view.ViewOutlineProvider;

import java.util.List;

public class Volunteer {
    String name;
    String email;
    String password;
    int badges;

    public Volunteer(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
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

    public int getBadges() {
        return badges;
    }

    public void setBadges(int badges) {
        this.badges = badges;
    }
    public void giveReward(int amount){
        this.badges+=amount;
    }
}
