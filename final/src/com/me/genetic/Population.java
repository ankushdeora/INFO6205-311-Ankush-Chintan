package com.me.genetic;

import com.me.sort.PrioritySort;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Population {

    ArrayList<RouteOrder> routes = new ArrayList<>();
    int populationSize;
    RouteOrder bestRouteOrder;
    public Population(int totalPopulation, int numOfCities) {

        this.populationSize = totalPopulation;
        IntStream.range(0, totalPopulation).forEach(x -> routes.add(new RouteOrder(numOfCities)));
    }

    public Population(int totalPopulation){
        this.populationSize = totalPopulation;
    }


    public RouteOrder getBestRouteOrder() {
        return bestRouteOrder;
    }

    public void setBestRouteOrder(RouteOrder bestRouteOrder) {
        this.bestRouteOrder = bestRouteOrder;
    }

    public ArrayList<RouteOrder> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<RouteOrder> routes) {
        this.populationSize = routes.size();
        this.routes = routes;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void sortRouteByFitness(){
        PrioritySort<RouteOrder> pq = new PrioritySort<>(routes.size());
        for(int i = 0; i < routes.size(); i++) {
            pq.insert(routes.get(i));
        }
        routes = new ArrayList<>();
        while(!pq.isEmpty()){
            routes.add(pq.delMax());
        }
    }

    public void normalizeFitness(){
        double sum = 0;
        for(int i = 0; i < routes.size(); i++){
            sum += routes.get(i).getFitness();
        }

        for(int i = 0; i < routes.size(); i++){
            double fitness = routes.get(i).getFitness();
            routes.get(i).setFitness((fitness/sum));
        }
        this.setBestRouteOrder(routes.get(0));
    }

    public void newPopulationFitness(){
        for(int i = 0; i < this.routes.size(); i++) {
            this.routes.get(i).calculateFitness();
        }
        normalizeFitness();
    }
}
