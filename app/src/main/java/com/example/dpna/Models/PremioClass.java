package com.example.dpna.Models;

import android.media.Image;

public class PremioClass {
    public int id;
    public String address;
    public String description;
    public int value_km;

    public PremioClass(){}

    public PremioClass(int id, String address, String description, int value_km) {
        this.id = id;
        this.address = address;
        this.description = description;
        this.value_km = value_km;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getValue_km() {
        return value_km;
    }

    public void setValue_km(int value_km) {
        this.value_km = value_km;
    }
}
