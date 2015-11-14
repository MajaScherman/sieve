package src;

import java.math.BigInteger;

public class Algo {
	// TODO this will have to change to something else
	private int F[] = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29 };
	private int L = 12; // TODO L=2^10
	private int fSize = F.length;
        
        private BigInteger N;
        private RVal[] rValsArray;

	public Algo(BigInteger N) {
		this.N = N;
                rValArray = new RVal[L];

                // TODO get all r values, aka populate rValArray
                createRList();

                // TODO Get x*M = 0 Solutions

                
                // TODO cycle through above Solutions until gcd(y-x,N) = p or
                // q. Send that p or q to testing


                // TODO Test that solution is correct
	}




    /* HELPER METHODS */

        private void createRList() {
            int k = 0;
            int j = 1;
            int counter = 0; // run until we have L solutions
            RVal testVal = null; // reference to the RVal that will be tested


            while (counter < L) {
                
                // increment k and j in a staggered pattern, so that r grows
                // more slowly. (e.g. (k,j) = (1,1);(1,2);(2,2);(2,3); etc..)
                if (counter % 2 == 0) {
                    k++;
                } else {
                    j++;
                }

                // get a new r to process
                testVal = rGenerator(j,k);

                // factor testVal
                // if valid, store it and counter++; if invalid, throw away 
                // TODO make parallel
                



            } // end master while
        } // end createRList

        /* Name: rGenerator
         * Purpose: obtain a new value of r, create a matching RVal object, and
         * set the rSquareMod field
         * Parameters:
         *      - BigInteger j,k : varying values which will yield new r's
         * Return:
         *      - Rval rVal : new value to test for validity in matrix
         */
        private RVal rGenerator(BigInteger j, BigInteger k) {
            
            // floor(sqrt(N*k))+j
	    BigInteger r = squareRoot((N.multiply(k))).add(j);

            RVal rVal = new RVal( r, matrixWidth);

            // r = r^2 mod N
            rVal.setRSquareMod(r.multiply(r).mod(N));

            return rVal; 
        }

	/** Calculate the square root of a BigInteger in logarithmic time */
	// naturally floors it?
	private BigInteger squareRoot(BigInteger x) {
		BigInteger right = x, left = BigInteger.ZERO, mid;
		while (right.subtract(left).compareTo(BigInteger.ONE) > 0) {
			mid = (right.add(left)).shiftRight(1);
			if (mid.multiply(mid).compareTo(x) > 0)
				right = mid;
			else
				left = mid;
		}
		return left;
	}
}
