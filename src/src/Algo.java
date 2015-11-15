package src;

import java.math.BigInteger;

public class Algo {
	// TODO this will have to change to something else
	private int F[] = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29 };
	private int L = 12; // TODO L=2^10
	private int fSize = F.length;
        
    private BigInteger N;
    private RVal[] rValArray;

	public Algo(BigInteger N) {
		this.N = N;

        // generate factorbase
        
        
        // get all r values, aka populate rValArray
		RArray rArray = new RArray(L,fSize,N, F);
		rValArray = rArray.getArray();

        // TODO Get x*M = 0 Solutions

        
        // TODO cycle through above Solutions until gcd(y-x,N) = p or
        // q. Send that p or q to testing
        SolutionsProcessor solnProcessor = new SolutionsProcessor(rValArray, F);

        // TODO Test that solution is correct
	}


    /* HELPER METHODS */
	
}
