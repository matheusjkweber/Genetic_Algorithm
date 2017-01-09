package Genetic_Algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


/**
* The Population class implements a generic population, populate, selection and crossover.
*
* @author  Matheus Jose Krumenauer Weber
* @version 1.0
* @since   2017-01-07
*/

public abstract class Population {
	protected int size;
	protected ArrayList<Chromosome> parents;
	
	/**
	   * Constructor of population with value.
	   * @param size The size of this population.
	   */
	
	public Population(int size) {
		super();
		this.size = size;
		this.parents = new ArrayList<Chromosome>();
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
	 * If first time, this method will randomly generate a new population for the Genetic Algorithm.
	 * If not first time, this method will also include the best parents from the previous generation.
	 */
	
	public void generatePopulation(){
		
	}
	
	/**
	 * This method will select the best parents from this population based on a specific method and will save on parents.
	 */
	
	public void selectParents(SelectionType type, int selectedChromosomes){
		
	}
	
	/**
	 * This method will perform a crossover based on a specific method. 
	 */
	
	public Chromosome crossover(CrossoverType type, ArrayList<Chromosome> chromosomes){
		
		return null;
	}

	/**
	 * Create a random chromosome.
	 */
	
	public Chromosome create_chromosome(){
		Random random = new Random();		
		/*Gene d = new Gene("Diameter", 200, true, MutationType.SBS);
		Gene hg = new Gene("Hg", 5, true, MutationType.SBS);
		Gene l = new Gene("Lenght", 50, true, MutationType.SBS);
		Gene q = new Gene("Flow", 50, true, MutationType.SBS);*/
		
		Gene d = new Gene("Diameter", random.nextInt(1000) + 1, true, MutationType.SBS);
		Gene hg = new Gene("Hg", random.nextInt(35) + 1, true, MutationType.SBSD3);
		Gene l = new Gene("Lenght", random.nextInt(500) + 1, true, MutationType.SBS);
		Gene q = new Gene("Flow", random.nextInt(500) + 1, true, MutationType.SBS);
		
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
		
		Collections.sort(chromosomes);

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
	 * @param ArrayList<Chromosome> The selected parents.
	 */
	
	public ArrayList<Chromosome> rouletteSelection(ArrayList<Chromosome> chromosomes, int number){
		// The roulette will always be biggest than the population.
		int rouletteSize = chromosomes.size() * 3;
		Collections.sort(chromosomes);
		
		ArrayList<Chromosome> selection = new ArrayList<Chromosome>();
		
		int parts = chromosomes.size() / 3;
		for(int i = 0; i < chromosomes.size(); i++){
			// For first part will put 3 times each one.
			if(i < parts){
				selection.add(chromosomes.get(i));
				selection.add(chromosomes.get(i));
				selection.add(chromosomes.get(i));
			}
			// For second part will put 2 times each one.
			if(i > parts && i < parts * 2){
				selection.add(chromosomes.get(i));
				selection.add(chromosomes.get(i));
			}
			// For third part will put 1 time each one.
			if(i > parts * 2){
				selection.add(chromosomes.get(i));
			}
		}
		
		// Fill the rest of roulette with one of each.
		while(selection.size() < rouletteSize){
			for(int i = 0; i < chromosomes.size(); i++){
				if(selection.size() < rouletteSize){
					selection.add(chromosomes.get(i));
				}
			}
		}
		
		// Select randomly the number of parents needed.
		ArrayList<Chromosome> final_selection = new ArrayList<Chromosome>();
		for(int i = 0; i < number; i++){
			Random random = new Random();
			int r = random.nextInt(rouletteSize) + 1;
			final_selection.add(selection.get(r));
		}
		
		return final_selection;
	}
	
	public ArrayList<Chromosome> getPopulation(){
		return null;
	}
}


