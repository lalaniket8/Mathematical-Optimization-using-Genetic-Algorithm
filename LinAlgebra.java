import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class LinAlgebra extends Generation{
	 
	Exp e1,e2;
	
	LinAlgebra(){
		
		objectiveFun = new Exp();
		objectiveFun.setExp("x+y", false, true);
		
	 	e1 = new Exp();
	    e1.setExp("x",true,false);
	    constraints.add(e1);

	    e2 = new Exp();
	    e2.setExp("8",false,false);
	    constraints.add(e2);
	    
	    variationRate = 0.1;
	}
	@Override
	protected void calFitness() {
		// TODO Auto-generated method stub
		for(int i=0; i<populationSize; i++) {
			try {
				
				this.population[i].setFitness(objectiveFun.solve(this.population[i].getX(),this.population[i].getY()));
				if(!e1.satisfy(this.population[i].getX(),this.population[i].getY()) || !e2.satisfy(this.population[i].getX(),this.population[i].getY())){
					this.population[i].setFitness(0);
				}
			
			} catch (PointUsageException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//this.initPopulation(this.population);
		
	}

	@Override
	protected void genMatingPool() {
		// TODO Auto-generated method stub
		
		ArrayList<Point> tempFeasible = new ArrayList<Point>();
		
		//for(Point u:this.population)
			//System.out.println(u+"!");
		
		for(int i=0;i<populationSize;i++) {
			try {
				//System.out.println("!"+this.population[i].getFitness());
				if(this.population[i].getFitness()>0)
					tempFeasible.add(this.population[i]);
			} catch (PointUsageException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Collections.sort(tempFeasible, new Comparator<Point>(){

			@Override
			public int compare(Point arg0, Point arg1) {
				// TODO Auto-generated method stub
				double arg0Fitness = 0;
				double arg1Fitness = 0;
				
				try {
				arg0Fitness = arg0.getFitness()*100;
				arg1Fitness = arg1.getFitness()*100;}
				catch(Exception e) {
					e.printStackTrace();
				}
				
				return (int) (arg1Fitness - arg0Fitness);
			}			
		});
		
		int tempfittestsize = (int)(tempFeasible.size()*(0.80));
		Point[] tempfittest = new Point[tempfittestsize];
		int frontcount = 0;
		int endcount = tempfittestsize-1;
		int count = 0;
		while(count<tempfittestsize) {
			if(count/tempfittestsize > variationRate)
			{tempfittest[count] = tempFeasible.get(frontcount);
			frontcount++;}
			else
			{tempfittest[count] = tempFeasible.get(endcount);
			endcount--;}
			count++;
			
		}
		population = tempfittest; //tempFeasible.toArray(new Point[tempFeasible.size()]);
	}

	@Override
	protected void breed() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		ArrayList<Point> temp = new ArrayList<Point>();
		while(temp.size()<populationSize) {
			int randomChoice = rand.nextInt(population.length);
			
			Point parent1 = population[randomChoice];
			
			randomChoice = rand.nextInt(population.length);
			Point parent2 = population[randomChoice];
			
			temp.add(genOffspring(parent1, parent2));
		}
			population = temp.toArray(new Point[populationSize]);
		
	}
	
	protected double crossover(double v1,double v2) {
		Random r = new Random();
		return v1>v2?((v1-v2)*r.nextDouble()+v1):((v2-v1)*r.nextDouble()+v2);
	}

	@Override
	protected Point genOffspring(Point p1, Point p2) {
		// TODO Auto-generated method stub
		double childx = 0;
		double childy = 0;
		try {
		 childx = crossover(p1.getX(),p2.getX());
		 childy = crossover(p1.getY(),p2.getY());
		 }
		catch(Exception e)
		{e.printStackTrace();}
		Point offSpring = new Point(childx,childy);
		return offSpring;
	}
	
	@Override
	protected void initPopulation(Point[] p) {
		// TODO Auto-generated method stub
		populationInitFlag = true;
		
		for(int i=0; i<populationSize; i++)
		{try {
			
			this.population[i] = new Point(p[i].getX(),p[i].getY());
			double r = p[i].getFitness();
			//System.out.println("****"+r);
			this.population[i].setFitness(r);
			
		} catch (PointUsageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		
		
	
	}

	

}
