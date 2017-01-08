package Genetic_Algorithm;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


/**
* The Population class implements a generic population, populate, selection and crossover.
*
* @author  Matheus Jose Krumenauer Weber
* @version 1.0
* @since   2017-01-07
*/

public class Population {
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
	
	public void selectParents(SelectionType type){
		
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
		Gene d = new Gene("Diameter", random.nextInt(100) + 1, true, MutationType.SBS); // Gene for diameter(m).
		Gene l = new Gene("Lenght", random.nextInt(100) + 1, true, MutationType.SBS); // Gene for length(mm)
		Gene p = new Gene("Price of steel", (float) 14.50, false, MutationType.NONE); // Gene for the price of steel(R$ kg^-1), TODO: Will be a entry, get the real value and put into the article.
		Gene p_mass = new Gene("Specific mass", (float) 1.2, false, MutationType.NONE); // Gene for the specific mass of the sheet used to make the pipe(kg^m-2), TODO: Will be a entry, get the real value and put into the article.
		Gene q = new Gene("Flow", (float) (random.nextFloat() * (100 - 0.1) + 0.1), true, MutationType.SBSD100); // Gene for flow(m3^s-1).
		Gene hg = new Gene("Manometric Height", (float) (random.nextFloat() * (100 - 0.1) + 0.1), true, MutationType.SBSD100); // Gene for geometric height(m).
		Gene c = new Gene("Roughness Coeficient", 90, false, null); // is the roughness coefficient of Hazen-Williams (adopted value 90 that configures welded steel tubing with 10 years of use);
		Gene l_virtual = new Gene("Virtual Length", random.nextInt(100) + 1, true, MutationType.SBS); // Gene for length(mm)
		Gene n_global = new Gene("Assembly efficiency", 100, false, null); // Gene for assembly efficiency, TODO: Verify if is entry and the "medida".
		Gene p_demand = new Gene("Price by demand", (float) 14.50,  false, MutationType.NONE); // Gene for price by demand.
		Gene n_hoursTH = new Gene("Hours in green horosozaonal tariff", 6,  false, MutationType.NONE); // is the number of hours that EB operates in green horosazonal tariff;
		Gene p_th = new Gene("Price in green horosozaonal", (float) 12.50, false, MutationType.NONE); //  is the price of kW at the time of the green seasonal tariff (R\$ kWh$^{-1}$);
		Gene n_hours = new Gene("Hours in reserved time rate", 12,  false, MutationType.NONE); // is the number of hours that EB operates at a reserved time rate; 
		Gene p_hr = new Gene("Price in reserved time", (float) 17.50, false, MutationType.NONE); //  is the price of the kWh in reserved time of concierge (R\$ kWh$^{-1}$);
		
		ArrayList<Gene> genes = new ArrayList<Gene>();
		genes.add(d);
		genes.add(l);
		genes.add(p);
		genes.add(p_mass);
		genes.add(q);
		genes.add(hg);
		genes.add(c);
		genes.add(l_virtual);
		genes.add(n_global);
		genes.add(p_demand);
		genes.add(n_hoursTH);
		genes.add(p_th);
		genes.add(n_hours);
		genes.add(p_hr);
		
		Chromosome chromosome = new Chromosome(genes);
				
		return chromosome;
	}
}


