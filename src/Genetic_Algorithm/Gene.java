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
	private int min;
	private int max;
	private float fixedValue;
	
	/**
	   * Constructor of gene with value.
	   * @param label The label described by this gene(Phenotype).
	   * @param value A real number value that this gene start.
	   * @param mutate True if this gene suffer mutation, false if not.
	   * @param MutationType The type of mutation that this gene suffer.
	   * @param min A value that define the minimum value for the gene.
	   * @param max A value that define a maximum value for the gene.
	   * @param fixedValue A value that fix the gene to a value, if 0 don`t fix. 
	   */
	
	public Gene(String label, float value, boolean mutate,
			MutationType mutation, int min, int max, float fixedValue) {
		super();
		this.label = label;
		this.value = value;
		this.mutate = mutate;
		this.mutation = mutation;
		this.min = min;
		this.max = max;
		this.fixedValue = fixedValue;
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
	  * This method set the minimum value that the gene can assume.
	  * @return int.
	  */
	
	public int getMin() {
		return min;
	}

	/**
	  * This method set a new mininum value for this gene.
	  * @param int.
	  */
	
	public void setMin(int min) {
		this.min = min;
	}

	
	/**
	  * This method set the maximum value that the gene can assume.
	  * @param int.
	  */
	
	public void setMax(int max) {
		this.max = max;
	}
	
	/**
	  * This method returns the maximum value that the gene can assume.
	  * @return int.
	  */
	
	public int getMax() {
		return max;
	}
	
	
	/**
	  * This method returns the fixed value that the gene can assume.
	  * @return float.
	  */
	
	public float getFixed_value() {
		return fixedValue;
	}
	
	/**
	  * This method set the fixed value that the gene can assume.
	  * @param float.
	  */
	
	public void setFixed_value(float fixedValue) {
		this.fixedValue = fixedValue;
	}

	/**
	 * This method mutation a gene based on MutantionType.
	 * @param step The step that mutation will have.
	 */
	
	public void mutate(){
		if(this.fixedValue != 0){
			Random random = new Random();
			int step = 100;
			
			if(mutation == MutationType.SBSD3){
				step = step / 3;
			}
			
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
			
			// Verify if it is between minimum and maximum allowed.
			if(value <= min){
				value = min;
			}
			
			if(value >= max){
				value = max;
			}
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