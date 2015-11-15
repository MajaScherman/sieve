package src;

/*
 * I don't know how this class is going to operate. Somehow it will take 
 * our rValArray, perform Gaussian elmination, and hold the list of solutions.
 * 
 * For the meantime, I'm going to say that this keeps the solutions to itself,
 * and pass the whole manager to SolutionsProcessor.
 */
public class SolutionsManager {
	byte[][] solutions = { {0,0,1,0,0,0,0,1,1,1,0,0},
			{1,0,1,1,0,0,0,0,0,0,0,0} }; // TEMP SOLUTION
	int i = -1;
		
	public SolutionsManager() {}
	
	public byte[] getNextSolution() {
		return solutions[++i];
	}
	
	// ONLY FOR DEVELOPMENT
	public byte[] getGoodSolution() {
		byte[] retVal  = {0,0,1,0,0,0,0,1,1,1,0,0};
		return retVal;
	}
	
	public byte[] getBadSolution() {
		byte[] retVal = {0,0,1,0,0,0,0,1,1,1,0,0};
		return retVal;
	}
	
	public void setSolutions(byte[][] solns) {
		solutions = solns;
		i = -1;
	}
}
