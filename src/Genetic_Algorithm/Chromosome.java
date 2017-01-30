package Genetic_Algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
* The Chromossome class implements the chromossome of GA, fitness function and mutation.
*
* @author  Matheus Jose Krumenauer Weber
* @version 1.0
* @since   2017-01-07
*/

public class Chromosome implements Comparable {
	private ArrayList<Gene> genes; 
	private float fitness;
	private float fitness2;
	
	/**
	   * Constructor of gene with value.
	   * @param genes The genes of this chromossome.
	   */
	
	public Chromosome(ArrayList<Gene> genes) {
		this.genes = genes;
		this.fitness = 0;
		this.fitnessFunction();
	}
	
	/**
	  * This method returns a ArrayList<Gene> from the genes of this chromosome.
	  * @return ArrayList<Gene> Genes of this chromosome.
	  */
	
	public ArrayList<Gene> getGenes() {
		return genes;
	}
	
	/**
	  * This method set a new ArrayList<Gene> for the genes parameter.
	  * @param ArrayList<Gene> The list of genes.
	  */
	
	public void setGenes(ArrayList<Gene> genes) {
		this.genes = genes;
	}
	
	/**
	  * This method returns a float that will be the fitness of this chromosome.
	  * @return Float The fitness.
	  */
	
	public float getFitness() {
		return fitness;
	}
	
	/**
	  * This method set a new fitness for this cromosome.
	  * @param Float The new value for fitness.
	  */
	
	public void setFitness(float fitness) {
		this.fitness = fitness;
	}
	
	
	public float getFitness2() {
		return fitness2;
	}

	public void setFitness2(float fitness2) {
		this.fitness2 = fitness2;
	}

	/**
	  * This method returns a String describing the chromosome.
	  * @return String The description of this chromosome.
	  */
	
	@Override
	public String toString() {
		return "Chromosome [Fitness=" + fitness + ", D = "+genes.get(0).getValue()+", hg = "+genes.get(1).getValue()+", L = "+
	genes.get(2).getValue()+", Q = "+genes.get(3).getValue()+"]";
	}
	
	/**
	 * This method iterate the list of genes mutating each one based on its MutationType.
	 */
	
	public void mutate(){
		
	}
	
	/**
	 * This method iterate the list of genes getting it to use in the Fitness Function, then update the fitness of chromossome.
	 */
	
	public void fitnessFunction(){
		Gene d = genes.get(0);
		Gene hg = genes.get(1);
		Gene l = genes.get(2);
		Gene q = genes.get(3);
		
		Config config = new Config();
		
		float weight = (float) ((3.14 * d.getValue()/1000) * config.getP_mass());
		float speed = (float) ((q.getValue()/1000 * 4)/(3.1415*(Math.pow(d.getValue()/1000, 2))));
		float fixed_cost = (float) (weight * l.getValue() * config.getP4()); 
		float anual_fixed_cost = fixed_cost * config.getFRC();
		
		// Calculus of AMT
		float a = q.getValue() / 1000;
		float b = (float) Math.pow(a / (0.278 * config.getC() * Math.pow(d.getValue() / 1000, 2.63)), 1.85);
		float c = l.getValue() + ((config.getN1() * d.getValue()) / 1000);
		
		float AMT = hg.getValue() + b  * c;
		
		float power_CV = (q.getValue() * AMT) / (75 * config.getRend());
		float power_kW = (float) (power_CV * 0.736);
		
		float kWh_horosazonal = (float) (power_kW * ((config.getN_h()/21) * 12.5)); // 12.5 is the time that normally work in "horosazonal" tariff.
		float kWh_reservedTime = (float) (power_kW * ((config.getN_h()/21) * 8.5)); // 8.5 is the time that normally work in "reserved time" tariff.
		
		float comsumption = (kWh_horosazonal * config.getRev_p1()) + (kWh_reservedTime * config.getRev_p3());
		float demand = power_kW * config.getRev_p2() * 4;
		
		float energy_cost = comsumption + demand;
		
		float anual_cost = energy_cost + anual_fixed_cost;
		
		fitness = anual_cost;
	}
	
	/**
	 * This method iterrate all genes, and randomly mutate one.
	 * @mutate_rate The rate that mutation can happen.
	 * @step The step that mutation will happen.
	 */
	
	public void mutate(int step){
		for(int i = 0; i < genes.size(); i++){
			genes.get(i).mutate(step);
		}
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Chromosome c = (Chromosome) o;
		float compareFitness = c.getFitness();
		
		return (int) (this.fitness - compareFitness);
	}
}
