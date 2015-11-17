package src;

import java.math.*;
import java.util.Arrays;

public class Algo {
	// TODO this will have to change to something else
	private int certainty = 1000;
	private int F[] = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29 };
	private int fSize;
	private int L; // TODO L=2^10
	private final int BUFFER_CONST = 24;

        
    private BigInteger N;
    private RVal[] rValArray;

	public Algo(BigInteger N, int factorbaseSize) {
		this.N = N;

        // generate factorbase
        generateFactorbase(factorbaseSize);
        fSize = F.length;
        L = fSize + BUFFER_CONST;
        
        // get all r values, aka populate rValArray
		RArray rArray = new RArray(L,fSize,N, F);
		rValArray = rArray.getArray();
		
//        // TODO Get x*M = 0 Solutions
//		SolutionsManager sm = new SolutionsManager();
//        
//        // TODO cycle through above Solutions until gcd(y-x,N) = p or
//        // q. Send that p or q to testing
//		// Tentatively finished - must edit to fit Gaussian implementation
//        SolutionsProcessor solnProcessor = 
//        		new SolutionsProcessor(rValArray, F, sm, N);
//        BigInteger q = solnProcessor.findFactor();
//
//        // TODO Test that solution is correct
//        BigInteger p = N.divide(q);
//        checkQ(q, p);
	}

    /* HELPER METHODS */
	private void checkQ(BigInteger q, BigInteger p) {
		// check that each is a 12 digit prime number, and that their product = N
		System.out.println("q = " + q.toString() + "  isProbablyPrime: " +
				q.isProbablePrime(certainty));
		
		System.out.println("p = " + p.toString() + " isProbablyPrime: " +
				p.isProbablePrime(certainty));
		
		System.out.println(N.toString() + " == " + q.multiply(p).toString() + " ?");
	}
	
	void generateFactorbase(int size) { 
		int n = 0;
		int i, j, k;
		F = new int[size]; // degree of 24
		
		for(i = 2; n < size; ++i) { 
			k = (int) (Math.sqrt((double) i) + 1);
		    
			for(j=0; j < n && F[j] <= k; ++j) {
				if( (i % F[j]) == 0) {
					j = 0; 
					break; 
				}
			}
		
		    if (j != 0 || n == 0) { 
		    	F[n++]= i;
		    	System.out.print(F[n-1] + ", ");
			}
		}
		System.out.println();
	}
}
