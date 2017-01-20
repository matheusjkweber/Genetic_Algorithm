package Genetic_Algorithm;

import java.util.Random;

/**
	* The Gene class implements the all the parameters necessary to create a new gene.
	*
	* @author  Matheus Jose Krumenauer Weber
	* @version 1.0
	* @since   2017-01-07
	*/

public class Gene {
	private String label;
	private float value;
	private boolean mutate;
	private MutationType mutation;
	
	/**
	   * Constructor of gene with value.
	   * @param label The label described by this gene(Phenotype).
	   * @param value A real number value that this gene start.
	   * @param mutate True if this gene suffer mutation, false if not.
	   * @param MutationType The type of mutation that this gene suffer.
	   */
	
	public Gene(String label, float value, boolean mutate,
			MutationType mutation) {
		super();
		this.label = label;
		this.value = value;
		this.mutate = mutate;
		this.mutation = mutation;
	}
	
	/**
	  * This method returns a string.
	  * @return String The label of the gene.
	  */
	
	public String getLabel() {
		return label;
	}
	
	/**
	  * This method set a new value for label.
	  * @param String The new label for this gene.
	  */
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	/**
	  * This method returns a float.
	  * @return float The value of the gene.
	  */
	
	public float getValue() {
		return value;
	}
	
	/**
	  * This method set a new value for value.
	  * @param float The new value for this gene.
	  */
	
	public void setValue(float value) {
		this.value = value;
	}
	
	/**
	  * This method returns a boolean.
	  * @return boolean True if this gene suffer mutation, false if not.
	  */
	
	public boolean isMutate() {
		return mutate;
	}
	
	/**
	  * This method set a new boolean for mutate.
	  * @param boolean True if this gene will suffer mutation, false if not.
	  */
	
	public void setMutate(boolean mutate) {
		this.mutate = mutate;
	}
	
	/**
	  * This method returns a MutationType.
	  * @return MutationType The MutationType for this gene.
	  */
	
	public MutationType getMutation() {
		return mutation;
	}
	
	/**
	  * This method set a new MutationType for mutation.
	  * @param MutationType The new mutation of this gene.
	  */
	
	public void setMutation(MutationType mutation) {
		this.mutation = mutation;
	}
	
	/**
	 * This method mutation a gene based on MutantionType.
	 * @param step The step that mutation will have.
	 */
	public void mutate(int step){
		if(mutation == MutationType.SBSD3){
			step = step / 3;
		}
		
		Random random = new Random();
		
		// Random the step.
		int r1 = random.nextInt(step) + 1;
		step = r1;
		
		int r = random.nextInt(2) + 1;
		
		// Random if is positive or negative.
		if(r == 1){
			step = step * -1;
		}
		
		float final_value = value + step;
		
		// Only change if final value is positive.
		if(final_value > 0){
			value = final_value;
		}
	}
	
	@Override
	
	/**
	  * This method returns a String describing the gene.
	  * @return String The description of this gene.
	  */
	
	public String toString() {
		return "Gene [label=" + label + ", value=" + value + ", mutate="
				+ mutate + ", mutation=" + mutation + "]";
	}
}