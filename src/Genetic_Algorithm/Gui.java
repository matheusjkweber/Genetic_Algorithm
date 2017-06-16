package Genetic_Algorithm;
import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Button;
import org.swtchart.Chart;
import org.swtchart.IAxisSet;
import org.swtchart.IBarSeries;
import org.swtchart.ISeries;
import org.swtchart.ISeries.SeriesType;
import org.swtchart.ISeriesSet;
import org.swtchart.ITitle;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;

public class Gui {

	protected Shell shell;
	private Table table;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Table table_1;
	private TabFolder tabFolder;
	private Composite composite;
	private Composite composite_1;
	private Text text_4;
	private Table table_2;
	private Table table_3;
	private Composite composite_2;
	Genetic_Algorithm genetic;
	Group grpTempoXResultado;
	Group group_1;
	Group resultsGroup;
	Parameter best;
	Group grpParmetrosSetados;
	Group grpResultados;
	Group grpConvergncia;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Text text_8;
	private Text text_9;
	private Text text_10;
	private Text text_11;
	private Text text_12;
	private Text text_13;
	private Text text_15;
	private Text text_16;
	private Config config;
	/**
	 * Launch the application.
	 * @param args
	 */
	
	//TODO: Uncoment to go back to normal program.
	
	/*public static void main(String[] args) {
		try {
			Gui window = new Gui();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Open the window.
	 */
	public void open() {
		
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell(SWT.SHELL_TRIM & (~SWT.RESIZE));
		shell.setSize(1400, 800);
		shell.setText("");
		
		createFirstTab();
		
		createSecondTab();
		
		createThirdTab();
	}
	
	/**
	 * Create the "Calculo de Valores" tab.
	 */
	protected void createFirstTab(){
		tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setBounds(0, 0, 1382, 753);
		
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("Cálculo de Valores");
		
		composite = new Composite(tabFolder, SWT.NONE);
		tabItem.setControl(composite);
		
		
		
		buildParametros();
		
		buildParametrosExternos();
		
		buildCalcular();
	}
	
	/**
	 * Build "Parametros Setados" group.
	 */
	protected void buildParametros(){
		grpParmetrosSetados = new Group(composite, SWT.NONE);
		grpParmetrosSetados.setText("Par\u00E2metros Setados Para o Algoritmo");
		grpParmetrosSetados.setBounds(0, 518, 680, 202);
		
		table = new Table(grpParmetrosSetados, SWT.FULL_SELECTION);
		table.setBounds(10, 25, 660, 167);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnParmetro = new TableColumn(table, SWT.NONE);
		tblclmnParmetro.setWidth(437);
		tblclmnParmetro.setText("Par\u00E2metro");
		
		TableColumn tblclmnValor = new TableColumn(table, SWT.NONE);
		tblclmnValor.setWidth(200);
		tblclmnValor.setText("Valor");
		
		FileInputStream fis;
		try {
			fis = new FileInputStream("config.tmp");
			ObjectInputStream ois = new ObjectInputStream(fis);
			ArrayList<Parameter> bests = (ArrayList<Parameter>) ois.readObject();
			ois.close();
			
			best = bests.get(0);
			if(best.getModelPopulation() == ModelPopulationType.RM){
				TableItem tableItem = new TableItem(table, SWT.NONE);
				tableItem.setText(new String[]{"Modelo da população", "Regional"});
			}else{
				TableItem tableItem = new TableItem(table, SWT.NONE);
				tableItem.setText(new String[]{"Modelo da população", "Local"});
			}
			
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(new String[]{"Número máximo de iterações", Integer.toString(best.getMaximumIterations())});
			
			TableItem tableItem2 = new TableItem(table, SWT.NONE);
			tableItem2.setText(new String[]{"Condição de parada(%)", Double.toString(best.getStopCondition())});
			
			TableItem tableItem3 = new TableItem(table, SWT.NONE);
			tableItem3.setText(new String[]{"Tamanho da população", Integer.toString(best.getSizeOfPopulation())});
			
			TableItem tableItem4 = new TableItem(table, SWT.NONE);
			tableItem4.setText(new String[]{"Chance de Elitismo(%)", Double.toString(best.getElitismRate())});
			
			TableItem tableItem5 = new TableItem(table, SWT.NONE);
			tableItem5.setText(new String[]{"Chance de Crossover(%)", Double.toString(best.getCrossoverRate())});
			
			TableItem tableItem6 = new TableItem(table, SWT.NONE);
			tableItem6.setText(new String[]{"Chance de Mutação(%)", Double.toString(best.getMutationRate())});
			
			if(best.getModelPopulation() == ModelPopulationType.RM){
				TableItem tableItem7 = new TableItem(table, SWT.NONE);
				tableItem7.setText(new String[]{"Chance de Migração(%)", Double.toString(best.getMigrationRate())});
				
				TableItem tableItem8 = new TableItem(table, SWT.NONE);
				tableItem8.setText(new String[]{"Taxa de Migração(%)", Double.toString(best.getMigrationTax())});
				
				TableItem tableItem9 = new TableItem(table, SWT.NONE);
				tableItem9.setText(new String[]{"Número de populações", Integer.toString(best.getNumberOfPopulations())});
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showError("Antes de começar, treine o algoritmo.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Build "Convergencia" group.
	 */
	protected void buildConvergencia(ArrayList<Chromosome> answers){
		Collections.reverse(answers);
		
		grpConvergncia = new Group(composite, SWT.NONE);
		grpConvergncia.setText("Converg\u00EAncia");
		grpConvergncia.setBounds(693, 0, 681, 360);
		
		Chart chart = new Chart(grpConvergncia, SWT.NONE);
		
		double[] ySeries = new double[answers.size()];
		double[] xSeries = new double[answers.size()];
		
		for(int i = 0; i < answers.size();i++){
			xSeries[i] = i;
			ySeries[i] = answers.get(i).getFitness();
		}
		
		ISeriesSet seriesSet = chart.getSeriesSet();
		ISeries series = seriesSet.createSeries(SeriesType.LINE, "Valor");
		series.setYSeries(ySeries);
		series.setXSeries(xSeries);
		IAxisSet axisSet = chart.getAxisSet();
		axisSet.adjustRange();
		chart.setBounds(15,20,640,320);
		
		ITitle graphTitle = chart.getTitle();
		ITitle xAxisTitle = chart.getAxisSet().getXAxis(0).getTitle();
		ITitle yAxisTitle = chart.getAxisSet().getYAxis(0).getTitle();
		xAxisTitle.setText("Iteração");
		yAxisTitle.setText("R$");
		
		graphTitle.setText("Convergência para Resposta Final");
	}
	
	/**
	 * Build "Resultados" group.
	 */
	protected void buildResultados(ArrayList<Chromosome> answers){
		grpResultados = new Group(composite, SWT.NONE);
		grpResultados.setText("Resultados");
		//grpConvergncia.setBounds(693, 0, 681, 360);
		grpResultados.setBounds(693, 366, 681, 360);
		
		table_1 = new Table(grpResultados, SWT.FULL_SELECTION);
		table_1.setBounds(10, 24, 671, 320);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		TableColumn tblclmnPosio = new TableColumn(table_1, SWT.NONE);
		tblclmnPosio.setWidth(100);
		tblclmnPosio.setText("Posi\u00E7\u00E3o");
		
		TableColumn tblclmnDimetro = new TableColumn(table_1, SWT.NONE);
		tblclmnDimetro.setWidth(100);
		tblclmnDimetro.setText("Di\u00E2metro");
		
		TableColumn tblclmnAltura = new TableColumn(table_1, SWT.NONE);
		tblclmnAltura.setWidth(100);
		tblclmnAltura.setText("Altura");
		
		TableColumn tblclmnLarg = new TableColumn(table_1, SWT.NONE);
		tblclmnLarg.setWidth(100);
		tblclmnLarg.setText("Comprimento");
		
		TableColumn tblclmnVazo = new TableColumn(table_1, SWT.NONE);
		tblclmnVazo.setWidth(100);
		tblclmnVazo.setText("Vaz\u00E3o");
		
		TableColumn tblclmnValorr = new TableColumn(table_1, SWT.NONE);
		tblclmnValorr.setWidth(140);
		tblclmnValorr.setText("Valor(R$)");
		
		Collections.sort(answers);
		
		for(int i = 0; i < answers.size(); i++){
			Chromosome c = answers.get(i);
			
		    TableItem tableItem = new TableItem(table_1, SWT.NONE);
			tableItem.setText(new String[]{Integer.toString(i+1), Float.toString(c.getGenes().get(0).getValue()),
					Float.toString(c.getGenes().get(1).getValue()), Float.toString(c.getGenes().get(2).getValue()),
					Float.toString(c.getGenes().get(3).getValue()), Float.toString(c.getFitness())});
		}
	}
	
	/**
	 * Build "Calcular Valores" group.
	 */
	protected void buildCalcular(){
		Group grpCalcularValores = new Group(composite, SWT.NONE);
		grpCalcularValores.setText("Calcular Valores");
		grpCalcularValores.setBounds(0, 0, 680, 152);
		
		Label lblDimetro = new Label(grpCalcularValores, SWT.NONE);
		lblDimetro.setBounds(10, 36, 86, 26);
		lblDimetro.setText("Di\u00E2metro(m)");
		
		text = new Text(grpCalcularValores, SWT.BORDER);
		text.setBounds(102, 33, 149, 26);
		text.setText("0");
		
		Label lblAlturaGeomtrica = new Label(grpCalcularValores, SWT.NONE);
		lblAlturaGeomtrica.setBounds(10, 78, 149, 20);
		lblAlturaGeomtrica.setText("Altura Geom\u00E9trica(m)");
		
		text_1 = new Text(grpCalcularValores, SWT.BORDER);
		text_1.setBounds(165, 78, 86, 26);
		text_1.setText("0");
		
		Label lblVazo = new Label(grpCalcularValores, SWT.NONE);
		lblVazo.setBounds(293, 36, 115, 20);
		lblVazo.setText("Vaz\u00E3o(m3^(s-1))");
		
		Label lblLargura = new Label(grpCalcularValores, SWT.NONE);
		lblLargura.setBounds(293, 78, 115, 20);
		lblLargura.setText("Comprimento(m)");
		
		text_2 = new Text(grpCalcularValores, SWT.BORDER);
		text_2.setBounds(414, 33, 115, 26);
		text_2.setText("0");

		text_3 = new Text(grpCalcularValores, SWT.BORDER);
		text_3.setBounds(414, 78, 115, 26);
		text_3.setText("0");

		Button btnCalcular = new Button(grpCalcularValores, SWT.NONE);
		btnCalcular.setBounds(580, 54, 90, 30);
		btnCalcular.setText("Calcular");
		
		Label lblObsDeixe = new Label(grpCalcularValores, SWT.NONE);
		lblObsDeixe.setBounds(10, 122, 670, 20);
		lblObsDeixe.setText("Obs.: Deixe 0 para o valor(es) que voc\u00EA deseja descobrir.");
		
		btnCalcular.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try{
					genetic = new Genetic_Algorithm(shell);
					float diameter = Float.parseFloat(text.getText());
					float flow = Float.parseFloat(text_2.getText());
					float geometricHeight = Float.parseFloat(text_1.getText());
					float length = Float.parseFloat(text_3.getText());
					
					float[] fixed_genes = {diameter, flow, geometricHeight, length};
					best.setFixed_genes(fixed_genes);
					best.setMaximumIterations(100000);
					best.setStopCondition(95);
					genetic.calculate(best);
					
					while(genetic.isFinish() == false){
						Thread.sleep(100);
					}
					
					ArrayList<Chromosome> answers = genetic.getAnswers();
					
					if(grpResultados != null){
						grpResultados.dispose();
					}
					
					buildResultados(answers);
					
					if(grpConvergncia != null){
						grpConvergncia.dispose();
					}
					buildConvergencia(answers);
										
				}catch(NumberFormatException e){
					showError("Por favor digite números válidos");
					
					text.setText("0");
					text_1.setText("0");
					text_2.setText("0");
					text_3.setText("0");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create "Treino de Algoritmo" tab.
	 */
	protected void createSecondTab(){
		TabItem tabItem_1 = new TabItem(tabFolder, SWT.NONE);
		tabItem_1.setText("Treinar Algoritmo");
		
		composite_1 = new Composite(tabFolder, SWT.NONE);
		tabItem_1.setControl(composite_1);
		
		buildTreinar();
		
		group_1 = new Group(composite_1, SWT.NONE);
		group_1.setText("Moda");
		group_1.setBounds(0, 126, 680, 202);
		
		resultsGroup = new Group(composite_1, SWT.NONE);
		resultsGroup.setText("Resultados");
		resultsGroup.setBounds(0, 366, 1374, 354);
		
		grpTempoXResultado = new Group(composite_1, SWT.NONE);
		grpTempoXResultado.setText("Tempo x Resultado");
		grpTempoXResultado.setBounds(686, 10, 678, 318);
		
		
	}
	
	/**
	 * Create "Treinar" group.
	 */
	
	protected void buildTreinar(){
		Group group = new Group(composite_1, SWT.NONE);
		group.setText("Treinar");
		group.setBounds(0, 0, 680, 120);
		
		Label label = new Label(group, SWT.NONE);
		label.setText("Número máximo de testes:");
		label.setBounds(10, 36, 189, 23);
		
		text_4 = new Text(group, SWT.BORDER);
		text_4.setBounds(205, 33, 165, 26);
		
		Button button = new Button(group, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try{
					int number = Integer.parseInt(text_4.getText());
					genetic = new Genetic_Algorithm(shell);

					genetic.train(number);
					try{
						showMessage("Carregando resultados...");
						while(genetic.isFinish() == false){
							Thread.sleep(100);
						}
						
						if(genetic.getBests().get(0).getModelPopulation() == ModelPopulationType.LM){
							showMessage("Melhor modelo de população: Local Model\nTreino finalizado, atualizando tabelas...");
						}else{
							showMessage("Melhor modelo de população: Regional Model\nTreino finalizado, atualizando tabelas...");
						}
						
						grpParmetrosSetados.dispose();
						
						buildChart();
						buildModa();
						buildResultados1();
						buildParametros();
						// Reload tables and chart.
					}catch(InterruptedException a){
						showError("Error");
					}
				}catch(NumberFormatException e){
					if(text_4.getText().isEmpty()){
						genetic = new Genetic_Algorithm(shell);
						genetic.train(0);
						try{
							showMessage("Carregando resultados...");
							while(genetic.isFinish() == false){
								Thread.sleep(100);
							}
							
							if(genetic.getBests().get(0).getModelPopulation() == ModelPopulationType.LM){
								showMessage("Melhor modelo de população: Local Model\nTreino finalizado, atualizando tabelas...");
							}else{
								showMessage("Melhor modelo de população: Regional Model\nTreino finalizado, atualizando tabelas...");
							}
							
							grpParmetrosSetados.dispose();
							
							buildChart();
							buildModa();
							buildResultados1();
							buildParametros();
							// Reload tables and chart.
						}catch(InterruptedException a){
							showError("Error");
						}
					}else{
						showError("Por favor digite um número válido");
						text_4.setText("");
					}
					
				}
			}
		});
		button.setText("Treinar");
		button.setBounds(580, 31, 90, 30);
		
		Label lblObsTempoMximo = new Label(group, SWT.NONE);
		lblObsTempoMximo.setBounds(10, 65, 660, 44);
		lblObsTempoMximo.setText("Obs.: Número máximo de testes por modelo, deixe vazio para sem limites,\npor\u00E9m pode levar horas para finalizar.");
	}
	
	protected void buildParametrosExternos(){
		FileInputStream fis;
		try {
			fis = new FileInputStream("configParam.tmp");
			ObjectInputStream ois = new ObjectInputStream(fis);
			config = (Config) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			config = new Config();
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			config = new Config();
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			config = new Config();
			e.printStackTrace();
		}
		
		Genetic_Algorithm.config = config;
		
		Group group = new Group(composite, SWT.NONE);
		group.setText("Parâmetros Externos");
		group.setBounds(0, 158, 680, 354);
		
		Label label = new Label(group, SWT.NONE);
		label.setText("Custo Tarifa Verde");
		label.setBounds(10, 36, 128, 38);
		
		text_5 = new Text(group, SWT.BORDER);
		text_5.setText(Float.toString(config.getP1()));
		text_5.setBounds(144, 33, 134, 26);
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setText("Rendimento");
		label_1.setBounds(10, 78, 128, 20);
		
		text_6 = new Text(group, SWT.BORDER);
		text_6.setText(Float.toString(config.getRend()));
		text_6.setBounds(144, 78, 133, 26);
		
		Label label_2 = new Label(group, SWT.NONE);
		label_2.setText("Custo Chapa de Aço");
		label_2.setBounds(402, 39, 134, 20);
		
		Label label_3 = new Label(group, SWT.NONE);
		label_3.setText("Nº Horas");
		label_3.setBounds(402, 78, 65, 20);
		
		text_7 = new Text(group, SWT.BORDER);
		text_7.setText(Float.toString(config.getP4()));
		text_7.setBounds(542, 36, 90, 26);
		
		text_8 = new Text(group, SWT.BORDER);
		text_8.setText(Float.toString(config.getN_h()));
		text_8.setBounds(481, 78, 151, 26);
		
		Label label_4 = new Label(group, SWT.NONE);
		label_4.setText("Custo de Demanda");
		label_4.setBounds(10, 123, 140, 38);
		
		text_9 = new Text(group, SWT.BORDER);
		text_9.setText(Float.toString(config.getP2()));
		text_9.setBounds(160, 120, 117, 26);
		
		Label label_5 = new Label(group, SWT.NONE);
		label_5.setText("Taxa(%)");
		label_5.setBounds(402, 123, 65, 38);
		
		text_10 = new Text(group, SWT.BORDER);
		text_10.setText(Float.toString(config.getTax()));
		text_10.setBounds(481, 120, 151, 26);
		
		Label label_6 = new Label(group, SWT.NONE);
		label_6.setText("Custo Horário Res.");
		label_6.setBounds(10, 163, 128, 38);
		
		text_11 = new Text(group, SWT.BORDER);
		text_11.setText(Float.toString(config.getP3()));
		text_11.setBounds(160, 160, 117, 26);
		
		Label label_7 = new Label(group, SWT.NONE);
		label_7.setText("PIS");
		label_7.setBounds(402, 163, 65, 38);
		
		text_12 = new Text(group, SWT.BORDER);
		text_12.setText(Float.toString(config.getPIS()));
		text_12.setBounds(481, 160, 151, 26);
		
		Label label_8 = new Label(group, SWT.NONE);
		label_8.setText("COFINS");
		label_8.setBounds(10, 205, 128, 38);
		
		text_13 = new Text(group, SWT.BORDER);
		text_13.setText(Float.toString(config.getCOFINS()));
		text_13.setBounds(160, 202, 117, 26);
		
		Label label_10 = new Label(group, SWT.NONE);
		label_10.setText("ICMS");
		label_10.setBounds(402, 205, 58, 38);
		
		text_15 = new Text(group, SWT.BORDER);
		text_15.setText(Float.toString(config.getICMS()));
		text_15.setBounds(480, 202, 152, 26);
		
		Label label_11 = new Label(group, SWT.NONE);
		label_11.setText("Anos");
		label_11.setBounds(10, 245, 58, 38);
		
		text_16 = new Text(group, SWT.BORDER);
		text_16.setText(Integer.toString(config.getN()));
		text_16.setBounds(160, 242, 118, 26);
		
		Button button = new Button(group, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try{
					float p1 = Float.parseFloat(text_5.getText());
					float p4 = Float.parseFloat(text_7.getText());
					float rend = Float.parseFloat(text_6.getText());
					float n_h = Float.parseFloat(text_8.getText());
					
					float p2 = Float.parseFloat(text_9.getText());
					float taxa = Float.parseFloat(text_10.getText());
					float p3 = Float.parseFloat(text_11.getText());
					
					float pis = Float.parseFloat(text_12.getText());
					float cofins = Float.parseFloat(text_13.getText());
					float icms = Float.parseFloat(text_15.getText());
					int n = Integer.parseInt(text_16.getText());
					
					config = new Config(p1, p2, p3, p4, rend, n_h, n, taxa, pis, cofins, icms);
					Genetic_Algorithm.config = config;
					// Save the new configuration.
					FileOutputStream fos;
					try {
						fos = new FileOutputStream("configParam.tmp");
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						oos.writeObject(Genetic_Algorithm.config);
						oos.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}catch(NumberFormatException e){
					showError("Por favor digite números válidos");
					
					text_5.setText(Float.toString(config.getP1()));
					text_7.setText(Float.toString(config.getP4()));
					text_6.setText(Float.toString(config.getRend()));
					text_8.setText(Float.toString(config.getN_h()));
					
					text_9.setText(Float.toString(config.getP2()));
					text_10.setText(Float.toString(config.getTax()));
					text_15.setText(Float.toString(config.getICMS()));
					text_11.setText(Float.toString(config.getP3()));
					text_12.setText(Float.toString(config.getPIS()));
					
					text_13.setText(Float.toString(config.getCOFINS()));
					text_16.setText(Integer.toString(config.getN()));
				}
			}
		});
		button.setText("Alterar");
		button.setBounds(293, 314, 90, 30);
		
	}
	
	/**
	 * Create "Moda" group.
	 */
	
	protected void buildModa(){
		table_2 = new Table(group_1, SWT.FULL_SELECTION);
		table_2.setLinesVisible(true);
		table_2.setHeaderVisible(true);
		table_2.setBounds(10, 25, 660, 167);
		
		TableColumn tableColumn = new TableColumn(table_2, SWT.NONE);
		tableColumn.setWidth(246);
		tableColumn.setText("Configuração");
		
		TableColumn tableColumn_1 = new TableColumn(table_2, SWT.NONE);
		tableColumn_1.setWidth(200);
		tableColumn_1.setText("Valor");
		
		TableColumn tblclmnQuantidade = new TableColumn(table_2, SWT.NONE);
		tblclmnQuantidade.setWidth(188);
		tblclmnQuantidade.setText("Quantidade");
		
		if(genetic.getBests().get(0).getModelPopulation() == ModelPopulationType.LM){
			Map<Double,Integer> numberIterations = new HashMap<Double,Integer>();
			numberIterations.put((double) 100, 0);
			numberIterations.put((double) 1000, 0);
			numberIterations.put((double) 10000, 0);
		    
		    Map<Double,Integer> stopCondition = new HashMap<Double,Integer>();
		    stopCondition.put((double) 1, 0);
		    stopCondition.put((double) 5, 0);
		    stopCondition.put((double) 10, 0);
		    stopCondition.put((double) 15, 0);
		    
		    Map<Double,Integer> populationSize = new HashMap<Double,Integer>();
		    populationSize.put((double) 100, 0);
		    populationSize.put((double) 500, 0);
		    
		    Map<Double,Integer> elitismRate = new HashMap<Double,Integer>();
		    elitismRate.put((double) 5, 0);
		    elitismRate.put((double) 10, 0);
		    elitismRate.put((double) 15, 0);
		    
		    Map<Double,Integer> crossoverRate = new HashMap<Double,Integer>();
		    crossoverRate.put((double) 25.0, 0);
		    crossoverRate.put((double) 50.0, 0);
		    crossoverRate.put((double) 85.0, 0);
		    
		    Map<Double,Integer> mutationRate = new HashMap<Double,Integer>();
		    mutationRate.put((double) 0.5, 0);
		    mutationRate.put((double) 1, 0);
		    mutationRate.put((double) 5, 0);
			
		    for(int i = 0; i < genetic.getLocal().size();i++){
		    	Parameter p = genetic.getLocal().get(i);
		    	numberIterations.put((double) p.getMaximumIterations(), numberIterations.get((double) p.getMaximumIterations())+1);
		    	stopCondition.put((double) p.getStopCondition(), stopCondition.get((double) p.getStopCondition())+1);
		    	populationSize.put((double) p.getSizeOfPopulation(), populationSize.get((double) p.getSizeOfPopulation())+1);
		    	elitismRate.put((double) p.getElitismRate(), elitismRate.get((double) p.getElitismRate())+1);
		    	crossoverRate.put((double) p.getCrossoverRate(), crossoverRate.get((double) p.getCrossoverRate())+1);
		    	mutationRate.put((double) p.getMutationRate(), mutationRate.get((double) p.getMutationRate())+1);
			}
		    
		    TableItem tableItem = new TableItem(table_2, SWT.NONE);
		  
	    	if(numberIterations.get((double) 100) > numberIterations.get((double) 1000) && numberIterations.get((double) 100) > numberIterations.get((double) 10000)){
	    		tableItem.setText(new String[] {"Número máximo de iterações", "100", numberIterations.get((double) 100).toString()});
	    	}else if(numberIterations.get((double) 1000) > numberIterations.get((double) 10000)){
	    		tableItem.setText(new String[] {"Número máximo de iterações", "1000", numberIterations.get((double) 1000).toString()});
	    	}else{
	    		tableItem.setText(new String[] {"Número máximo de iterações", "10000", numberIterations.get((double) 10000).toString()});
	    	}
	    	
	    	TableItem tableItem1 = new TableItem(table_2, SWT.NONE);
	    	if(stopCondition.get((double) 1) > stopCondition.get((double) 5) && stopCondition.get((double) 1) > stopCondition.get((double) 10) && stopCondition.get((double) 1) > stopCondition.get((double) 15)){
	    		tableItem1.setText(new String[] {"Condição de Parada(%)", "1", stopCondition.get((double) 1).toString()});
	    	}else if(stopCondition.get((double) 5) > stopCondition.get((double) 10) && stopCondition.get((double) 5) > stopCondition.get((double) 15)){
	    		tableItem1.setText(new String[] {"Condição de Parada(%)", "5", stopCondition.get((double) 5).toString()});
	    	}else if(stopCondition.get((double) 10) > stopCondition.get((double) 15)){
	    		tableItem1.setText(new String[] {"Condição de Parada(%)", "10", stopCondition.get((double) 10).toString()});
	    	}else{
	    		tableItem1.setText(new String[] {"Condição de Parada(%)", "10", stopCondition.get((double) 15).toString()});
	    	}
	    	
	    	TableItem tableItem2 = new TableItem(table_2, SWT.NONE);

	    	if(populationSize.get((double) 100) > populationSize.get((double) 500)){
	    		tableItem2.setText(new String[] {"Tamanho da População", "100", populationSize.get((double) 100).toString()});
	    	}else{
	    		tableItem2.setText(new String[] {"Tamanho da População", "500", populationSize.get((double) 500).toString()});
	    	}
	    	
	    	TableItem tableItem3 = new TableItem(table_2, SWT.NONE);

	    	if(elitismRate.get((double) 5) > elitismRate.get((double) 10) && elitismRate.get((double) 5) > elitismRate.get((double) 15)){
	    		tableItem3.setText(new String[] {"Chance de Elitismo(%)", "5", elitismRate.get((double) 5).toString()});
	    	}else if(elitismRate.get((double) 10) > elitismRate.get((double) 15)){
	    		tableItem3.setText(new String[] {"Chance de Elitismo(%)", "10", elitismRate.get((double) 10).toString()});
	    	}else{
	    		tableItem3.setText(new String[] {"Chance de Elitismo(%)", "15", elitismRate.get((double) 15).toString()});
	    	}
	    	
	    	TableItem tableItem4 = new TableItem(table_2, SWT.NONE);

	    	if(crossoverRate.get((double) 25) > crossoverRate.get((double) 50) && crossoverRate.get((double) 25) > crossoverRate.get((double) 85)){
	    		tableItem4.setText(new String[] {"Chance de Crossover(%)", "25", crossoverRate.get((double) 25).toString()});
	    	}else if(crossoverRate.get((double) 50) > crossoverRate.get((double) 85)){
	    		tableItem4.setText(new String[] {"Chance de Crossover(%)", "50", crossoverRate.get((double) 50).toString()});
	    	}else{
	    		tableItem4.setText(new String[] {"Chance de Crossover(%)", "85", crossoverRate.get((double) 85).toString()});
	    	}
	    	
	    	TableItem tableItem5 = new TableItem(table_2, SWT.NONE);

	    	if(mutationRate.get((double) 0.5) > mutationRate.get((double) 1) && mutationRate.get((double) 0.5) > mutationRate.get((double) 5)){
	    		tableItem5.setText(new String[] {"Chance de Mutação(%)", "0.5", mutationRate.get((double) 0.5).toString()});
	    	}else if(mutationRate.get((double) 1) > mutationRate.get((double) 5)){
	    		tableItem5.setText(new String[] {"Chance de Mutação(%)", "1", mutationRate.get((double) 1).toString()});
	    	}else{
	    		tableItem5.setText(new String[] {"Chance de Mutação(%)", "5", mutationRate.get((double) 5).toString()});
	    	}
		    
		}else{
			Map<Double,Integer> numberIterations = new HashMap<Double,Integer>();
			numberIterations.put((double) 100, 0);
			numberIterations.put((double) 1000, 0);
			numberIterations.put((double) 10000, 0);
		    
		    Map<Double,Integer> stopCondition = new HashMap<Double,Integer>();
		    stopCondition.put((double) 1, 0);
		    stopCondition.put((double) 5, 0);
		    stopCondition.put((double) 10, 0);
		    stopCondition.put((double) 15, 0);
		    
		    Map<Double,Integer> populationSize = new HashMap<Double,Integer>();
		    populationSize.put((double) 100, 0);
		    populationSize.put((double) 500, 0);
		    
		    Map<Double,Integer> elitismRate = new HashMap<Double,Integer>();
		    elitismRate.put((double) 5, 0);
		    elitismRate.put((double) 10, 0);
		    elitismRate.put((double) 15, 0);
		    
		    Map<Double,Integer> crossoverRate = new HashMap<Double,Integer>();
		    crossoverRate.put((double) 25.0, 0);
		    crossoverRate.put((double) 50.0, 0);
		    crossoverRate.put((double) 85.0, 0);
		    
		    Map<Double,Integer> mutationRate = new HashMap<Double,Integer>();
		    mutationRate.put((double) 0.5, 0);
		    mutationRate.put((double) 1, 0);
		    mutationRate.put((double) 5, 0);
		    
		    Map<Double,Integer> migrationRate = new HashMap<Double,Integer>();
		    migrationRate.put((double) 10, 0);
		    migrationRate.put((double) 20, 0);
		    migrationRate.put((double) 40, 0);
		    
		    Map<Double,Integer> migrationTax = new HashMap<Double,Integer>();
		    migrationTax.put((double) 10, 0);
		    migrationTax.put((double) 20, 0);
		    migrationTax.put((double) 40, 0);
		    
		    Map<Double,Integer> numberOfPopulations = new HashMap<Double,Integer>();
		    numberOfPopulations.put((double) 2, 0);
		    numberOfPopulations.put((double) 4, 0);
		    numberOfPopulations.put((double) 8, 0);
		    numberOfPopulations.put((double) 16, 0);
			
		    for(int i = 0; i < genetic.getRegional().size();i++){
		    	Parameter p = genetic.getRegional().get(i);
		    	numberIterations.put((double) p.getMaximumIterations(), numberIterations.get((double) p.getMaximumIterations())+1);
		    	stopCondition.put((double) p.getStopCondition(), stopCondition.get((double) p.getStopCondition())+1);
		    	populationSize.put((double) p.getSizeOfPopulation(), populationSize.get((double) p.getSizeOfPopulation())+1);
		    	elitismRate.put((double) p.getElitismRate(), elitismRate.get((double) p.getElitismRate())+1);
		    	crossoverRate.put((double) p.getCrossoverRate(), crossoverRate.get((double) p.getCrossoverRate())+1);
		    	mutationRate.put((double) p.getMutationRate(), mutationRate.get((double) p.getMutationRate())+1);
		    	migrationRate.put((double) p.getMigrationRate(), migrationRate.get((double) p.getMigrationRate())+1);
		    	migrationTax.put((double) p.getMigrationTax(), migrationTax.get((double) p.getMigrationTax())+1);
		    	numberOfPopulations.put((double) p.getNumberOfPopulations(), numberOfPopulations.get((double) p.getNumberOfPopulations())+1);
			}
		    
		    TableItem tableItem = new TableItem(table_2, SWT.NONE);
		  
	    	if(numberIterations.get((double) 100) > numberIterations.get((double) 1000) && numberIterations.get((double) 100) > numberIterations.get((double) 10000)){
	    		tableItem.setText(new String[] {"Número máximo de iterações", "100", numberIterations.get((double) 100).toString()});
	    	}else if(numberIterations.get((double) 1000) > numberIterations.get((double) 10000)){
	    		tableItem.setText(new String[] {"Número máximo de iterações", "1000", numberIterations.get((double) 1000).toString()});
	    	}else{
	    		tableItem.setText(new String[] {"Número máximo de iterações", "10000", numberIterations.get((double) 10000).toString()});
	    	}
	    	
	    	TableItem tableItem1 = new TableItem(table_2, SWT.NONE);
	    	if(stopCondition.get((double) 1) > stopCondition.get((double) 5) && stopCondition.get((double) 1) > stopCondition.get((double) 10) && stopCondition.get((double) 1) > stopCondition.get((double) 15)){
	    		tableItem1.setText(new String[] {"Condição de Parada(%)", "1", stopCondition.get((double) 1).toString()});
	    	}else if(stopCondition.get((double) 5) > stopCondition.get((double) 10) && stopCondition.get((double) 5) > stopCondition.get((double) 15)){
	    		tableItem1.setText(new String[] {"Condição de Parada(%)", "5", stopCondition.get((double) 5).toString()});
	    	}else if(stopCondition.get((double) 10) > stopCondition.get((double) 15)){
	    		tableItem1.setText(new String[] {"Condição de Parada(%)", "10", stopCondition.get((double) 10).toString()});
	    	}else{
	    		tableItem1.setText(new String[] {"Condição de Parada(%)", "10", stopCondition.get((double) 15).toString()});
	    	}
	    	
	    	TableItem tableItem2 = new TableItem(table_2, SWT.NONE);

	    	if(populationSize.get((double) 100) > populationSize.get((double) 500)){
	    		tableItem2.setText(new String[] {"Tamanho da População", "100", populationSize.get((double) 100).toString()});
	    	}else{
	    		tableItem2.setText(new String[] {"Tamanho da População", "500", populationSize.get((double) 500).toString()});
	    	}
	    	
	    	TableItem tableItem3 = new TableItem(table_2, SWT.NONE);

	    	if(elitismRate.get((double) 5) > elitismRate.get((double) 10) && elitismRate.get((double) 5) > elitismRate.get((double) 15)){
	    		tableItem3.setText(new String[] {"Chance de Elitismo(%)", "5", elitismRate.get((double) 5).toString()});
	    	}else if(elitismRate.get((double) 10) > elitismRate.get((double) 15)){
	    		tableItem3.setText(new String[] {"Chance de Elitismo(%)", "10", elitismRate.get((double) 10).toString()});
	    	}else{
	    		tableItem3.setText(new String[] {"Chance de Elitismo(%)", "15", elitismRate.get((double) 15).toString()});
	    	}
	    	
	    	TableItem tableItem4 = new TableItem(table_2, SWT.NONE);

	    	if(crossoverRate.get((double) 25) > crossoverRate.get((double) 50) && crossoverRate.get((double) 25) > crossoverRate.get((double) 85)){
	    		tableItem4.setText(new String[] {"Chance de Crossover(%)", "25", crossoverRate.get((double) 25).toString()});
	    	}else if(crossoverRate.get((double) 50) > crossoverRate.get((double) 85)){
	    		tableItem4.setText(new String[] {"Chance de Crossover(%)", "50", crossoverRate.get((double) 50).toString()});
	    	}else{
	    		tableItem4.setText(new String[] {"Chance de Crossover(%)", "85", crossoverRate.get((double) 85).toString()});
	    	}
	    	
	    	TableItem tableItem5 = new TableItem(table_2, SWT.NONE);

	    	if(mutationRate.get((double) 0.5) > mutationRate.get((double) 1) && mutationRate.get((double) 0.5) > mutationRate.get((double) 5)){
	    		tableItem5.setText(new String[] {"Chance de Mutação(%)", "0.5", mutationRate.get((double) 0.5).toString()});
	    	}else if(mutationRate.get((double) 1) > mutationRate.get((double) 5)){
	    		tableItem5.setText(new String[] {"Chance de Mutação(%)", "1", mutationRate.get((double) 1).toString()});
	    	}else{
	    		tableItem5.setText(new String[] {"Chance de Mutação(%)", "5", mutationRate.get((double) 5).toString()});
	    	}
	    	
	    	TableItem tableItem6 = new TableItem(table_2, SWT.NONE);

	    	if(migrationRate.get((double) 10) > migrationRate.get((double) 20) && migrationRate.get((double) 10) > migrationRate.get((double) 40)){
	    		tableItem6.setText(new String[] {"Chance de Migração(%)", "10", migrationRate.get((double) 10).toString()});
	    	}else if(migrationRate.get((double) 20) > migrationRate.get((double) 40)){
	    		tableItem6.setText(new String[] {"Chance de Migração(%)", "20", migrationRate.get((double) 20).toString()});
	    	}else{
	    		tableItem6.setText(new String[] {"Chance de Migração(%)", "40", migrationRate.get((double) 40).toString()});
	    	}
	    	
	    	TableItem tableItem7 = new TableItem(table_2, SWT.NONE);

	    	if(migrationTax.get((double) 10) > migrationTax.get((double) 20) && migrationTax.get((double) 10) > migrationTax.get((double) 40)){
	    		tableItem7.setText(new String[] {"Taxa de Migração(%)", "10", migrationTax.get((double) 10).toString()});
	    	}else if(migrationTax.get((double) 20) > migrationTax.get((double) 40)){
	    		tableItem7.setText(new String[] {"Taxa de Migração(%)", "20", migrationTax.get((double) 20).toString()});
	    	}else{
	    		tableItem7.setText(new String[] {"Taxa de Migração(%)", "40", migrationTax.get((double) 40).toString()});
	    	}
	    	
	    	TableItem tableItem8 = new TableItem(table_2, SWT.NONE);
	    	if(numberOfPopulations.get((double) 2) > numberOfPopulations.get((double) 4) && numberOfPopulations.get((double) 2) > numberOfPopulations.get((double) 8) && numberOfPopulations.get((double) 2) > stopCondition.get((double) 16)){
	    		tableItem8.setText(new String[] {"Condição de Parada(%)", "2", numberOfPopulations.get((double) 2).toString()});
	    	}else if(numberOfPopulations.get((double) 4) > numberOfPopulations.get((double) 8) && numberOfPopulations.get((double) 4) > numberOfPopulations.get((double) 16)){
	    		tableItem8.setText(new String[] {"Condição de Parada(%)", "4", numberOfPopulations.get((double) 4).toString()});
	    	}else if(numberOfPopulations.get((double) 8) > numberOfPopulations.get((double) 16)){
	    		tableItem8.setText(new String[] {"Condição de Parada(%)", "8", numberOfPopulations.get((double) 8).toString()});
	    	}else{
	    		tableItem8.setText(new String[] {"Condição de Parada(%)", "16", numberOfPopulations.get((double) 16).toString()});
	    	}
		}
		
	}
	
	/**
	 * Create "Resultados" group.
	 */
	
	protected void buildResultados1(){
		table_3 = new Table(resultsGroup, SWT.FULL_SELECTION);
		table_3.setLinesVisible(true);
		table_3.setHeaderVisible(true);
		table_3.setBounds(10, 24, 1354, 320);
		
		if(genetic.getBests().get(0).getModelPopulation() == ModelPopulationType.LM){
			TableColumn tableColumn_2 = new TableColumn(table_3, SWT.NONE);
			tableColumn_2.setWidth(270);
			tableColumn_2.setText("Número Máximo de Iterações");
			
			TableColumn tableColumn_3 = new TableColumn(table_3, SWT.NONE);
			tableColumn_3.setWidth(270);
			tableColumn_3.setText("Condição de Parada(%)");
			
			TableColumn tableColumn_4 = new TableColumn(table_3, SWT.NONE);
			tableColumn_4.setWidth(270);
			tableColumn_4.setText("Tamanho da População");
			
			TableColumn tableColumn_5 = new TableColumn(table_3, SWT.NONE);
			tableColumn_5.setWidth(270);
			tableColumn_5.setText("Chance de Elitismo(%)");
			
			TableColumn tableColumn_6 = new TableColumn(table_3, SWT.NONE);
			tableColumn_6.setWidth(270);
			tableColumn_6.setText("Chance de Crossover(%)");
			
			TableColumn tableColumn_7 = new TableColumn(table_3, SWT.NONE);
			tableColumn_7.setWidth(270);
			tableColumn_7.setText("Chance de Mutação(%)");
			
			TableColumn tableColumn_8 = new TableColumn(table_3, SWT.NONE);
			tableColumn_8.setWidth(270);
			tableColumn_8.setText("Gene Final");
			
			TableColumn tableColumn_9 = new TableColumn(table_3, SWT.NONE);
			tableColumn_9.setWidth(270);
			tableColumn_9.setText("Fitness(R$)");
			
			TableColumn tableColumn_10 = new TableColumn(table_3, SWT.NONE);
			tableColumn_10.setWidth(270);
			tableColumn_10.setText("Tempo(ms)");
			
			for(int i = 0; i < genetic.getLocal().size(); i++){
				Parameter p = genetic.getLocal().get(i);
				TableItem tableItem = new TableItem(table_3, SWT.NONE);
				tableItem.setText(new String[] {Integer.toString(p.getMaximumIterations()), Float.toString(p.getStopCondition()), 
						Integer.toString(p.getSizeOfPopulation()),Float.toString(p.getElitismRate()), Float.toString(p.getCrossoverRate()),
						Float.toString(p.getMutationRate()), p.getFinalGene().toString(), Double.toString(p.getFitness()),
						Double.toString(p.getTime())});
			}
		}else{
			TableColumn tableColumn_2 = new TableColumn(table_3, SWT.NONE);
			tableColumn_2.setWidth(270);
			tableColumn_2.setText("Número Máximo de Iterações");
			
			TableColumn tableColumn_3 = new TableColumn(table_3, SWT.NONE);
			tableColumn_3.setWidth(270);
			tableColumn_3.setText("Condição de Parada(%)");
			
			TableColumn tableColumn_4 = new TableColumn(table_3, SWT.NONE);
			tableColumn_4.setWidth(270);
			tableColumn_4.setText("Tamanho da População");
			
			TableColumn tableColumn_5 = new TableColumn(table_3, SWT.NONE);
			tableColumn_5.setWidth(270);
			tableColumn_5.setText("Chance de Elitismo(%)");
			
			TableColumn tableColumn_6 = new TableColumn(table_3, SWT.NONE);
			tableColumn_6.setWidth(270);
			tableColumn_6.setText("Chance de Crossover(%)");
			
			TableColumn tableColumn_7 = new TableColumn(table_3, SWT.NONE);
			tableColumn_7.setWidth(270);
			tableColumn_7.setText("Chance de Mutação(%)");
			
			TableColumn tableColumn_8 = new TableColumn(table_3, SWT.NONE);
			tableColumn_8.setWidth(270);
			tableColumn_8.setText("Chance de Migração(%)");
			
			TableColumn tableColumn_9 = new TableColumn(table_3, SWT.NONE);
			tableColumn_9.setWidth(270);
			tableColumn_9.setText("Taxa de Migração(%)");
			
			TableColumn tableColumn_10 = new TableColumn(table_3, SWT.NONE);
			tableColumn_10.setWidth(270);
			tableColumn_10.setText("Número de Populações");
			
			TableColumn tableColumn_11 = new TableColumn(table_3, SWT.NONE);
			tableColumn_11.setWidth(270);
			tableColumn_11.setText("Gene Final");
			
			TableColumn tableColumn_12 = new TableColumn(table_3, SWT.NONE);
			tableColumn_12.setWidth(270);
			tableColumn_12.setText("Fitness(R$)");
			
			TableColumn tableColumn_13 = new TableColumn(table_3, SWT.NONE);
			tableColumn_13.setWidth(270);
			tableColumn_13.setText("Tempo(ms)");	
			
			for(int i = 0; i < genetic.getRegional().size(); i++){
				Parameter p = genetic.getRegional().get(i);
				TableItem tableItem = new TableItem(table_3, SWT.NONE);
				tableItem.setText(new String[] {Integer.toString(p.getMaximumIterations()), Float.toString(p.getStopCondition()), 
						Integer.toString(p.getSizeOfPopulation()),Float.toString(p.getElitismRate()), Float.toString(p.getCrossoverRate()),
						Float.toString(p.getMutationRate()), Double.toString(p.getMigrationRate()), Double.toString(p.getMigrationTax()),
						Integer.toString(p.getNumberOfPopulations()),p.getFinalGene().toString(), Double.toString(p.getFitness()),
						Double.toString(p.getTime())});
			}
		}
	}
	
	/**
	 * Create "Tempo x Resultados" group and chart.
	 */
	protected void buildChart(){
		Chart chart = new Chart(grpTempoXResultado, SWT.NONE);
	    
		// set titles
		chart.getTitle().setText("Tempo x Resultado");
		chart.getAxisSet().getXAxis(0).getTitle().setText("Tempo(ms)");
		chart.getAxisSet().getYAxis(0).getTitle().setText("Resultado");

		double[] ySeries = new double[genetic.getBests().size()];
		double[] xSeries = new double[genetic.getBests().size()];
		
		for(int i = 0; i < genetic.getBests().size();i++){
			xSeries[i] = genetic.getBests().get(i).getTime();
			ySeries[i] = genetic.getBests().get(i).getFitness();
		}
		// create bar series
		IBarSeries barSeries = (IBarSeries) chart.getSeriesSet()
		    .createSeries(SeriesType.BAR, "bar series");
		barSeries.setYSeries(ySeries);
		barSeries.setXSeries(xSeries);
		
		// adjust the axis range
		chart.getAxisSet().adjustRange();
		
		chart.setBounds(15,20,640,290);
	}
	
	/**
	 * Create "Ajuda" tab.
	 */
	
	protected void createThirdTab(){
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("Ajuda");
		
		composite_2 = new Composite(tabFolder, SWT.NONE);
		tabItem.setControl(composite_2);
		
		buildSobre();
		
		buildObjetivos();
		
		buildFAQ();
	}
	
	/**
	 * Create "Sobre" group.
	 */
	
	protected void buildSobre(){
		Group grpSobre = new Group(composite_2, SWT.NONE);
		grpSobre.setText("Sobre");
		grpSobre.setBounds(0, 0, 1374, 220);
		
		Text text = new Text(grpSobre, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
		text.setBounds(10, 22, 1354, 188);
		text.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque nec nibh ac risus ultrices posuere eget in magna. Proin sodales erat lacinia, eleifend libero vel, ullamcorper risus. Nunc vitae dolor auctor, malesuada nulla in, aliquet mauris. Suspendisse fringilla ut enim vitae efficitur. Vivamus scelerisque purus et dignissim tempor. Integer ornare neque sed justo dictum, eu aliquet ligula fringilla. Vestibulum non purus eget elit lacinia venenatis vitae ac lacus. Phasellus fermentum odio consectetur lacus ornare efficitur. Ut eget ligula odio. Suspendisse potenti. Nam lacinia tortor et dolor aliquam, at convallis lacus convallis.Duis quis iaculis elit, a venenatis tellus. Nunc sit amet odio urna. Donec nec libero vel erat tempus sollicitudin. Cras pretium blandit mauris, a accumsan nisl bibendum vitae. Praesent imperdiet, tellus id tempus ultrices, lectus elit viverra odio, nec tempor dui ante a sem. Vestibulum tincidunt ligula augue, in facilisis eros fringilla quis. Curabitur a tellus vel arcu euismod auctor. Proin massa libero, semper ac erat fringilla, faucibus mollis dolor.");
	}
	
	/**
	 * Create "Objetivos" group.
	 */
	
	protected void buildObjetivos(){
		Group group = new Group(composite_2, SWT.NONE);
		group.setText("Objetivos");
		group.setBounds(0, 251, 1374, 220);
		
		Text text = new Text(group, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
		text.setBounds(10, 22, 1354, 188);
		text.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque nec nibh ac risus ultrices posuere eget in magna. Proin sodales erat lacinia, eleifend libero vel, ullamcorper risus. Nunc vitae dolor auctor, malesuada nulla in, aliquet mauris. Suspendisse fringilla ut enim vitae efficitur. Vivamus scelerisque purus et dignissim tempor. Integer ornare neque sed justo dictum, eu aliquet ligula fringilla. Vestibulum non purus eget elit lacinia venenatis vitae ac lacus. Phasellus fermentum odio consectetur lacus ornare efficitur. Ut eget ligula odio. Suspendisse potenti. Nam lacinia tortor et dolor aliquam, at convallis lacus convallis.Duis quis iaculis elit, a venenatis tellus. Nunc sit amet odio urna. Donec nec libero vel erat tempus sollicitudin. Cras pretium blandit mauris, a accumsan nisl bibendum vitae. Praesent imperdiet, tellus id tempus ultrices, lectus elit viverra odio, nec tempor dui ante a sem. Vestibulum tincidunt ligula augue, in facilisis eros fringilla quis. Curabitur a tellus vel arcu euismod auctor. Proin massa libero, semper ac erat fringilla, faucibus mollis dolor.");
	}
	
	/**
	 * Create "FAQ" group.
	 */
	
	protected void buildFAQ(){
		Group group_1 = new Group(composite_2, SWT.NONE);
		group_1.setText("FAQ");
		group_1.setBounds(0, 500, 1374, 220);
		
		Text text = new Text(group_1, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
		text.setBounds(10, 22, 1354, 188);
		text.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque nec nibh ac risus ultrices posuere eget in magna. Proin sodales erat lacinia, eleifend libero vel, ullamcorper risus. Nunc vitae dolor auctor, malesuada nulla in, aliquet mauris. Suspendisse fringilla ut enim vitae efficitur. Vivamus scelerisque purus et dignissim tempor. Integer ornare neque sed justo dictum, eu aliquet ligula fringilla. Vestibulum non purus eget elit lacinia venenatis vitae ac lacus. Phasellus fermentum odio consectetur lacus ornare efficitur. Ut eget ligula odio. Suspendisse potenti. Nam lacinia tortor et dolor aliquam, at convallis lacus convallis.Duis quis iaculis elit, a venenatis tellus. Nunc sit amet odio urna. Donec nec libero vel erat tempus sollicitudin. Cras pretium blandit mauris, a accumsan nisl bibendum vitae. Praesent imperdiet, tellus id tempus ultrices, lectus elit viverra odio, nec tempor dui ante a sem. Vestibulum tincidunt ligula augue, in facilisis eros fringilla quis. Curabitur a tellus vel arcu euismod auctor. Proin massa libero, semper ac erat fringilla, faucibus mollis dolor.");
	}
	
	public void showError(String message){
	    Shell alert = new Shell(shell);

	    int style = SWT.ICON_ERROR;
	    
	    //SWT.ICON_QUESTION | SWT.YES | SWT.NO
	    
	    MessageBox messageBox = new MessageBox(alert, style);
	    messageBox.setMessage(message);
	    int rc = messageBox.open();

	    switch (rc) {
	    case SWT.OK:
	      System.out.println("SWT.OK");
	      break;
	    case SWT.CANCEL:
	      System.out.println("SWT.CANCEL");
	      break;
	    case SWT.YES:
	      System.out.println("SWT.YES");
	      break;
	    case SWT.NO:
	      System.out.println("SWT.NO");
	      break;
	    case SWT.RETRY:
	      System.out.println("SWT.RETRY");
	      break;
	    case SWT.ABORT:
	      System.out.println("SWT.ABORT");
	      break;
	    case SWT.IGNORE:
	      System.out.println("SWT.IGNORE");
	      break;
	    }

	   // display.dispose();
	}
	
	public void showMessage(String message){
	    Shell alert = new Shell(shell);

	    int style = SWT.ICON_WORKING;
	    
	    //SWT.ICON_QUESTION | SWT.YES | SWT.NO
	    
	    MessageBox messageBox = new MessageBox(alert, style);
	    messageBox.setMessage(message);
	    int rc = messageBox.open();

	    switch (rc) {
	    case SWT.OK:
	      System.out.println("SWT.OK");
	      break;
	    case SWT.CANCEL:
	      System.out.println("SWT.CANCEL");
	      break;
	    case SWT.YES:
	      System.out.println("SWT.YES");
	      break;
	    case SWT.NO:
	      System.out.println("SWT.NO");
	      break;
	    case SWT.RETRY:
	      System.out.println("SWT.RETRY");
	      break;
	    case SWT.ABORT:
	      System.out.println("SWT.ABORT");
	      break;
	    case SWT.IGNORE:
	      System.out.println("SWT.IGNORE");
	      break;
	    }

	   // display.dispose();
	}
}
