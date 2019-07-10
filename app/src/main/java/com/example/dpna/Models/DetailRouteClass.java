package com.example.dpna.Models;

public class DetailRouteClass {
    public int id;
    public int idRoute;
    public String latitud;
    public String longitud;
    public double orientation;

    public DetailRouteClass(int id, int idRoute, String latitud, String longitud, double orientation) {
        this.id = id;
        this.idRoute = idRoute;
        this.latitud = latitud;
        this.longitud = longitud;
        this.orientation = orientation;
    }

    public DetailRouteClass(){ }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public double getOrientation() {
        return orientation;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }
}
