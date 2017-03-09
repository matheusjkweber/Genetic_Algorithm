package Genetic_Algorithm;

import java.util.ArrayList;

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
	private int maximumIterations;
	private int nGenerations;
	private double migrationRate;
	private int migrationTax;
	private int numberOfPopulations;
	private int nSlaves;
	
	/**
	   * Constructor of parameter with value.
	   * @param modelPopulation The model of population that will be used on this test.
	   * @param sizeOfPopulation The size of population that will be used on this test.
	   * @param elitismRate The elitism rate that will be used on this test.
	   * @param selection The type of selection that will be used on this test.
	   * @param crossoverRate The crossover rate that will be used on this test.
	   * @param crossoverType The crossover type that will be used on this test.
	   * @param mutationRate The mutation rate that will be used on this test.
	   * @param mutationStep The mutation step that will be used on this test.
	   * @param stopCondition The stop condition that will be used on this test.
	   * @param maximumIterations The maximum iterations that will be used on this test.
	   * @param nGenerations The number of generations that regional population will take, only used for RM model.
	   * @param migrationRate The migration rate that regional population will use, only used for RM model.
	   * @param migrationChance The migration chance that regional population will use, only used for RM model.
	   * @param numberOfPopulations The number of subpopulations that regional population will use, only used for RM model.
	   * @param nSlaves The number of slaves that global population will use, only used for GM model.
	   */
	
	public Parameter(ModelPopulationType modelPopulation, int sizeOfPopulation,
			int elitismRate, SelectionType selection, float crossoverRate,
			CrossoverType crossoverType, float mutationRate, int mutationStep,
			float stopCondition, int maximumIterations, int nGenerations, double migrationRate, double migrationChance, int numberOfPopulations, int nSlaves) {
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
		this.maximumIterations = maximumIterations;
		
		this.nGenerations = nGenerations;
		this.migrationRate = migrationRate;
		//this.migration_chance = e;
		this.numberOfPopulations = numberOfPopulations;
		this.nSlaves = nSlaves;
	}
	
	/**
	   * Constructor of parameter with value.
	   * @param modelPopulation The model of population that will be used on this test.
	   * @param numberIterations The maximum number of iterations that will be used on this test.
	   * @param stopCondition The stop condition that will be used on this test.
	   * @param sizeOfPopulation The size of population that will be used on this test.
	   * @param elitismRate The elitism rate that will be used on this test.
	   * @param crossoverRate The crossover rate that will be used on this test.
	   * @param crossoverType The crossover type that will be used on this test.
	   * @param mutationRate The mutation rate that will be used on this test.
	   * @param mutationStep The mutation step that will be used on this test.
	   */
	
	public Parameter(ModelPopulationType modelPopulation, int numberIterations, int stopCondition, int sizeOfPopulation,
			int elitismRate, float crossoverRate, CrossoverType crossoverType, float mutationRate, int mutationStep){
		super();
		this.maximumIterations = numberIterations;
		this.stopCondition = stopCondition;
		this.sizeOfPopulation = sizeOfPopulation;
		this.elitismRate = elitismRate;
		this.crossoverRate = crossoverRate;
		this.crossoverType = crossoverType;
		this.mutationRate = mutationRate;
		this.mutationStep = mutationStep;
		this.modelPopulation = modelPopulation;
	}
	
	/**
	   * Constructor of parameter with value.
	   * @param modelPopulation The model of population that will be used on this test.
	   * @param numberIterations The maximum number of iterations that will be used on this test.
	   * @param stopCondition The stop condition that will be used on this test.
	   * @param sizeOfPopulation The size of population that will be used on this test.
	   * @param elitismRate The elitism rate that will be used on this test.
	   * @param crossoverRate The crossover rate that will be used on this test.
	   * @param crossoverType The crossover type that will be used on this test.
	   * @param mutationRate The mutation rate that will be used on this test.
	   * @param mutationStep The mutation step that will be used on this test.
	   * @param migrationRate The migration rate that regional population will use, only used for RM model.
	   * @param migrationTax The migration tax that regional population will use, only used for RM model.
	   * @param numberOfPopulations The number of subpopulations that regional population will use, only used for RM model.
	   */
	
	public Parameter(ModelPopulationType modelPopulation, int numberIterations, int stopCondition, int sizeOfPopulation, 
			int elitismRate, float crossoverRate, CrossoverType crossoverType, float mutationRate, int mutationStep,
			double migrationRate, int migrationTax, int numberOfPopulations){
		super();
		this.maximumIterations = numberIterations;
		this.stopCondition = stopCondition;
		this.sizeOfPopulation = sizeOfPopulation;
		this.elitismRate = elitismRate;
		this.crossoverRate = crossoverRate;
		this.crossoverType = crossoverType;
		this.mutationRate = mutationRate;
		this.mutationStep = mutationStep;
		this.modelPopulation = modelPopulation;
		this.migrationRate = migrationRate;
		this.numberOfPopulations = numberOfPopulations;
		this.migrationTax = migrationTax;
	}
	
	
	/**
	  * This method returns a int.
	  * @return int The number of generations.
	  */
	
	public int getNGenerations() {
		return nGenerations;
	}
	
	/**
	  * This method set a new value for n_generations.
	  * @param n_generations The new n_generations for this parameter.
	  */
	
	public void setNGenerations(int nGenerations) {
		this.nGenerations = nGenerations;
	}
	
	/**
	  * This method returns a float.
	  * @return double The migration rate.
	  */
	
	public double getMigrationRate() {
		return migrationRate;
	}
	
	/**
	  * This method set a new value for migration_rate.
	  * @param migration_rate The new migration_rate for this parameter.
	  */
	
	public void setMigrationRate(double migrationRate) {
		this.migrationRate = migrationRate;
	}
	
	/**
	  * This method returns a int.
	  * @return int The number of populations.
	  */
	
	public int getNumberOfPopulations() {
		return numberOfPopulations;
	}
	
	/**
	  * This method set a new value for number_of_populations.
	  * @param number_of_populations The new number_of_populations for this parameter.
	  */
	
	public void setNumberOfPopulations(int numberOfPopulations) {
		this.numberOfPopulations = numberOfPopulations;
	}
	
	/**
	  * This method returns a int.
	  * @return int The number of slaves.
	  */
	
	public int getNSlaves() {
		return nSlaves;
	}
	
	/**
	  * This method set a new value for n_slaves.
	  * @param n_slaves The new n_slaves for this parameter.
	  */
	
	public void setNSlaves(int nSlaves) {
		this.nSlaves = nSlaves;
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

	/**
	  * This method returns a int.
	  * @return int The maximumIterations of the parameter.
	  */
	
	public int getMaximumIterations() {
		return maximumIterations;
	}
	
	/**
	  * This method set a new value for maximumIterations.
	  * @param int The new maximumIterations for this parameter.
	  */
	
	public void setMaximumIterations(int maximumIterations) {
		this.maximumIterations = maximumIterations;
	}

	/**
	  * This method returns a int.
	  * @return int The migrationTax of the parameter.
	  */
	
	public int getMigrationTax() {
		return migrationTax;
	}

	/**
	  * This method set a new value for stopCondition.
	  * @param float The new stopCondition for this parameter.
	  */
	
	public void setMigration_tax(int migrationTax) {
		this.migrationTax = migrationTax;
	}
	
	/**
	 * This method generate a list of tests.
	 * @return ArrayList<Parameter>
	 */
	
	public static ArrayList<Parameter> generateLocalTests(){
		int[] number_iterations = {100, 1000, 10000};
		int[] size_of_population = {100, 500};
		int[] stopCondition = {1,5,10,15};
		int[] elitismRate = {5,10,15};
		int[] crossoverRate = {85,50,25};
		double[] mutationRate = {0.5,1.0,5.0};
		ArrayList<Parameter> parameters = new ArrayList<Parameter>();
		
		for(int i = 0; i < number_iterations.length; i++){
			int number = number_iterations[i];
			for(int j = 0; j < size_of_population.length; j++){
				int size = size_of_population[j];
				for(int z = 0; z < stopCondition.length; z++){
					int stop = stopCondition[z];
					for(int p = 0; p < elitismRate.length; p++){
						int elitism = elitismRate[p];
						for(int v = 0; v < crossoverRate.length; v++){
							int cross = crossoverRate[v];
							for(int b = 0; b < mutationRate.length; b++){
								float mutation = (float) mutationRate[b];
								Parameter p2 = new Parameter(ModelPopulationType.LM, number, stop, size, elitism, cross, CrossoverType.P, mutation, 10);
								parameters.add(p2);
							}
						}
					}
				}
			}
		}
		
		return parameters;
	}
	
	/**
	 * This method generate a list of tests.
	 * @return ArrayList<Parameter>
	 */
	
	public static ArrayList<Parameter> generateRegionalTests(){
		int[] number_iterations = {100, 1000, 10000};
		int[] size_of_population = {100, 500};
		int[] stopCondition = {1,5,10,15};
		int[] elitismRate = {5,10,15};
		int[] crossoverRate = {85,50,25};
		double[] mutationRate = {0.5,1.0,5.0};
		int[] migrationRate = {10,20,40};
		int[] migrationTax = {10,20,40};
		int[] numberOfPopulations = {2,4,8,16};
		
		ArrayList<Parameter> parameters = new ArrayList<Parameter>();
		
		for(int i = 0; i < number_iterations.length; i++){
			int number = number_iterations[i];
			for(int j = 0; j < size_of_population.length; j++){
				int size = size_of_population[j];
				for(int z = 0; z < stopCondition.length; z++){
					int stop = stopCondition[z];
					for(int p = 0; p < elitismRate.length; p++){
						int elitism = elitismRate[p];
						for(int v = 0; v < crossoverRate.length; v++){
							int cross = crossoverRate[v];
							for(int b = 0; b < mutationRate.length; b++){
								float mutation = (float) mutationRate[b];
								for(int a = 0; a < migrationRate.length; a++){
									int migrationR = migrationRate[a];
									for(int q = 0; q < migrationTax.length;q++){
										int migrationT = migrationTax[q];
										for(int l = 0; l < numberOfPopulations.length; l++){
											int numberP = numberOfPopulations[l];
											Parameter p2 = new Parameter(ModelPopulationType.RM, number, stop, size, elitism, cross, CrossoverType.P, mutation, 10,
													migrationR, migrationT, numberP);
											parameters.add(p2);
										}
									}
								}
								
							}
						}
					}
				}
			}
		}
		
		return parameters;
	}
	
	
}
