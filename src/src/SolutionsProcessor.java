package src;

import java.math.BigInteger;

public class SolutionsProcessor {

	public SolutionsProcessor(RVal[] rValArray, int[] F) {
		// TODO somehow get hold of a solution. This depends on how we implement Gauss stuff
		RVal rVal = null; // current RVal to get values from
		int largestIdx;
		int newExp;
		byte[] soln = {0,0,1,0,0,0,0,1,1,1,0,0};
		
		BigInteger x = new BigInteger("1"); // left side of the equation
		BigInteger y = new BigInteger("1"); // right side of the equation
		BigInteger current = null;
		
		for (int i = 0; i < soln.length; i++) {
			if (soln[i] == 1) {
				rVal = rValArray[i];
				largestIdx = rVal.getLargestFactorIdx();
				
				x = x.multiply(rVal.getR());
				
				for (int j = 0; j < largestIdx; j++) {
					newExp = rVal.getExponent(j)/2;
					current = new BigInteger( Integer.toString(F[i]^newExp) );
					System.out.print(F[i] + "^" + newExp + ", ");
					y = y.multiply(current);
				}
				System.out.println();
			}
		}
	}
	
	
}