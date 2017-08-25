/*
 * Class point is a point on the graph and also a possible solution
 * candidate for the problem.
 */
public class Point{
	
//x,y coords of point and its solution
private double x;
private double y;
private double fitness;

//flags to check whether or not coords and soln are initialized before getting them
private Boolean isCoordSet = false;
private Boolean isFitnessSet = true;

//constructor to init points
Point(double d, double e){
	isCoordSet = true;
	this.x = d;
	this.y = e;
}

@Override
public String toString() {
	return "("+this.x+","+this.y+"):"+this.fitness;
	}

//set solution to this point object
public void setFitness(double f) {
	isFitnessSet=true;
	this.fitness = f;
}

//getters for x, y and solution 
public double getX() throws PointUsageException {
	if(isCoordSet)
		return this.x;
	else
		throw new PointUsageException("coords not set");
}

public double getFitness()  throws PointUsageException {
	//System.out.println("ingere"+this.fitness);
	if(isFitnessSet)
		return this.fitness;
	else
		throw new PointUsageException("fitness for this point not beign set yet");
}

public double getY() throws PointUsageException {
	if(isCoordSet)
		return this.y;
	else
		throw new PointUsageException("coords not set");
}
}
