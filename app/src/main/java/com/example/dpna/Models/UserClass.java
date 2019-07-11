package com.example.dpna.Models;

public class UserClass {
    //peso esattura fecha nacimiento y kmrecorrido
    public int id;
    public String user;
    public String password;
    public double peso;
    public double estatura;
    public String token;
    public double km;

    public UserClass(){}

    public UserClass(int id, String user, String password, double peso, double estatura, String token, double km) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.peso = peso;
        this.estatura = estatura;
        this.token = token;
        this.km = km;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }
}
