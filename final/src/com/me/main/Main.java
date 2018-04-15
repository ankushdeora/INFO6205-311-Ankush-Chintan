package com.me.main;

import com.me.classes.City;
import com.me.genetic.GA;
import com.me.genetic.Population;
import com.me.genetic.RouteOrder;

import java.util.ArrayList;
import java.util.Arrays;
import org.apache.log4j.Logger;

public class Main {

    static Logger log = Logger.getLogger(Main.class.getName());

    public static ArrayList<City> cities = new ArrayList<>();
    public static RouteOrder bestRouteOrderRecorded;

    private static int populationSize = 20;
    private static int generations = 100;
    private static int numOfCities = 30;

    public static double bestGeneralFitness = Double.POSITIVE_INFINITY;

    public static int generationCount = 0;

    public Main(){
        generateCities();
    }

    public static void main(String[] args) {
        Main main = new Main();

        double bestDistance = 0.0;

        Population population = new Population(populationSize, numOfCities);
        population.normalizeFitness();
        population.sortRouteByFitness();
        bestRouteOrderRecorded = population.getBestRouteOrder();
        bestDistance = population.getRoutes().get(0).getDistance();
        bestGeneralFitness = population.getRoutes().get(0).getGeneralFitness();
        main.printPopulation(population);

        for(int j = 1;  j <= generations; j++) {
            GA geneticAlgo = new GA(cities);
            Population nextGen = geneticAlgo.nextGeneration(population);
            nextGen.normalizeFitness();
            nextGen.newPopulationFitness();
            nextGen.sortRouteByFitness();
            generationCount++;
//            main.printPopulation(nextGen);

            population = nextGen;
            if(bestDistance > nextGen.getRoutes().get(0).getDistance()){
                bestDistance = nextGen.getRoutes().get(0).getDistance();
                bestRouteOrderRecorded = nextGen.getRoutes().get(0);
                bestGeneralFitness = nextGen.getRoutes().get(0).getGeneralFitness();
            }
            System.out.println("");
            System.out.println("generation count: "+generationCount);
            System.out.println("bestDistance Recorded: "+bestDistance);
            System.out.println("best Route recorded: "+Arrays.toString(bestRouteOrderRecorded.getOrder()));
            System.out.println("final score: "+bestGeneralFitness);
            System.out.println("survivor count: "+GA.survivors_count);

            //log
            log.info("");
            log.info("generation count: "+generationCount);
            log.info("bestDistance Recorded: "+bestDistance);
            log.info("best Route recorded: "+Arrays.toString(bestRouteOrderRecorded.getOrder()));
            log.info("final score: "+bestGeneralFitness);
            log.info("survivor count: "+GA.survivors_count);
        }
//        System.out.println();
        log.info("");
    }


    //Printing on console
    public void printPopulation (Population population)
    {

        for(int i = 0; i < numOfCities/3; i++){
            System.out.print("\t");
        }
        System.out.print("Route");
        for(int i = 0; i < numOfCities/3; i++){
            System.out.print("\t");
        }
        System.out.print("\t| Normalized fitness|	Distance	|	General Fitness");
        System.out.println("");
        System.out.println("------------------------------------------------------------------------------------------");
        population.getRoutes().forEach(x -> {
            System.out.println(Arrays.toString(x.getOrder()) + "\t|\t"+
                    String.format("%.4f", x.getFitness()) + "\t\t\t|\t" + String.format("%.2f", x.calculateTotalDistance()) + " \t|\t" + String.format("%.5f", x.getGeneralFitness()));
        });
        System.out.println("");

        //log
        log.info("");
        log.info("generation count: "+generationCount);
        log.info("\t\t\tRoute\t\t\t| Normalized fitness|	Distance	|	General Fitness");
        log.info("");
        log.info("---------------------------------------------------------------------------");
        population.getRoutes().forEach(x -> {
            log.info(Arrays.toString(x.getOrder()) + "\t|\t"+
                    String.format("%.4f", x.getFitness()) + "\t\t\t|\t" + String.format("%.2f", x.calculateTotalDistance()) + " \t|\t" + String.format("%.5f", x.getGeneralFitness()));
        });

        log.info("");
    }

    //Generating initial cities
    private void generateCities(){

        cities = new ArrayList<City>(Arrays.asList(
                new City("A", 42.3601, -71.0589),
                new City("B", 29.7604, -95.3698),
                new City("C", 30.2672, -97.7431),
                new City("D", 37.7749, -122.4194),
                new City("E", 39.7392, -104.9903),
                new City("F", 34.0522, -118.2437),
                new City("G", 41.8781, -87.6298),
                new City("H", 40.7128, -74.0059),
                new City("I", 32.7767, -96.7970),
                new City("J", 47.6062, -122.3321),
                new City("K", 62.3601, -41.0589),
                new City("L", 89.7604, -75.3698),
                new City("M", 10.2672, -97.7431),
                new City("N", 37.7749, -12.4194),
                new City("O", 59.7392, -94.9903),
                new City("P", 14.0522, -108.2437),
                new City("Q", 21.8781, -37.6298),
                new City("R", 40.7128, -104.0059),
                new City("S", 82.7767, -26.7970),
                new City("T", 87.6062, -122.3321),
                new City("U", 32.3601, -101.0589),
                new City("V", 44.7604, -70.3698),
                new City("W", 30.2672, -60.7431),
                new City("X", 21.7749, -102.4194),
                new City("Y", 19.7392, -104.9903),
                new City("Z", 74.0522, -18.2437),
                new City("AB", 51.8781, -107.6298),
                new City("AC", 20.7128, -74.0059),
                new City("AD", 82.7767, -76.7970),
                new City("AE", 77.6062, -82.3321)
        ));

    }

}
