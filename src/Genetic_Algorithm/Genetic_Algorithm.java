package Genetic_Algorithm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

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

public class Genetic_Algorithm implements Runnable{
	private Shell gui;
	ProgressAlert alert;
	private int max_tests;
	private boolean use_max;
	private ArrayList<Parameter> parameters;
	private ArrayList<Parameter> parameters1;
	private ArrayList<Parameter> best_local;
	private ArrayList<Parameter> best_regional;
	private ArrayList<Parameter> bests;
	private boolean finish;
	private Parameter best;
	private boolean isCalculus;
	private Chromosome answer;
	private ArrayList<Chromosome> answers;
	
	public Genetic_Algorithm(Shell gui){
		this.gui = gui;
		this.max_tests = 0;
		this.finish = false;
	}
	
	public void train(int max_tests){
		isCalculus = false;
		parameters = Parameter.generateLocalTests();
		parameters1 = Parameter.generateRegionalTests();
		
		
		int count = parameters.size() + parameters1.size();
		
		use_max = true;
		
		if(max_tests == 0){
			max_tests = count;
			use_max = false;
		}
		
		alert = new ProgressAlert(gui, max_tests);
		this.max_tests = max_tests;
		
		Thread new_train = new Thread(this);
		new_train.start();
		
		alert.showProgressBar();
	}
	
	public void calculate(Parameter p){
		isCalculus = true;
		best = p;
		Thread new_train = new Thread(this);
		new_train.start();
	}
	
	@Override
	public void run(){
		if(isCalculus == false){
			// Local tests.
			try {
				this.best_local = trainModel(this.parameters, 0);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			// Regional tests.
			try {
				this.best_regional = trainModel(this.parameters1, 1);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(this.best_local.get(0).getFitness() < this.best_regional.get(0).getFitness()){
				bests = this.best_local;
				System.out.println(this.best_local.get(0).getFitness()+" "+this.best_regional.get(0).getFitness());

			}else{
				bests = this.best_regional;
				System.out.println(this.best_local.get(0).getFitness()+" "+this.best_regional.get(0).getFitness());

			}
			
			// Save the bests configuration.
			FileOutputStream fos;
			try {
				fos = new FileOutputStream("config.tmp");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(bests);
				oos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.finish = true;
		}else{
			answers = calculateChromosome(this.best);
			this.answer = answers.get(answers.size()-1);
			System.out.println(this.answer);
			this.finish = true;
		}
		
	}
	
	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}
	
	private ArrayList<Chromosome> calculateChromosome(Parameter parameter){
		Population population;
		
		
		if(parameter.getModelPopulation() == ModelPopulationType.LM){
			population = new Local_Population(parameter);
		}else{
			population = new Regional_Population(parameter);
		}
		
		return population.train();		
	}

	private ArrayList<Parameter> trainModel(ArrayList<Parameter> parameters, int model) throws FileNotFoundException{
		String separator = ",";
		StringBuilder sb = new StringBuilder();
		PrintWriter pw;
		
		if(model == 0){
			pw = new PrintWriter(new File("local.csv"));
		}else{
			pw = new PrintWriter(new File("regional.csv"));
		}
		
		if(this.max_tests > parameters.size() || use_max == false){
			this.max_tests = parameters.size();
		}		
		
		ArrayList<Parameter> bests = new ArrayList<Parameter>();
		
		for(int i = 0; i < this.max_tests; i++){
			alert.setProgress(alert.getProgress()+1);
			Parameter p = parameters.get(i);
			// Used to calculate the time that algorithm will run.
			long startTime = System.currentTimeMillis(); 
			Population population;
			// Check the model of population.
			if(p.getModelPopulation() == ModelPopulationType.GM){
				population = new Global_Population(p.getSizeOfPopulation(), 3, p.getElitismRate(), p);
				// GM is not in use yet.
			}else if(p.getModelPopulation() == ModelPopulationType.LM){
				if(i == 0){
					sb.append("Number of iterations"+separator+"Stop Condition"+separator+"Size of Population"+separator+"Elitism Rate"+separator+"Crossover Rate"+separator+"Crossover Type"+separator+"Mutation Rate"+separator+"Mutation Step"+separator+"D"+separator+"Hg"+separator+"L"+separator+"Q"+separator+"Final Fitness"+separator+"Time"+separator+"Verified\n");
				}
				population = new Local_Population(p);
				ArrayList<Chromosome> answers = population.train();
				Chromosome selected = answers.get(answers.size()-1);
				
				sb.append(p.getMaximumIterations()+""+separator+""+p.getStopCondition()+""+separator+""+p.getSizeOfPopulation()+""+separator+""+p.getElitismRate()+""+separator+""
				+p.getCrossoverRate()+""+separator+"P"+separator+""+p.getMutationRate()+""+separator+"10"+separator+""+selected.getGenes().get(0).getValue()+""+separator+""+selected.getGenes().get(1).getValue()+
				""+separator+""+selected.getGenes().get(2).getValue()+""+separator+""+selected.getGenes().get(3).getValue()+""+separator+""+selected.getFitness());
				p.setFitness(selected.getFitness());
				p.setFinalGene(selected);
				
			}else if(p.getModelPopulation() == ModelPopulationType.RM){
				if(i == 0){
					sb.append("Number of iterations"+separator+"Stop Condition"+separator+"Size of Population"+separator+"Elitism Rate"+separator+"Crossover Rate"+separator+"Crossover Type"+separator+"Mutation Rate"+separator+"Mutation Step"
							+separator+"Migration Rate"+separator+"Migration Tax"+separator+"Number of Population"+separator+"D"+separator+"Hg"+separator+"L"+separator+"Q"+separator+"Final Fitness"+separator+"Time"+separator+"Verified\n");
				}
				int maximumIterations = p.getMaximumIterations();
				
				population = new Regional_Population(p);
				ArrayList<Chromosome> answers = population.train();
				Chromosome selected = answers.get(answers.size()-1);
				
				sb.append(maximumIterations+""+separator+""+p.getStopCondition()+""+separator+""+p.getSizeOfPopulation()+""+separator+""+p.getElitismRate()+""+separator+""
						+p.getCrossoverRate()+""+separator+"P"+separator+""+p.getMutationRate()+""+separator+"10"+separator+""+p.getMigrationRate()+""+separator+""+p.getMigrationTax()+""+separator+""+p.getNumberOfPopulations()+""+separator+""
						+selected.getGenes().get(0).getValue()+""+separator+""+selected.getGenes().get(1).getValue()+
						""+separator+""+selected.getGenes().get(2).getValue()+""+separator+""+selected.getGenes().get(3).getValue()+""+separator+""+selected.getFitness());
				p.setFitness(selected.getFitness());
				p.setMaximumIterations(maximumIterations);
				p.setFinalGene(selected);
			}
			
			bests.add(p);
			
			// Calculate and return the time that the algorithm used.
			long endTime = System.currentTimeMillis() - startTime;
			
			p.setTime(endTime);
			//System.out.println(i+" - "+endTime);
			sb.append(separator+endTime+"\n");
		}
		pw.write(sb.toString());
        pw.close();
        
        // Order the list.
        Collections.sort(bests);

        if(bests.size() > 100){
        	for(int z = bests.size() - 1; z >= 100; z--){
        		bests.remove(z);
        	}
        }
        
        return bests;
	}
	
	public ArrayList<Parameter> getBests(){
		return bests;
	}
	
	public ArrayList<Parameter> getLocal(){
		return best_local;
	}
	
	public ArrayList<Parameter> getRegional(){
		return best_regional;
	}

	public Chromosome getAnswer() {
		return answer;
	}

	public void setAnswer(Chromosome answer) {
		this.answer = answer;
	}

	public ArrayList<Chromosome> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<Chromosome> answers) {
		this.answers = answers;
	}
	
}
