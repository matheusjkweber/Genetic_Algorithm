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
	public Global_Population(int size, int n_slaves) {
		super(size);
		this.population = new ArrayList<Chromosome>();
		this.n_slaves = n_slaves;
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
	
	public void generatePopulation(){
		for(int i = 0; i < this.size; i++){
			population.add(this.create_chromosome());
		}
	}
	
	/**
	 * This method will select the best parents from this population based on a specific method and will save on parents.
	 * @param type The selection type.
	 * @param selectedChromosomes The number of chromosomes that it have to store.
	 */
	
	public void selectParents(SelectionType type, int selectedChromosomes){
		if(type == SelectionType.ARM){
			this.parents = rouletteSelection(population, selectedChromosomes);
			System.out.println(this.parents);
		}else if(type == SelectionType.RM){
			this.parents = rankingSelection(population, selectedChromosomes);
		}else if(type == SelectionType.TM){
			this.parents = tournamentSelection(population, selectedChromosomes);
		}else if(type == SelectionType.USSM){
			
		}
	}
}
