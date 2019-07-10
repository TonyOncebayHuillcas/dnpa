package com.example.dpna.Models;

import java.util.ArrayList;

public class RouteClass {
    public int id;
    public int distancia;
    public int velocidad;
    public int ganancia;
    //public ArrayList<DetailRouteClass> detailRouteClasses;
    //for json path, root ("amigos")

    public RouteClass(int id, int distancia, int velocidad, int ganancia) {
        this.id = id;
        this.distancia = distancia;
        this.velocidad = velocidad;
        this.ganancia = ganancia;
        //this.detailRouteClasses = detailRouteClasses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getGanancia() {
        return ganancia;
    }

    public void setGanancia(int ganancia) {
        this.ganancia = ganancia;
    }

}
