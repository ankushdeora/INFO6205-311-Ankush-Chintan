package com.me.classes;

public class City {
    private static final double earth_eq_radius = 6378.388D;
    private static final double deg_to_rad = Math.PI/180D;
    private static final double km_to_miles = 0.621371;
    private double longitude;
    private double latitude;
    private String name;

    public City(String name, double latitude, double longitute )
    {
        this.name = name;
        this.latitude =latitude*deg_to_rad;
        this.longitude = longitute*deg_to_rad;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public double measureDistance(City city)
    {

//
//        double deltaLatitude = city.getLatitude() - this.getLatitude();
//        double deltaLongitude = city.getLongitude() - this.getLongitude();
//        double betaLatitude = city.getLatitude() + this.getLatitude();
//        double q1 = Math.cos(deltaLongitude);
//        double q2 = Math.cos(deltaLatitude);
//        double q3 = Math.cos(betaLatitude);
//
//        double distance = earth_eq_radius * Math.acos(0.5*((1.0+q1)*q2 - (1.0-q1)*q3)) + 1.0;
//        return distance;


        double deltaLatitude = city.getLatitude() - this.getLatitude();
        double deltaLongitude = city.getLongitude() - this.getLongitude();
        double a = Math.pow(Math.sin(deltaLatitude/2D), 2D) +
                Math.cos(this.getLatitude())* Math.cos(city.getLatitude())* Math.pow(Math.sin(deltaLongitude/2D), 2D);

        double dist = km_to_miles*earth_eq_radius*2D*Math.atan2(Math.sqrt(a), Math.sqrt(1D-a));
        return dist;
    }



    public String toString(){
        return this.name;
    }
}
