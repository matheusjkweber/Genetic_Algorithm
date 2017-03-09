package Genetic_Algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.*;

/**
* The Population class implements a generic population, populate, selection and crossover.
*
* @author  Matheus Jose Krumenauer Weber
* @version 1.0
* @since   2017-01-07
*/

public abstract class Population {
	protected int size;
	protected float bigger_fitness;
	protected ArrayList<Chromosome> parents;
	protected int elitism_rate;
	protected Parameter parameters;
	protected Chromosome last_selected;
	protected int maximum_iterations;
	//TODO: Create fixed values.
	
	/**
	   * Constructor of population with value.
	   * @param size The size of this population.
	   */
	
	public Population(Parameter parameters) {
		super();
		this.size = parameters.getSizeOfPopulation();
		this.elitism_rate = parameters.getElitismRate();
		this.parents = new ArrayList<Chromosome>();
		this.parameters = parameters;
		this.bigger_fitness = 0;
		this.maximum_iterations = parameters.getMaximumIterations();
	}
	
	/**
	  * This method returns a int.
	  * @return Int The size of the population.
	  */
	
	public int getSize() {
		return size;
	}
	
	/**
	  * This method set a new size for the population.
	  * @param Int The new size of this population.
	  */
	
	public void setSize(int size) {
		this.size = size;
	}
	
	/**
	  * This method returns the selected parents for the next generation.
	  * @return List<Chromosome> A list of chromosomes.
	  */
	
	public ArrayList<Chromosome> getParents() {
		return parents;
	}
	
	/**
	  * This method set new parents for the next generationl.
	  * @param List<Chromosome> A list of chromosomes.
	  */
	
	public void setParents(ArrayList<Chromosome> parents) {
		this.parents = parents;
	}
	
	/**
	  * This method returns the elitism rate.
	  * @return int.
	  */
	public int getElitism_rate() {
		return elitism_rate;
	}
	
	/**
	  * This method set a new elitism rate.
	  * @param int Elitism rate.
	  */
	public void setElitism_rate(int elitism_rate) {
		this.elitism_rate = elitism_rate;
	}
	
	/**
	  * This method returns the last selected chromosome.
	  * @return Chromosome.
	  */
	public Chromosome getLast_selected() {
		return last_selected;
	}
	
	/**
	  * This method set a new last selected chromosome.
	  * @param Chromosome.
	  */
	
	public void setLast_selected(Chromosome last_selected) {
		this.last_selected = last_selected;
	}
	
	/**
	  * This method returns the parameters of the population.
	  * @return Parameter.
	  */
	
	public Parameter getParameters() {
		return parameters;
	}
	
	/**
	  * This method set a new parameter for the population.
	  * @param Parameter.
	  */

	public void setParameters(Parameter parameters) {
		this.parameters = parameters;
	}
	
	/**
	  * This method returns the bigger fitness of the population.
	  * @return float.
	  */
	
	public float getBigger_fitness() {
		return bigger_fitness;
	}

	/**
	  * This method set a new bigger fitness for the population.
	  * @param float.
	  */
	
	public void setBigger_fitness(float bigger_fitness) {
		this.bigger_fitness = bigger_fitness;
	}

	/**
	  * This method returns the last selected chromosome.
	  * @return Chromosome.
	  */
	
	public int getMaximum_iterations() {
		return maximum_iterations;
	}

	
	/**
	  * This method set a new maximum_iterations for the population.
	  * @param int.
	  */
	
	public void setMaximum_iterations(int maximum_iterations) {
		this.maximum_iterations = maximum_iterations;
	}
	
	/**
	 * If first time, this method will randomly generate a new population for the Genetic Algorithm.
	 * If not first time, this method will also include the best parents from the previous generation.
	 * Generic method, used just to be override.
	 * @boolean first_time, true if first, false if not.
	 */
	
	public void generatePopulation(boolean first_time){
		
	}
	
	/**
	 * This method will select the best parents from this population based on a specific method and will save on parents.
	 * @param population The population that will be its parents selected.
	 * @param type The selection type.
	 * @param selectedChromosomes The number of chromosomes that it have to store.
	 */
	
	public void selectParents(ArrayList<Chromosome> population, SelectionType type, int selectedChromosomes){
		if(type == SelectionType.ARM){
			this.parents = rouletteSelection(population, selectedChromosomes);
		}else if(type == SelectionType.RM){
			this.parents = rankingSelection(population, selectedChromosomes);
		}else if(type == SelectionType.TM){
			this.parents = tournamentSelection(population, selectedChromosomes);
		}else if(type == SelectionType.USSM){
			
		}
	}
	
	/**
	 * This method will perform a crossover based on a specific method. 
	 * Generic method, used just to be override.
	 * @param CrossoverType.
	 * @param ArrayList<Chromosome>
	 */
	
	public Chromosome crossover(CrossoverType type, ArrayList<Chromosome> chromosomes){
		
		return null;
	}

	/**
	 * Create a random chromosome, with hard coded min and max setted to Kopp work.
	 * TODO: Put fixed value.
	 */
	
	public Chromosome create_chromosome(){
		Random random = new Random();		
		
		Gene d = new Gene("Diameter", random.nextInt(2000) + 100, true, MutationType.SBS, 100, 2000, 0);
		Gene hg = new Gene("Hg", random.nextInt(25) + 1, true, MutationType.SBSD3, 1, 25, 0);
		Gene l = new Gene("Lenght", random.nextInt(1000) + 1, true, MutationType.SBS, 1, 1000, 0);
		Gene q = new Gene("Flow", random.nextInt(2000) + 50, true, MutationType.SBS, 50, 2000, 0);
		
		ArrayList<Gene> genes = new ArrayList<Gene>();
		genes.add(d);
		genes.add(hg);
		genes.add(l);
		genes.add(q);
		
		Chromosome chromosome = new Chromosome(genes);
				
		return chromosome;
	}
	
	/**
	 * This method order the chromosomes and return the bests.
	 * @param chromosomes The list that will get the parents.
	 * @param number The number of parents that is needed.
	 * @return ArrayList<Chromosome> The selected parents.
	 */
	
	public ArrayList<Chromosome> rankingSelection(ArrayList<Chromosome> chromosomes, int number){
		ArrayList<Chromosome> parents = new ArrayList<Chromosome>();
		
		boolean flag = true;
		while(flag){
			flag = false;
			for(int i = 0; i < chromosomes.size() - 1; i++){
				if(chromosomes.get(i).getFitness() > chromosomes.get(i+1).getFitness()){
					Chromosome aux = chromosomes.get(i);
					chromosomes.set(i, chromosomes.get(i+1));
					chromosomes.set(i+1, aux);
					flag = true;
				}
			}
		}
		
		
		if(number >= chromosomes.size()){
			number = chromosomes.size() - 1;
		}
		
		for(int i = 0; i < number; i++){
			parents.add(chromosomes.get(i));
		}
		
		return parents;
	}
	
	
	/**
	 * This method select the parents using the tournament model, 
	 * it consists in divide the chromosomes in series and make 
	 * them compete for the right to be one of the bests.
	 * @param chromosomes The list that will get the parents.
	 * @param number The number of parents that is needed.
	 * @param ArrayList<Chromosome> The selected parents.
	 */
	
	public ArrayList<Chromosome> tournamentSelection(ArrayList<Chromosome> chromosomes, int number){
		// Calculate the number of series.
		int number_of_series = (int) chromosomes.size() / 25;
		// Fix the number_of_series to work.
		if(number_of_series < number){
			number_of_series = number;
		}
		int chromossomes_per_serie = chromosomes.size() / number_of_series;
		// If zero, call the ranking selection because there is no need for tournament.
		if(number_of_series == 0){
			return rankingSelection(chromosomes, number);
		}else{
			ArrayList<ArrayList<Chromosome>> tournament = new ArrayList<ArrayList<Chromosome>>();
			ArrayList<Chromosome> aux = new ArrayList<Chromosome>();
			
			int j = 0;
			for(int i = 0; i < chromosomes.size(); i++){
				aux.add(chromosomes.get(i));
				if(i % chromossomes_per_serie == 0){
					Collections.sort(aux);
					tournament.add(aux);
					j++;
					if(j < number_of_series){
						aux = new ArrayList<Chromosome>();
					}
				}
			}
			
			ArrayList<Chromosome> final_selection = new ArrayList<Chromosome>();
			if(number == number_of_series){
				for(int i = 0; i < number; i++){
					final_selection.add(tournament.get(i).get(0));
				}
				return final_selection;
			}else if(number < number_of_series){
				// Example, 4 parents for 8 series.
				// Get 2 for each series, form a new array of chromosomes and call tournament selection. 
				int selected_for_array = (int) number_of_series / 4;
				for(int i = 0; i < number_of_series; i++){
					for(int l = 0; l < selected_for_array; l++){
						final_selection.add(tournament.get(i).get(l));
					}
				}
				return tournamentSelection(final_selection, number);
			}
		}
		
		return chromosomes;
	}
	
	/**
	 * This method select the parents using the addicted roulette model, 
	 * it consists in divide the chromosomes in a roullete and select
	 * parts of it as parent.
	 * @param chromosomes The list that will get the parents.
	 * @param number The number of parents that is needed.
	 * @return ArrayList<Chromosome> The selected parents.
	 */
	
	public ArrayList<Chromosome> rouletteSelection(ArrayList<Chromosome> chromosomes, int number){
		// Calculate the fitness 2 for each chromosome, it is the mod(fitness - bigger_fitness)
		// Calculate the sum of fitness2.
		float sum = 0;
		
		for(int i = 0; i < chromosomes.size(); i++){
			float fitness = Math.abs(chromosomes.get(i).getFitness() - this.bigger_fitness + 1);
			chromosomes.get(i).setFitness2(fitness);
			sum = sum + fitness;
		}
		
		// Make a random number usgin 1 as minimum and sum as maximum.
		Random random = new Random();
		float r = random.nextFloat() * (sum - 1) + 1;
		
		// Choose the expected chromosome for that number.
		int selected = 0;
		float last = 0;
		ArrayList<Chromosome> final_selection = new ArrayList<Chromosome>();
		
		while(selected <= number){
			
			for(int i = 0; i < chromosomes.size(); i++){
				float actual = last + chromosomes.get(i).getFitness2();
				if(r >= last && r <= actual){
					final_selection.add(chromosomes.get(i));
					selected++;
					r = random.nextFloat() * (sum - 1) + 1;
					i = 0;
					last = 0;
				}
				
				last = last + chromosomes.get(i).getFitness2();
				
				if(selected >= number){
					return final_selection;
				}
			}
			
			last = 0;
		}
		
		
		return final_selection;
	}
	
	/**
	 * This method combine two chromosomes in two new.
	 * @param chromosome1 The first chromosome.
	 * @param chromosome2 The second chromosome.
	 * @param type The crossover type.
	 * @return ArrayList<Chromosome> The two chromosomes generated.
	 * */
	public  ArrayList<Chromosome> crossover(Chromosome chromosome1, Chromosome chromosome2, CrossoverType type){
		if(type == CrossoverType.P){
			Gene g1 = chromosome1.getGenes().get(0);
			Gene g2 = chromosome1.getGenes().get(1);
			Gene g3 = chromosome2.getGenes().get(2);
			Gene g4 = chromosome2.getGenes().get(3);
			
			ArrayList<Gene> genes = new ArrayList<Gene>();
			genes.add(g1);
			genes.add(g2);
			genes.add(g3);
			genes.add(g4);
			
			Chromosome chromosome3 = new Chromosome(genes);
			
			Gene g5 = chromosome2.getGenes().get(0);
			Gene g6 = chromosome2.getGenes().get(1);
			Gene g7 = chromosome1.getGenes().get(2);
			Gene g8 = chromosome1.getGenes().get(3);
			
			
			ArrayList<Gene> genes1 = new ArrayList<Gene>();
			genes1.add(g5);
			genes1.add(g6);
			genes1.add(g7);
			genes1.add(g8);
			
			Chromosome chromosome4 = new Chromosome(genes1);
			
			ArrayList<Chromosome> chromosomes = new ArrayList<Chromosome>();
			chromosomes.add(chromosome3);
			chromosomes.add(chromosome4);
			
			return chromosomes;
		}
		
		Gene g1 = chromosome2.getGenes().get(0);
		Gene g2 = chromosome1.getGenes().get(1);
		Gene g3 = chromosome1.getGenes().get(2);
		Gene g4 = chromosome2.getGenes().get(3);
		
		ArrayList<Gene> genes = new ArrayList<Gene>();
		genes.add(g1);
		genes.add(g2);
		genes.add(g3);
		genes.add(g4);
		
		Chromosome chromosome3 = new Chromosome(genes);
		
		Gene g5 = chromosome1.getGenes().get(0);
		Gene g6 = chromosome2.getGenes().get(1);
		Gene g7 = chromosome2.getGenes().get(2);
		Gene g8 = chromosome1.getGenes().get(3);
		
		
		ArrayList<Gene> genes1 = new ArrayList<Gene>();
		genes1.add(g5);
		genes1.add(g6);
		genes1.add(g7);
		genes1.add(g8);
		
		Chromosome chromosome4 = new Chromosome(genes1);
		
		ArrayList<Chromosome> chromosomes = new ArrayList<Chromosome>();
		chromosomes.add(chromosome3);
		chromosomes.add(chromosome4);
		
		return chromosomes;
		
	}
	
	public ArrayList<Chromosome> getPopulation(){
		return null;
	}
	
	public Chromosome train(){
		return null;
	}
}


