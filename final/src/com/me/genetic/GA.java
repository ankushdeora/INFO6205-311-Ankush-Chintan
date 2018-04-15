package com.me.genetic;

import java.util.ArrayList;
import java.util.Random;
import com.me.classes.City;

public class GA {
    public static final float mutation_rate = 0.35f;
    public static int survivors_count = 0;
    //Random
    Random rand = new Random();

    private ArrayList<City> initialRoute;

    public GA(ArrayList<City> initialRoute) {
        this.initialRoute = initialRoute;
    }

    public ArrayList<City> getInitialRoute() {
        return initialRoute;
    }

    //Evolution
    public Population nextGeneration(Population population){
        Population newPopulation = new Population(population.getPopulationSize());
        ArrayList<RouteOrder> ro = new ArrayList<>();
        int randomEliteNum = 1 + (rand.nextInt(4-1)+1);
        //System.out.println("randElite: "+randomEliteNum);

        for(int i = 0; i < randomEliteNum; i++){
            RouteOrder order = population.getRoutes().get(i);
            if(!ro.contains(order)) {
                ro.add(order);
            }
        }
        for(int i = 0; i < newPopulation.getPopulationSize(); i++){
            RouteOrder orderA = pickBestOne(population);
            RouteOrder orderB = pickBestOne(population);
            RouteOrder order = crossOver(orderA, orderB);
            ro.add(order);
        }
        this.survivors_count = ro.size();
        newPopulation.setRoutes(ro);
        return newPopulation;
    }


    private RouteOrder crossOver(RouteOrder orderA, RouteOrder orderB) {
        int start = rand.nextInt(orderA.getOrder().length);
        int end = rand.nextInt(orderA.getOrder().length-start) + start;
         ArrayList<Integer> newOrder = new ArrayList<Integer>();
        for(int i = start; i <= end; i++){
            newOrder.add(orderA.getOrder()[i]);
        }

        for(int i = 0; i < orderB.getOrder().length; i++){
            int cityNum = orderB.getOrder()[i];
            if(!newOrder.contains(cityNum)){
                newOrder.add(cityNum);
            }
        }

        RouteOrder newRouteOrder = new RouteOrder();
        int[] a = newOrder.stream().mapToInt(Integer::intValue).toArray();
        mutate(a, mutation_rate);
        newRouteOrder.setOrder(a);
        newRouteOrder.calculateFitness();
        return newRouteOrder;
    }

    //Pick a gene according to it's normalized fitness
    public RouteOrder pickBestOne(Population population){
        int index = 0;

        float r = rand.nextFloat();

        while(r >= 0){
            r = (float) (r - population.getRoutes().get(index).getFitness());
            index++;
        }
        index--;
        return population.getRoutes().get(index);
    }


    //Mutation function
    public void mutate(int[] a, float mutationRate){
        for(int i = 0; i < a.length; i++) {
            float r = rand.nextFloat();
            if(r < mutationRate) {
                int indexA = rand.nextInt(a.length);
                int indexB = rand.nextInt(a.length);
                swap(a, indexA, indexB);
            }
        }
    }

    public void swap(int[] a, int i , int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
