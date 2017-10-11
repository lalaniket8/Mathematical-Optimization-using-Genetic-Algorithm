import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class LinAlgebra extends Generation{
	 
	Exp e1,e2;
	//here is where you give constraints and obj fun
	final String objectiveFunString = "x*x+y";
	final String constraint1String = "4";
	final String constraint2String = "5";
	final String constraint3String = "4";
	final String constraint4String = "3";
	final int Scale = 3;
	final Boolean Maximization = false;
	final double matingPoolSelectionRatio = 0.75;
	//Point solution = null;
	
	LinAlgebra(){
		
		//init values
		variationRate = 0.1;
		
		objectiveFun = new Exp();
		objectiveFun.setExp(objectiveFunString, false, true);
		
	 	e1 = new Exp();
	    e1.setExp(constraint1String,true,false);
	    constraints.add(e1);

	    e2 = new Exp();
	    e2.setExp(constraint2String,false,false);
	    constraints.add(e2);
	    
	    
	}
	@Override
	protected void calFitness() {
		// TODO Auto-generated method stub
		for(int i=0; i<populationSize; i++) {
			try {
				this.population[i].setFitness(0);
				if(this.population[i].getX()>=0 && this.population[i].getY()>=0 && e1.satisfy(this.population[i].getX(),this.population[i].getY()) && e2.satisfy(this.population[i].getX(),this.population[i].getY())){
					this.population[i].setFitness(objectiveFun.solve(this.population[i].getX(),this.population[i].getY()));				
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
		
		ArrayList<Point> tempFeasible = new ArrayList<Point>();

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
				if(Maximization)
				return (int) (arg1Fitness - arg0Fitness);
				else
				return (int) (arg0Fitness - arg1Fitness);
			}			
		});
		//System.out.println(tempFeasible);
		solution = tempFeasible.get(0);
		dispSolutionOnGraph();
		
		int tempfittestsize = (int)(tempFeasible.size()*(0.50));
		Point[] tempfittest = new Point[tempfittestsize];
		int frontcount = 0;
		int endcount = tempfittestsize-1;
		int count = 0;
		Random randomvar = new Random();
		
		while(count<tempfittestsize) {
			int r = randomvar.nextInt(100);
			if(r > variationRate)
			{tempfittest[count] = tempFeasible.get(frontcount);
			frontcount++;}
			else
			{tempfittest[count] = tempFeasible.get(endcount);
			endcount--;}
			count++;
			
		}
		population = tempfittest; //tempFeasible.toArray(new Point[tempFeasible.size()]);
	}

	public void printSolution() {
		if(this.solution != null)
			{try {
				System.out.println("Solution:("+this.solution.getX()+","+this.solution.getY()+"):"+objectiveFun.solve(solution.getX(),solution.getY()));
			} catch (PointUsageException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
	}
	
	@Override
	protected void breed() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		ArrayList<Point> temp = new ArrayList<Point>();
		
		while(temp.size()<populationSize) {
			int randomChoice = rand.nextInt(population.length);
			//Point parent = population[randomChoice];
			Point parent = population[0];
			
		//	randomChoice = rand.nextInt(population.length);
		//	Point parent2 = population[randomChoice];
			
		//	temp.add(genOffspring(parent1, parent2));
			Point children[] = genOffspring(parent);
			for(int z=0;z<9;z++)
			temp.add(children[z]);
		}
			population = temp.toArray(new Point[populationSize]);
		
	}
	
	protected double crossover(double v1,double v2) {
		Random r = new Random();
		return v1>v2?((v1-v2)*r.nextDouble()+v1):((v2-v1)*r.nextDouble()+v2);
	}
	
	@Override
	protected Point[] genOffspring(Point p) {
		// TODO Auto-generated method stub
		Point[] children = new Point[9];
		try {
			Random rv = new Random();
			double r = rv.nextDouble();
			double scaledval = Scale*r;
			
		children[0] = new Point(p.getX(),p.getY());r = rv.nextDouble();scaledval = Scale*r;
		children[1] = new Point(p.getX()-scaledval,p.getY());r = rv.nextDouble();scaledval = Scale*r;
		children[2] = new Point(p.getX(),p.getY()+scaledval);r = rv.nextDouble();scaledval = Scale*r;
		children[3] = new Point(p.getX(),p.getY()-scaledval);r = rv.nextDouble();scaledval = Scale*r;
		children[4] = new Point(p.getX()+scaledval,p.getY()+scaledval);r = rv.nextDouble();scaledval = Scale*r;
		children[5] = new Point(p.getX()-scaledval,p.getY()-scaledval);r = rv.nextDouble();scaledval = Scale*r;
		children[6] = new Point(p.getX()-scaledval,p.getY()+scaledval);r = rv.nextDouble();scaledval = Scale*r;
		children[7] = new Point(p.getX()+scaledval,p.getY()-scaledval);r = rv.nextDouble();scaledval = Scale*r;
		children[8] = new Point(p.getX()+scaledval,p.getY());
		}
		catch(Exception e) {e.printStackTrace();}
		return children;
		/*double childx = 0;
		double childy = 0;
		try {
		 childx = crossover(p1.getX(),p2.getX());
		 childy = crossover(p1.getY(),p2.getY());
		 }
		catch(Exception e)
		{e.printStackTrace();}
		Point offSpring = new Point(childx,childy);
		return offSpring;*/
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
