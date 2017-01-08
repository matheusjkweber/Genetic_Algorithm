package Genetic_Algorithm;

import java.util.ArrayList;


public class Local_Population extends Population{
	private ArrayList<Chromosome> population;
	
	/**
	   * Constructor of global_population with value.
	   * @param size The size of the population.
	   * @param n_slave The number of threads that this population will use as slaves.
	   
	   */
	public Local_Population(int size) {
		super(size);
		this.population = new ArrayList<Chromosome>();
	}
	
	/**
	  * This method returns the population of this object.
	  * @return ArrayList<Chromosome> A array of chromosomes.
	  */
	
	public ArrayList<Chromosome> getPopulation() {
		return population;
	}
	
	/**
	  * This method set a new population for this object.
	  * @param ArrayList<Chromosome> The new list of chromosome.
	  */
	
	public void setPopulation(ArrayList<Chromosome> population) {
		this.population = population;
	}
	
	/**
	 * If first time, this method will randomly generate a new population for the Genetic Algorithm.
	 * If not first time, this method will also include the best parents from the previous generation.
	 */
	
	public void generatePopulation(){
		for(int i = 0; i < this.size; i++){
			population.add(this.create_chromosome());
			
		}
	}
	
}
