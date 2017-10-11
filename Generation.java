import java.util.ArrayList;
import java.util.Random;

import org.jfree.ui.RefineryUtilities;

//Defines all methods and members for implementing Genetic Algorithm
public abstract class Generation {
	
	protected ArrayList<Exp> constraints = new ArrayList<Exp>();//list of expressions containing constraints
	protected int populationSize = 100;//population size default 100
	protected double variationRate = 0; //variation or mutation rate default 0% 
	protected Point[] population = new Point[populationSize];
	protected Boolean populationInitFlag = false;
	protected XYLineChart_AWT chart;
	protected Point[] matingPool;
	protected Exp objectiveFun;
	protected int windowX,windowY;
	Point solution = null;
	
	//display present generation on graph
public void displayGraph(String title, String genName, String xAxesName, String yAxesName,int windowX, int windowY) {
	chart = new XYLineChart_AWT(title,genName,xAxesName,yAxesName,constraints,windowX, windowY);
	chart.pack( );          
    RefineryUtilities.centerFrameOnScreen(chart);          
    chart.setVisible(true);    
    chart.dispConstraints();
    chart.initPopulation(population,populationInitFlag);
    this.windowX = windowX;
    this.windowY = windowY;
   
}

public void dispSolutionOnGraph() {
	 chart.dispSoln(solution);
}

public void updateGraph(String title) {
	chart.setTitle(title);
	chart.initPopulation(population, populationInitFlag);
}

public void dispMatingPool() {
	chart.initPopulation(population, populationInitFlag);
}
//calculate fitness for all individual or Points in the population
protected abstract void calFitness();

//generating mating pool for population
protected abstract void genMatingPool();

//breed Points in mating pool to generate new population
protected abstract void breed();

//initialize population
protected abstract void initPopulation(Point[] p);

//generate offspring from 2 parent Points. 
protected abstract Point[] genOffspring(Point p1);

//generate population with random genotype
public Point[] genRandomPopulation() {
	Point[] randomPopulation = new Point[populationSize];
	 Random rand = new Random();
         for (int i = 0; i < populationSize; i++) {
        	 Point p = new Point(rand.nextDouble() * windowX,rand.nextDouble() * windowY);
             randomPopulation[i] = p;
            // randomPopulation[i].setY(rand.nextDouble() * 15);
            // xydataset.getSeries(PopulationSeriesIndex).add(x,y);
             //System.out.println("indivisual added:"+x+","+y);
         }
    
     return randomPopulation;
}
}
