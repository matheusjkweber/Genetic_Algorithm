package Genetic_Algorithm;

import java.util.ArrayList;


public class Global_Population extends Population{
	private ArrayList<Chromosome> population;
	private int n_slaves;
	
	/**
	   * Constructor of global_population with value.
	   * @param size The size of the population.
	   * @param n_slave The number of threads that this population will use as slaves.
	   
	   */
	public Global_Population(int size, int n_slaves, int elitism_rate, Parameter parameters) {
		super(parameters);
		this.population = new ArrayList<Chromosome>();
		this.n_slaves = n_slaves;
	}
	
	/**
	  * This method returns the population of this object.
	  * @return ArrayList<Chromosome> A array of chromosomes.
	  */
	
	@Override
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
	  * This method returns the n_slaves of this population.
	  * @return Int.
	  */
	
	public int getN_slaves() {
		return n_slaves;
	}
	
	/**
	  * This method set a new value for n_slaves.
	  * @param Int
	  */
	
	public void setN_slaves(int n_slaves) {
		this.n_slaves = n_slaves;
	}
	
	/**
	  * This method returns a String describing the population.
	  * @return String The description of this population.
	  */
	
	@Override
	public String toString() {
		return "Global_Population [n_slaves=" + n_slaves + "]";
	}
	
	/**
	 * If first time, this method will randomly generate a new population for the Genetic Algorithm.
	 * If not first time, this method will also include the best parents from the previous generation.
	 */
	
	@Override
	public void generatePopulation(boolean first_time){
		/*if(first_time == true){
			for(int i = 0; i < this.size; i++){
				population.add(this.create_chromosome());
			}
		}else{
			// Regenerate the population.
			// Apply elitism.
			float proportion = ((float) elitism_rate) / ((float) size);
			proportion = proportion * 100;
			
			selectParents(population, SelectionType.RM, (int) proportion);
			
			ArrayList<Chromosome> new_population = new ArrayList<Chromosome>();
			new_population = this.parents;
			
			// Apply crossover and get the rest applying mutation if need.
			selectParents(population, parameters.getSelection(), size - (int) proportion);
			
			for(int i = 0; i < parents.size() - 1; i = i+2){
				Chromosome c1 = parents.get(i);
				Chromosome c2 = parents.get(i+1);
				
				ArrayList<Chromosome> c3 = crossover(c1, c2, parameters.getCrossoverType());
				
				new_population.add(c3.get(0));
				new_population.add(c3.get(1));
			}
			
			population = new_population;
		}*/
			
	}
}
