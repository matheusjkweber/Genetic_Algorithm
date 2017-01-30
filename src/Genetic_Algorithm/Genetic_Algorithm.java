package Genetic_Algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
* The Genetic Algorithm class implements the Genetic Algorithm and tests.
*
* @author  Matheus Jose Krumenauer Weber
* @version 1.0
* @since   2017-01-07
*/

/**
 * @TODO:
 * - Padronizar nomes de metodos e variaveis.
 * - Comentarios.
 * - Trocar testes para testes com fors encadeados.
 */

public class Genetic_Algorithm {
	private static ArrayList<Parameter> parameters;
	
	public static void main(String [] args) throws FileNotFoundException{
		// Initialize random to use in future.
		Random random = new Random();
		
		// Initialize the parameters list.
		
		PrintWriter pw;
		pw = new PrintWriter(new File("test.csv"));
		
		String separator = ";";
		
		StringBuilder sb = new StringBuilder();
		
		parameters = Parameter.generateRegionalTests();
		
		// For each parameter in parameters list will run the Genetic Algorithm and return the result and time elapsed for it.
		for(int i = 0; i < parameters.size(); i++){
			Parameter p = parameters.get(i);
			// Used to calculate the time that algorithm will run.
			long startTime = System.currentTimeMillis(); 
			Population population;
			// Check the model of population.
			if(p.getModelPopulation() == ModelPopulationType.GM){
				population = new Global_Population(p.getSizeOfPopulation(), 3, p.getElitismRate(), p);
				
			}else if(p.getModelPopulation() == ModelPopulationType.LM){
				if(i == 0){
					sb.append("Number of iterations"+separator+"Stop Condition"+separator+"Size of Population"+separator+"Elitism Rate"+separator+"Crossover Rate"+separator+"Crossover Type"+separator+"Mutation Rate"+separator+"Mutation Step"+separator+"D"+separator+"Hg"+separator+"L"+separator+"Q"+separator+"Final Fitness"+separator+"Time"+separator+"Verified\n");
				}
				population = new Local_Population(p.getSizeOfPopulation(), p.getElitismRate(), p);
				Chromosome selected = population.train();
				
				sb.append(p.getMaximumIterations()+""+separator+""+p.getStopCondition()+""+separator+""+p.getSizeOfPopulation()+""+separator+""+p.getElitismRate()+""+separator+""
				+p.getCrossoverRate()+""+separator+"P"+separator+""+p.getMutationRate()+""+separator+"10"+separator+""+selected.getGenes().get(0).getValue()+""+separator+""+selected.getGenes().get(1).getValue()+
				""+separator+""+selected.getGenes().get(2).getValue()+""+separator+""+selected.getGenes().get(3).getValue()+""+separator+""+selected.getFitness());
			}else if(p.getModelPopulation() == ModelPopulationType.RM){
				if(i == 0){
					sb.append("Number of iterations"+separator+"Stop Condition"+separator+"Size of Population"+separator+"Elitism Rate"+separator+"Crossover Rate"+separator+"Crossover Type"+separator+"Mutation Rate"+separator+"Mutation Step"
							+separator+"Migration Rate"+separator+"Migration Tax"+separator+"Number of Population"+separator+"D"+separator+"Hg"+separator+"L"+separator+"Q"+separator+"Final Fitness"+separator+"Time"+separator+"Verified\n");
				}
				
				population = new Regional_Population(p.getSizeOfPopulation(), p.getMigration_rate(), p.getMigration_tax(), p.getNumber_of_populations(), p.getElitismRate(), p);
				Chromosome selected = population.train();
				sb.append(p.getMaximumIterations()+""+separator+""+p.getStopCondition()+""+separator+""+p.getSizeOfPopulation()+""+separator+""+p.getElitismRate()+""+separator+""
						+p.getCrossoverRate()+""+separator+"P"+separator+""+p.getMutationRate()+""+separator+"10"+separator+""
						+separator+""+p.getMigration_rate()+""+separator+""+p.getMigration_tax()+""+separator+""+p.getNumber_of_populations()+""+separator+""
						+selected.getGenes().get(0).getValue()+""+separator+""+selected.getGenes().get(1).getValue()+
						""+separator+""+selected.getGenes().get(2).getValue()+""+separator+""+selected.getGenes().get(3).getValue()+""+separator+""+selected.getFitness());
					
			}
			
			// Calculate and return the time that the algorithm used.
			long endTime = System.currentTimeMillis() - startTime;
			System.out.println(i+" - "+endTime);
			sb.append(","+endTime+"\n");
		}
		
		pw.write(sb.toString());
        pw.close();
	}
}
