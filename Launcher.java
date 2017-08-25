import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.jfree.ui.RefineryUtilities;

import com.sun.javafx.fxml.expression.Expression;

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//try {	
		LinAlgebra la = new LinAlgebra();
		la.displayGraph("EMPTY", "", "x ", "y ");

	    Point[] p = la.genRandomPopulation(15);
	    int count=0;
	    
		la.initPopulation(p);
		try {
			Thread.sleep(2000);
   	    while(count<15) {
		count++;
   	    la.updateGraph("Generation-"+count+":Entire Population");
	    
   	    Thread.sleep(500);
	    
	    la.calFitness();
	    la.genMatingPool();
	    la.updateGraph("Generation-"+count+":Mating Pool");
		
	    Thread.sleep(500);
		
	    la.breed();
	    la.updateGraph("Generation-"+count+":Offsprings");
	    Thread.sleep(500);
   	    }
		}
		catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 }
	}

}
