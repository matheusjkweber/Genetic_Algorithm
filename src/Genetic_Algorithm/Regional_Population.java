package Genetic_Algorithm;

import java.util.ArrayList;


public class Regional_Population extends Population{
	private ArrayList<ArrayList<Chromosome>> population;
	private int n_generations;
	private double migration_rate;
	private double migration_chance;
	private int number_of_populations;
	
	/**
	   * Constructor of global_population with value.
	   * @param size The size of the population.
	   * @param n_slave The n_generations that this model is.
	   * @param d The migration rate of this population.
	   * @param e The chance that migration will happen by generation.
	   * @param number_of_populations The number of subpopulations that this model will use.
	   */
	public Regional_Population(int size, int n_gerenations, double d, double e, int number_of_populations) {
		super(size);
		this.n_generations = n_gerenations;
		this.migration_rate = d;
		this.number_of_populations = number_of_populations;
		this.migration_chance = e;
		this.population = new ArrayList<ArrayList<Chromosome>>();
	}

	/**
	  * This method returns the population of this object.
	  * @return ArrayList<Chromosome> A array of chromosomes.
	  */
	
	/*public ArrayList<ArrayList<Chromosome>> getPopulation() {
		return population;
	}*/
	
	/**
	  * This method set a new population for this object.
	  * @param ArrayList<Chromosome> The new list of chromosome.
	  */
	
	public void setPopulation(ArrayList<ArrayList<Chromosome>> population) {
		this.population = population;
	}
	
	/**
	  * This method returns the n_generations of this population.
	  * @return Int.
	  */
	
	public int getN_generations() {
		return n_generations;
	}
	
	/**
	  * This method set a new value for n_generations.
	  * @param Int
	  */
	
	public void setN_generations(int n_generations) {
		this.n_generations = n_generations;
	}
	
	/**
	  * This method returns the migration_rate of this population.
	  * @return double.
	  */
	
	public double getMigration_rate() {
		return migration_rate;
	}
	
	/**
	  * This method set a new value for migration_rate.
	  * @param migration rate
	  */
	
	public void setMigration_rate(double migration_rate) {
		this.migration_rate = migration_rate;
	}
	
	/**
	  * This method returns the number_of_populations of this population.
	  * @return Int.
	  */
	
	public int getNumber_of_populations() {
		return number_of_populations;
	}

	/**
	  * This method set a new value for number_of_populations.
	  * @param number_of_populations
	  */
	
	public void setNumber_of_populations(int number_of_populations) {
		this.number_of_populations = number_of_populations;
	}
	
	/**
	  * This method returns the migration_chance of this population.
	  * @return double.
	  */
	
	public double getMigration_chance() {
		return migration_chance;
	}

	/**
	  * This method set a new value for migration_chance.
	  * @param migration_chance
	  */
	
	public void setMigration_chance(double migration_chance) {
		this.migration_chance = migration_chance;
	}
	
	/** 
	 * This method will raffle if migrate or not based on migration_chance, if yes will randomly choose chromosomes(based on migration_rate)
	 * from a randomly subpopulation and migrate to other subpopulation defined by random.
	 */
	
	public void migrate(){
		
	}
	
	/**
	  * This method returns a String describing the population.
	  * @return String The description of this population.
	  */
	
	@Override
	public String toString() {
		return "Global_Population [n_generations=" + n_generations + "]";
	}

	/**
	 * If first time, this method will randomly generate a new population for the Genetic Algorithm.
	 * If not first time, this method will also include the best parents from the previous generation.
	 */
	
	public void generatePopulation(){
		for(int j = 0; j < this.number_of_populations; j++){
			ArrayList<Chromosome> subpopulation = new ArrayList<Chromosome>();
			for(int i = 0; i < (int) this.size / this.number_of_populations; i++){
				subpopulation.add(this.create_chromosome());
			}
			
			population.add(subpopulation);
		}
				
	}
}
