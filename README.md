# INFO6205-311 - Travelling Salesman Problem (TSP)

#Problem : 
Given a set of cities and distance between every pair of cities, the problem is to find the shortest possible route that visits every city exactly once and returns to the starting point.

#Overview Any genetic algorithm (GA) is made up of the following components and our approach to the TSP using a GA is no different:
Selection
Reproduction (genetic crossover)
Mutation

In our code each gene is represented as a City object and each Route object is a collection of 30 Cities. The fitness of each Route is evaluated according to it's distance. Each Population is made up of a number of Routes. At each generation, the Population is repopulated with the fittest route having a higher probability of being selected for reproduction. To keep the mating pool from becoming stagnant, a mutation (which may or may not improve the Path fitness) is applied at random. This child Path is then a member of the Population for the next generation.

Detail description is provided in TSP_Final_report File with some observations

We have used :
1. Java 1.8.
2. Log4j - to log all the events/output.
3. Junit for unit testing.

#run file :  Main.java
#output file : Logfile.log
#Final Report - TSP_Final_report

#Team Members : 
1. Ankush Deora Moolchand
2. Chintan Shah
