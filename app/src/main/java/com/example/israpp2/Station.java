package com.example.israpp2;

public class Station {
    private String name;
    private int time;
    private double quality;
    private String description;
    private String user;
    private String location;
    public Station(){
        location = "";
        time = 0;
        name = "";
        quality = 0.0;
        description = "";
        user = "";
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getUser() {
        return user;
    }

    public String getDescription() {
        return description;
    }
    public int getTime() {
        return time;
    }

    public double getQuality() {
        return quality;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }
    public void setTime(int time) {
        this.time = time;
    }
    public Station getStation() {
        return this;
    }
    @Override
    public String toString() {
        return "Location=" + location + "name=" + name + " Time=" + time + " quality=" + quality + " description=" + description;
    }
}

