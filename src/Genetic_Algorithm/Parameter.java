package Genetic_Algorithm;

/**
* The Parameter class implements the parameters that can be used on test.
*
* @author  Matheus Jose Krumenauer Weber
* @version 1.0
* @since   2017-01-07
*/

public class Parameter{
	private ModelPopulationType modelPopulation;
	private int sizeOfPopulation;
	private int elitismRate;
	private SelectionType selection;
	private float crossoverRate;
	private CrossoverType crossoverType;
	private float mutationRate;
	private int mutationStep;
	private float stopCondition;
	
	/**
	   * Constructor of parameter with value.
	   * @param modelPopulation The model of population that will be used on this test.
	   * @param sizeOfPopulation The size of population that will be used on this test.
	   * @param elitismRate The elitism rate that will be used on this test.
	   * @param crossoverRate The crossover rate that will be used on this test.
	   * @param crossoverType The crossover type that will be used on this test.
	   * @param mutationRate The mutation rate that will be used on this test.
	   * @param mutationStep The mutation step that will be used on this test.
	   * @param stopCondition The stop condition that will be used on this test.
	   */
	
	public Parameter(ModelPopulationType modelPopulation, int sizeOfPopulation,
			int elitismRate, SelectionType selection, float crossoverRate,
			CrossoverType crossoverType, float mutationRate, int mutationStep,
			float stopCondition) {
		super();
		this.modelPopulation = modelPopulation;
		this.sizeOfPopulation = sizeOfPopulation;
		this.elitismRate = elitismRate;
		this.selection = selection;
		this.crossoverRate = crossoverRate;
		this.crossoverType = crossoverType;
		this.mutationRate = mutationRate;
		this.mutationStep = mutationStep;
		this.stopCondition = stopCondition;
	}
	
	/**
	  * This method returns a ModelPopulationType.
	  * @return ModelPopulationType The type of population.
	  */
	
	public ModelPopulationType getModelPopulation() {
		return modelPopulation;
	}
	
	/**
	  * This method set a new value for modelPopulation.
	  * @param ModelPopulationType The new modelPopulation for this parameter.
	  */
	
	public void setModelPopulation(ModelPopulationType modelPopulation) {
		this.modelPopulation = modelPopulation;
	}
	
	/**
	  * This method returns a int.
	  * @return int The size of this parameter.
	  */
	
	public int getSizeOfPopulation() {
		return sizeOfPopulation;
	}
	
	/**
	  * This method set a new value for sizeOfPopulation.
	  * @param int The new sizeOfPopulation for this parameter.
	  */
	
	public void setSizeOfPopulation(int sizeOfPopulation) {
		this.sizeOfPopulation = sizeOfPopulation;
	}
	
	/**
	  * This method returns a int.
	  * @return int The elitismRate of the parameter.
	  */
	
	public int getElitismRate() {
		return elitismRate;
	}
	
	/**
	  * This method set a new value for elitismRate.
	  * @param int The new elitismRate for this parameter.
	  */
	
	public void setElitismRate(int elitismRate) {
		this.elitismRate = elitismRate;
	}
	
	/**
	  * This method returns a SelectionType.
	  * @return SelectionType The selection of the parameter.
	  */
	
	public SelectionType getSelection() {
		return selection;
	}
	
	/**
	  * This method set a new SelectionType for selection.
	  * @param SelectionType The new selection for this parameter.
	  */
	
	public void setSelection(SelectionType selection) {
		this.selection = selection;
	}
	
	/**
	  * This method returns a float.
	  * @return float The crossoverRate of the parameter.
	  */
	
	public float getCrossoverRate() {
		return crossoverRate;
	}
	
	/**
	  * This method set a new value for crossoverRate.
	  * @param float The new crossoverRate for this parameter.
	  */
	
	public void setCrossoverRate(float crossoverRate) {
		this.crossoverRate = crossoverRate;
	}
	
	/**
	  * This method returns a CrossoverType.
	  * @return CrossoverType The crossoverType of the parameter.
	  */
	
	public CrossoverType getCrossoverType() {
		return crossoverType;
	}
	
	/**
	  * This method set a new CrossoverType for crossoverType.
	  * @param CrossoverType The new crossoverType for this parameter.
	  */
	
	public void setCrossoverType(CrossoverType crossoverType) {
		this.crossoverType = crossoverType;
	}
	
	/**
	  * This method returns a float.
	  * @return float The mutationRate of the parameter.
	  */
	
	public float getMutationRate() {
		return mutationRate;
	}
	
	/**
	  * This method set a new value for mutationRate.
	  * @param float The new mutationRate for this parameter.
	  */
	
	public void setMutationRate(float mutationRate) {
		this.mutationRate = mutationRate;
	}
	
	/**
	  * This method returns a int.
	  * @return int The mutationStep of the parameter.
	  */
	
	public int getMutationStep() {
		return mutationStep;
	}
	
	/**
	  * This method set a new value for mutationStep.
	  * @param int The new mutationStep for this parameter.
	  */
	
	public void setMutationStep(int mutationStep) {
		this.mutationStep = mutationStep;
	}
	
	/**
	  * This method returns a float.
	  * @return float The stopCondition of the parameter.
	  */
	
	public float getStopCondition() {
		return stopCondition;
	}
	
	/**
	  * This method set a new value for stopCondition.
	  * @param float The new stopCondition for this parameter.
	  */
	
	public void setStopCondition(float stopCondition) {
		this.stopCondition = stopCondition;
	}

	
	
	
}
