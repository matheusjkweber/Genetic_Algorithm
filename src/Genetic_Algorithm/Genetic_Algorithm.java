package Genetic_Algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
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
	public static Config config;
	
	public Genetic_Algorithm(Shell gui){
		this.gui = gui;
		this.max_tests = 0;
		this.finish = false;
	}
	
	public void train(int max_tests){
		isCalculus = false;
		parameters = Parameter.generateLocalTests();
		//parameters1 = Parameter.generateRegionalTests();
		
		
		//int count = parameters.size() + parameters1.size();
		
		use_max = true;
		
		if(max_tests == 0){
			//max_tests = count;
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
			
			if(this.best_regional != null){
				if(this.best_local.get(0).getFitness() < this.best_regional.get(0).getFitness()){
					bests = this.best_local;
					System.out.println(this.best_local.get(0).getFitness()+" "+this.best_regional.get(0).getFitness());

				}else{
					bests = this.best_regional;
					System.out.println(this.best_local.get(0).getFitness()+" "+this.best_regional.get(0).getFitness());

				}
			}else{
				bests = this.best_local;
				System.out.println(this.best_local.get(0).getFitness()+" - Nao entrou no regional.");
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
	
	private static void trainFromSheet(){
		Genetic_Algorithm.config = new Config();
		ArrayList<Chromosome> expectedChromosomes = getChromosomesFromEntry();
		ArrayList<Parameter> parameters = Parameter.generateRegionalTests(expectedChromosomes);
		
		System.out.println(parameters.size());
		Population population;
		
		boolean first_regional = false;
		boolean first_local = false;
		
		for(int i = 34225; i < parameters.size(); i++){
			String separator = ",";
			StringBuilder sb = new StringBuilder();
			FileWriter pw = null;
			System.out.println(i+" of "+parameters.size());
			
			try {
				pw = new FileWriter("tests.csv", true);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Parameter p = parameters.get(i);
			long startTime = System.currentTimeMillis(); 
			
			if(parameters.get(i).getModelPopulation() == ModelPopulationType.RM){
				if(first_regional == true){
					sb.append("Number"+separator+"Number of iterations"+separator+"Stop Condition"+separator+"Size of Population"+separator+"Elitism Rate"+separator+"Crossover Rate"+separator+"Crossover Type"+separator+"Mutation Rate"+separator+"Mutation Step"+separator+"Migration Rate"+separator+"Migration Tax"+separator+"Number of Population"+separator+"D"+separator+"Hg"+separator+"L"+separator+"Q"+separator+"Final Fitness"+separator+"Expected Fitness"+separator+"Improvement"+separator+"Time"+"\n");
					first_regional = false;
				}
				population = new Regional_Population(parameters.get(i));
			}else{
				if(first_local == true){
					sb.append("Number"+separator+"Number of iterations"+separator+"Stop Condition"+separator+"Size of Population"+separator+"Elitism Rate"+separator+"Crossover Rate"+separator+"Crossover Type"+separator+"Mutation Rate"+separator+"Mutation Step"+separator+"D"+separator+"Hg"+separator+"L"+separator+"Q"+separator+"Final Fitness"+separator+"Expected Fitness"+"Improvement"+"Time"+"\n");
					first_local = false;
				}
				population = new Local_Population(parameters.get(i));
			}
			
			ArrayList<Chromosome> answers = population.train();
			Chromosome selected = answers.get(answers.size()-1);
			
			double improvement = parameters.get(i).getExpectedFitness() - selected.getFitness();
			
			double improvementPercent = (improvement * 100) / parameters.get(i).getExpectedFitness();
			
			if(parameters.get(i).getModelPopulation() == ModelPopulationType.RM){
				sb.append(i+separator+p.getMaximumIterations()+""+separator+""+p.getStopCondition()+""+separator+""+p.getSizeOfPopulation()+""+separator+""+p.getElitismRate()+""+separator+""
						+p.getCrossoverRate()+""+separator+"P"+separator+""+p.getMutationRate()+""+separator+"10"+separator+""+p.getMigrationRate()+""+separator+""+p.getMigrationTax()+""+separator+""+p.getNumberOfPopulations()+""+separator+""
						+selected.getGenes().get(0).getValue()+""+separator+""+selected.getGenes().get(1).getValue()+
						""+separator+""+selected.getGenes().get(2).getValue()+""+separator+""+selected.getGenes().get(3).getValue()+""+separator+""+selected.getFitness()+separator+p.getExpectedFitness()+separator+improvementPercent+"%");
			}else{
				sb.append(i+separator+p.getMaximumIterations()+""+separator+""+p.getStopCondition()+""+separator+""+p.getSizeOfPopulation()+""+separator+""+p.getElitismRate()+""+separator+""
						+p.getCrossoverRate()+""+separator+"P"+separator+""+p.getMutationRate()+""+separator+"10"+separator+""+selected.getGenes().get(0).getValue()+""+separator+""+selected.getGenes().get(1).getValue()+
						""+separator+""+selected.getGenes().get(2).getValue()+""+separator+""+selected.getGenes().get(3).getValue()+""+separator+""+selected.getFitness()+separator+p.getExpectedFitness()+separator+improvementPercent+"%");
						
			}
			
			long endTime = System.currentTimeMillis() - startTime;
			
			p.setTime(endTime);
			sb.append(separator+endTime+"\n");
			
			try {
				pw.write(sb.toString());
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(improvementPercent+"%");
			//System.out.println(selected);
		}
		
		
	}
	
	private static ArrayList<Chromosome> getChromosomesFromEntry(){
		String csvFile = "arquivo_entrada.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        ArrayList<Chromosome> results = new ArrayList<Chromosome>();
        try {
        	 br = new BufferedReader(new FileReader(csvFile));
        	 
        	 // Jump on line.
        	 br.readLine();
        	 
        	 // Read the lines.
             while ((line = br.readLine()) != null) {

				 // use comma as separator
				 String[] values = line.split(cvsSplitBy);
				 
				 // Convert values.
				 float height = Float.parseFloat(values[0]);
				 float lenght = Float.parseFloat(values[1]);
				 float flow = Float.parseFloat(values[2]);
				 float final_fitness = Float.parseFloat(values[3]);
				 
				 Gene d = new Gene("Diameter", 0, true, MutationType.SBS, 100, 1500, 0);
				 Gene hg = new Gene("Hg", height, true, MutationType.SBSD3, 1, 25, height);
				 Gene l = new Gene("Lenght", lenght, true, MutationType.SBS, 1, 1000, lenght);
				 Gene q = new Gene("Flow", flow, true, MutationType.SBS, 50, 2000, flow);
         		 
				 ArrayList<Gene> genes = new ArrayList<Gene>();
				 genes.add(d);
				 genes.add(q);
				 genes.add(hg);
				 genes.add(l);
				 
        		
                 Chromosome c = new Chromosome(genes);
                 
                 c.setFitness(final_fitness);
                 results.add(c);
            }
		}catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return results;
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
	
	/**
	 * Main used for tests.
	 * @param args
	 */
	public static void main(String[] args) {
		trainFromSheet();
	}
}
