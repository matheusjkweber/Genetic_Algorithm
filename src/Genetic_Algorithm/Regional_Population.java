package Genetic_Algorithm;

import java.util.ArrayList;
import java.util.Random;


public class Regional_Population extends Population{
	private ArrayList<Local_Population> population;
	private double migration_rate;
	private int number_of_populations;
	private int migration_tax;
	
	/**
	   * Constructor of global_population with value.
	   * @param size The size of the population.
	   * @param migration_rate The migration rate of this population.
	   * @param migration_tax The % of population that will migrate.
	   * @param number_of_populations The number of subpopulations that this model will use.
	   */
	public Regional_Population(Parameter parameters) {
		super(parameters);
		this.migration_rate = parameters.getMigrationRate();
		this.number_of_populations = parameters.getNumberOfPopulations();
		this.migration_tax = parameters.getMigrationTax();
		this.population = new ArrayList<Local_Population>();
		
		// Create the set of subpopulations.
		for(int i = 0; i < number_of_populations; i++){
			// Number_max_iterations of population will be based on migatrion rate.
			int number = (int) (100 - migration_rate);
			this.parameters.setMaximumIterations(number);
			
			population.add(new Local_Population(parameters));
		}
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
	
	public void setPopulation(ArrayList<Local_Population> population) {
		this.population = population;
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
	 * This method will raffle if migrate or not based on migration_chance, if yes will randomly choose chromosomes(based on migration_rate)
	 * from a randomly subpopulation and migrate to other subpopulation defined by random.
	 */
	
	public void migrate(){
		
	}
	
	/**
	 * If first time, this method will randomly generate a new population for the Genetic Algorithm.
	 * If not first time, this method will also include the best parents from the previous generation.
	 */
	
	public void generatePopulation(boolean first_time){
		for(int j = 0; j < this.number_of_populations; j++){
			this.population.get(j).generatePopulation(first_time);
		}		
	}
	
	public Chromosome train(){
		boolean stop = false;
		int iterations = 0;
		int unchangedLastSelected = 0;
		ArrayList<Chromosome> selected = new ArrayList<Chromosome>();
		ArrayList<Thread> threads = new ArrayList<Thread>();
		Chromosome select = null;

		// Genetic algorithm loop.
		while(stop == false){
			iterations++;	
			unchangedLastSelected++;
			
			threads.clear();
			selected.clear();
			// Train each population.
			for(int i = 0; i < this.population.size(); i++){
				threads.add(new Thread(this.population.get(i)));
				threads.get(i).start();
			}
			
			// Train each population.
			for(int i = 0; i < this.population.size(); i++){
				try {
					threads.get(i).join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// Get the selected chromosomes.
			for(int i = 0; i < this.population.size(); i++){
				selected.add(this.population.get(i).getLast_selected());
				threads.get(i).interrupt();
			}
			// Migrate chromosomes.
			for(int i = 0; i < this.population.size() - 1; i++){
				float proportion = ((float) migration_tax) / ((float) this.population.get(i).getPopulation().size());
				proportion = proportion * 100;
				
				// Get the proportion chromosomes from the population and trade with from the next population.
				for(int j = 0; j < proportion; j++){
					Random random = new Random();
					int r = random.nextInt(this.population.get(i).getPopulation().size() - 1) + 0;
					
					Chromosome aux = this.population.get(i).getPopulation().get(r);
					
					if(r >= this.population.get(i).getPopulation().size()){
						r = this.population.get(i).getPopulation().size() - 1;
					}
					
					if(r >= this.population.get(i+1).getPopulation().size()){
						r = this.population.get(i+1).getPopulation().size() - 1;
					}
					
					this.population.get(i).getPopulation().set(r, this.population.get(i+1).getPopulation().get(r));
					
					this.population.get(i+1).getPopulation().set(r, aux);
				}
				
			}
			// Stop condition.
			for(int i = 0; i < selected.size(); i++){
				if(select == null || select.getFitness() > selected.get(i).getFitness()){
					select = selected.get(i);
					this.last_selected = select;
					unchangedLastSelected = 0;
				}
			}
			
			// If reach the maximum iterations number, stop.
			if(this.maximum_iterations == iterations){
				stop = true;
			}
			
			float proportion = (int)(this.maximum_iterations*(parameters.getStopCondition()/100.0f));
			
			// If reach the maximum unchanged chromosome allowed, stop.
			if(unchangedLastSelected > proportion){
				stop = true;
			}
		}
		
		return this.last_selected;
	}
}
