package com.me.test;

import com.me.classes.City;
import com.me.genetic.Population;
import com.me.main.Main;
import com.me.sort.PrioritySort;

import static org.junit.Assert.*;

public class Test {
	Main main = new Main();            //generating cities

	//Testing sort by fitness function
	@org.junit.Test
	public void test1() {
		Population population = new Population(4,4);
		population.normalizeFitness();
		population.sortRouteByFitness();
		double fitness1 = population.getRoutes().get(0).getFitness();
		double fitness2 = population.getRoutes().get(1).getFitness();
		assertTrue(fitness1 >= fitness2);
	}


	//Testing measure distance function
	@org.junit.Test
	public void test2(){
		City city1 = main.cities.get(0);
		City city2 = main.cities.get(1);

		assertEquals(1605, (int)city1.measureDistance(city2));

	}


	//Testing priority queue sorting function
	@org.junit.Test
	public void test3(){
		PrioritySort<String> ps = new PrioritySort<>(5);
		ps.insert("b");
		ps.insert("a");
		ps.insert("d");
		ps.insert("e");
		ps.insert("c");
		assertEquals(5, ps.size());
		assertEquals("e", ps.delMax());
		assertEquals("d", ps.delMax());
		assertEquals("c", ps.delMax());
		assertEquals("b", ps.delMax());
		assertEquals("a", ps.delMax());
		assertEquals(0, ps.size());
	}

	//Testing normalize fitness function
	@org.junit.Test
	public void test4()
	{
		Population population = new Population(3, 3);
		double sum = population.getRoutes().get(0).getFitness() + population.getRoutes().get(1).getFitness() +population.getRoutes().get(2).getFitness();
		double f1 = population.getRoutes().get(0).getFitness() / sum;
		population.normalizeFitness();

		//assertEquals(f1, population.getRoutes().get(0).getFitness());
		assertEquals(0, Double.compare(f1, population.getRoutes().get(0).getFitness()));

	}

}
