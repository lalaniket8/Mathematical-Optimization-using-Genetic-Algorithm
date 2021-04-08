package simulator;

public class Launcher {

	public static void main(String[] args) {

		// set window value carefully!
		final int windowX = 10;
		final int windowY = 10;

		// try {
		LinAlgebra la = new LinAlgebra();
		la.displayGraph("EMPTY", "", "x ", "y ", windowX, windowY);

		Point[] p = la.genRandomPopulation();
		int count = 0;

		la.initPopulation(p);
		try {
			Thread.sleep(2000);
			double prevObjFunSoln = 0;
			double currObjFunSoln = 99;
			while (Math.abs(currObjFunSoln - prevObjFunSoln) > 0.0001) {
				count++;
				la.updateGraph("Generation-" + count + ":Entire Population");
				prevObjFunSoln = currObjFunSoln;
				currObjFunSoln = la.printSolution();
				Thread.sleep(600);

				la.calFitness();
				la.genMatingPool();
				la.updateGraph("Generation-" + count + ":Mating Pool");

				Thread.sleep(600);

				la.breed();
				la.updateGraph("Generation-" + count + ":Offsprings");
				Thread.sleep(600);
				System.gc();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
