package com.me.genetic;

import com.me.main.Main;

import java.util.Random;

public class RouteOrder implements Comparable<RouteOrder>{

    private int[] order;
    private double fitness;
    private double distance;
    private double generalFitness;

    private static Random random = new Random();

    public RouteOrder(int numOfOrders) {
        this.order = new int[numOfOrders];
        for(int i = 0 ; i < order.length; i++){
            order[i] =  i;
        }
        shuffle(order, 100);

        fitness = 1/calculateTotalDistance();
        generalFitness = fitness*10000;
    }

    public RouteOrder(){
//        this.order = order.clone();
//
//        fitness = 1 / calculateTotalDistance();
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int[] getOrder() {
        return order;
    }

    public void setOrder(int[] order) {
        this.order = order;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getGeneralFitness() {
        return generalFitness;
    }

    public void setGeneralFitness(double generalFitness) {
        this.generalFitness = generalFitness;
    }

    public void calculateFitness(){
        this.fitness = 1/calculateTotalDistance();
        this.generalFitness = this.fitness*10000;
    }

    //Total Distance for this order
    public double calculateTotalDistance(){
        int citiesSize = this.order.length;
        double returnValue = 0;
        for (int cityIndex = 0; cityIndex + 1 < citiesSize; cityIndex++) {
            returnValue += Main.cities.get(this.order[cityIndex]).measureDistance(Main.cities.get(this.order[cityIndex + 1]));
        }
        returnValue += Main.cities.get(this.order[citiesSize - 1]).measureDistance(Main.cities.get(this.order[0]));
        this.distance = returnValue;
        return returnValue;
    }


    //shuffle array
    public void shuffle(int[] order, int numOfTimes){
        for(int i = 0; i < numOfTimes; i++){
            int indexA = random.nextInt(order.length);
            int indexB = random.nextInt(order.length);
            swap(order, indexA, indexB);
        }
    }

    public void swap(int[] a, int i , int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    @Override
    public int compareTo(RouteOrder o) {
        if(this.getFitness() > o.getFitness()){
            return 1;
        }
        else if(this.getFitness() < o.getFitness()){
            return -1;
        }
        return 0;
    }

}
