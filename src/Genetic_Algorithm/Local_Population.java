package Genetic_Algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Local_Population extends Population implements Runnable{
	private ArrayList<Chromosome> population;
	
	/**
	   * Constructor of global_population with value.
	   * @param size The size of the population.
	   * @param parameters The parameters of this population.
	   */
	
	public Local_Population(Parameter parameters) {
		super(parameters);
		this.population = new ArrayList<Chromosome>();
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
	 * If first time, this method will randomly generate a new population for the Genetic Algorithm.
	 * If not first time, this method will also include the best parents from the previous generation.
	 * @param boolean If is first time = true, if not = false.
	 */
	
	@Override
	public void generatePopulation(boolean first_time){
		if(first_time == true){
			for(int i = 0; i < this.size; i++){
				population.add(this.create_chromosome(i));
				if(population.get(i).getFitness() > this.bigger_fitness){
					this.bigger_fitness = population.get(i).getFitness();
				}
			}
		}else{
			// Regenerate the population.
			// Apply elitism.
			float proportion = ((float) elitism_rate) / 100;
			proportion = size * proportion;
			
			// Select parents to elitism.
			selectParents(population, SelectionType.RM, (int) proportion);
			
			
			ArrayList<Chromosome> new_population = new ArrayList<Chromosome>();
			new_population = this.parents;
			
			// Rewrite all the parents ordering it.
			selectParents(population, SelectionType.RM, population.size());
			
			// Prepare parents for crossover.
			ArrayList<Chromosome> cross_population = this.parents;
			
			// Remove the parents already selected by elitism.
			for(int i = 0; i < proportion - 1; i++){
				if(i >= cross_population.size()){
					break;
				}
				cross_population.remove(i);
			}
			
			// Populate the rest of population by selection + crossover + mutation.
			
			// Apply crossover and get the rest applying mutation if need.
			selectParents(cross_population, parameters.getSelection(), size - (int) proportion);
			/*Get 10% of parents to crossover, 10% from elitism and 80% random.
			this.generateRandomWithoutRepeat(100, 2000);
			int added = (int) (proportion + (size / 10));
			for(int i = added, j = 0; i < this.size; i++, j++){
				new_population.add(this.create_chromosome(j));
			}*/
			
			for(int i = 0; i < parents.size() - 1; i = i+2){
				Chromosome c1 = parents.get(i);
				Chromosome c2 = parents.get(i+1);
				
				ArrayList<Chromosome> c3 = crossover(c1, c2, parameters.getCrossoverType());
				
				new_population.add(c3.get(0));
				new_population.add(c3.get(1));
			}
						
			population = new_population;
			//Collections.sort(population);
			// Apply mutation.
			for(int i = 0; i < population.size(); i++){
				Chromosome c1 = population.get(i);
				
				// Apply mutation if drawn.
				Random random = new Random();
				for(int z = 0; z < c1.getGenes().size(); z++){
					int r = random.nextInt(100) + 0;
					
					if(r < parameters.getMutationRate()){
						c1.mutate(z);
					}
				}
				
				
				
			}
		}
	}
	
	/**
	 * This method train the population and returns the best chromosome found.
	 * @return Chromosome.
	 */
	@Override
	public ArrayList<Chromosome> train(){
		boolean stop = false;
		int iterations = 0;
		int unchangedLastSelected = 0;
		ArrayList<Chromosome> selectedChromosomes = new ArrayList<Chromosome>();
		// Genetic algorithm loop.
		while(stop == false){
			iterations++;
			// Create first population or repopulate and calculate the fitness.
			if(population.size() == 0){
				generatePopulation(true);
			}else{
				// Select parents, apply crossover, apply mutation and create a new population.
				generatePopulation(false);
			}
			// Get the best chromosome.
			selectParents(population, SelectionType.RM, 1);
			
			// Increase unachanged flag.
			if(last_selected != null && parents.size() > 0){
				if(last_selected.getFitness() <= parents.get(0).getFitness()){
					unchangedLastSelected++;
				}
			}
			
			// If reach the maximum iterations number, stop.
			if(this.maximum_iterations == iterations){
				stop = true;
			}
			
			// Save the best.
			if(parents.size() > 0){
				last_selected = parents.get(0);
				selectedChromosomes.add(last_selected);
			}

			float proportion = (int)(this.maximum_iterations*(parameters.getStopCondition()/100.0f));
			
			// If reach the maximum unchanged chromosome allowed, stop.
			if(unchangedLastSelected > proportion){
				stop = true;
			}
			
		}
		return selectedChromosomes;
		
	}

	@Override
	public void run() {
		train();
	}
	
}
