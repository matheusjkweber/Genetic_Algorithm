package Genetic_Algorithm;

import java.util.ArrayList;
import java.util.Random;

/**
* The Genetic Algorithm class implements the Genetic Algorithm and tests.
*
* @author  Matheus Jose Krumenauer Weber
* @version 1.0
* @since   2017-01-07
*/

public class Genetic_Algorithm {
	private static ArrayList<Parameter> parameters;
	
	public static void main(String [] args){
		// Initialize random to use in future.
		Random random = new Random();
		
		// Initialize the parameters list.
		parameters = new ArrayList<Parameter>();
		
		Parameter p = new Parameter(ModelPopulationType.GM, 100,
				10, SelectionType.ARM, 85,
				CrossoverType.MP, (float) 0.5, 10,
				10);
		parameters.add(p);
		
		// For each parameter in parameters list will run the Genetic Algorithm and return the result and time elapsed for it.
		for(int i = 0; i < parameters.size(); i++){
			// Used to calculate the time that algorithm will run.
			long startTime = System.currentTimeMillis(); 
			Population population;
			// Check the model of population.
			if(p.getModelPopulation() == ModelPopulationType.GM){
				population = new Global_Population(p.getSizeOfPopulation(), 3);
				population.generatePopulation();
			}else if(p.getModelPopulation() == ModelPopulationType.LM){
				population = new Local_Population(p.getSizeOfPopulation());
				population.generatePopulation();
			}else if(p.getModelPopulation() == ModelPopulationType.RM){
				population = new Regional_Population(200, random.nextInt(20) + 1, random.nextInt(30) + 1, random.nextInt(4) + 1);
				population.generatePopulation();
			}
			
			// Calculate and return the time that the algorithm used.
			long endTime = System.currentTimeMillis() - startTime;
			System.out.println(endTime);
		}
		
		
	}
}
