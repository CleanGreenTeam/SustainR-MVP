package com.example.sustainr;

import java.util.List;

public class Coordinator {
    String name;
    String email;
    String password;
    List<String> eventIds;

    public Coordinator(String name, String email, String password) {
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

    public List<String> getEventIds() {
        return eventIds;
    }

    public void addEvent(String event){
        this.eventIds.add(event);
    }
}
