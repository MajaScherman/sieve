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
            int jkInc = 0; // incremented every iteration of master while
            int counter = 0; // run until we have L RVals, also idx of
                             // current soln 
            byte isSmooth; // assigned to return from factorR 
            RVal testVal = null; // reference to the RVal that will be tested

            // this is master while: manage acquisition of rValArray
            while (counter < L) {
                
                // increment k and j in a staggered pattern, so that r grows
                // more slowly. (e.g. (k,j) = (1,1);(1,2);(2,2);(2,3); etc..)
                if (jkInc % 2 == 0) {
                    k++;
                } else {
                    j++;
                }

                // get a new r to process and factor
                // TODO make parallel
                testVal = rGenerator(j,k);
                isSmooth = factorR(testVal);
                
                // if isSmooth, store it and counter++; if invalid, throw away 
                if (isSmooth == 1) {
                    // check for duplicity


                    // if not a duplicate, store!
                    rValArray[counter] = testVal;
                    counter++;
                }
            } // end master while
        } // end createRList


        /* Name: factorR
         * Purpose: factor an rSquareMod value to populate the RVal fields.
         * This makes necessary information available for later use, and 
         * determines if the RVal passed in should be discarded
         * Parameters:
         *          RVal r : the RVal to process
         * Return:
         *          ?
         */
        private byte factorR(RVal rVal) {
            short i = 0; // idx for increment through factorbase F, never reset
            short exp = 0; // exponent of each factor, reset beginning of factor
                         // processing
            boolean finished = 0; // is factoring complete?

            BigInteger n = rVal.getRSquareMod(); // processed down to 1 by factor algo

            while (i < fSize) {
                // possible factor, tested this iteration
                BigInteger contestant = new BigInteger(F[i]);

                // reset exp, stays 0 if not a factor
                exp = 0;

                // loops until this factor no longer works
                // aka gets multiplicity of contestant
                while (n.mod(contestant) == 0) {
                        exp++;
                        n = n.divide(contestant);
                }

                // populate this RVal's exponents and binary arrays for the ith 
                // factor
                rVal.setExponentsValue( i, exp);
                rVal.setBinaryValue( i, exp % 2);	
                
                // check if this is the last factor (aka n == 1). 
                // If so, exit this while( i<fSize )
                finished = (n.compareTo(BigInteger.ONE) == 0);

                if (finished) { // n == 1

                    // store the index of the largest factor, to that
                    // unboxing this factorization does not make
                    // meaningless checks at the end of F
                    rVal.setLargestFactorIdx(i);

                    // escape factoring while
                    i = fSize;
                } else {
                    i++;
                }

            } // end factoring while

            // if number is not completely factored (aka n != 1) after reaching
            // the end of the factorbase, then it is not B-smooth. Indicate
            // that it should be discarded
            if (!finished) {
                return 0;
            }

            return 1; // indicate to keep!
        }

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
