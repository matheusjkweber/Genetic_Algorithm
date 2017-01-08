package Genetic_Algorithm;

import java.util.ArrayList;

/**
* The Chromossome class implements the chromossome of GA, fitness function and mutation.
*
* @author  Matheus Jose Krumenauer Weber
* @version 1.0
* @since   2017-01-07
*/

public class Chromosome {
	private ArrayList<Gene> genes; 
	private float fitness;
	
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
	
	/**
	  * This method returns a String describing the chromosome.
	  * @return String The description of this chromosome.
	  */
	
	@Override
	public String toString() {
		return "Chromosome [Fitness=" + fitness + "]";
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
		float FC = 0; //fixed cost
		float VC = 0; //variable cost
		
		// Cf = (D \times \pi) \times L \times p \times \rho_{chapa}
		FC = (float) (genes.get(0).getValue() * 3.14 * genes.get(1).getValue() * genes.get(2).getValue() * genes.get(3).getValue());
		
		// CV = (Q \times 1000 \times ( hg + ( Q \div (0,278 \times C \times D ^ {2,63}))^{1,85} \\ \times(L + L_{virtual}))) \div (75 \times n_{global})
		
		float d = (float) Math.pow(genes.get(0).getValue(),2.63); //  D ^ {2,63}
		d = (float) (0.278 * genes.get(6).getValue() * d); // (0,278 \times C \times D ^ {2,63})
		d = genes.get(5).getValue() + genes.get(4).getValue() / d; // hg + (Q \div (0,278 \times C \times D ^ {2,63})
		d = genes.get(4).getValue() * 1000 * d; // (Q \times 1000 \times ( hg + ( Q \div (0,278 \times C \times D ^ {2,63}))^{1,85}
		
		float e = genes.get(1).getValue() + genes.get(7).getValue(); // L + L_{virtual}
		
		d = d * e; // (Q \times 1000 \times ( hg + ( Q \div (0,278 \times C \times D ^ {2,63}))^{1,85} \\ \times(L + L_{virtual})))
		
		d = d / (75 * genes.get(8).getValue());
		
		fitness = d;
	}
}
