package src;

import java.math.BigInteger;
import java.util.Arrays;

public class RArray {
	private final String JLIM_CONST = "200";
	private RVal[] rValArray;
	private int fSize;
	private int[] F;
	private BigInteger N;
	int L;

	public RArray(int L, BigInteger N, int[] F) {
		rValArray = new RVal[L];
		this.F = F;
		this.N = N;
		this.L = L;
		fSize = F.length;
	}

	/**
	 * 
	 * @return
	 */
	public RVal[] getArray() {
		BigInteger j = new BigInteger("1");
		BigInteger k = new BigInteger("2");
		RVal testVal = null; // reference to the RVal that will be tested
		int counter = 0; // run until we have L RVals, also idx of
							// current soln
		byte isSmooth; // assigned to return from factorR

		// this is master while: manage acquisition of rValArray
		while (counter < L) {
			BigInteger jLim = new BigInteger(JLIM_CONST);

			// Vary j and k to obtain r values
			if (j.compareTo(jLim) <= 0) {
				j = j.add(BigInteger.ONE);
			} else {
				k = k.add(BigInteger.ONE);
				j = new BigInteger("2");
			}

			// get a new r to process and factor
			testVal = rGenerator(j, k);
			isSmooth = factorR(testVal);

			// if isSmooth, store it and counter++; if invalid, throw away
			if (isSmooth == 1) {
				counter = checkIfDuplicate(counter, testVal);
			}
		} // end master while

		return rValArray;
	}

	/**
	 * 
	 * @param counter
	 * @param testVal
	 * @return
	 */
	public int checkIfDuplicate(int counter, RVal testVal) {
		// check for duplicates
		for (int l = 0; l < counter; l++) {
			if (Arrays.equals(rValArray[l].getBinaryRow(), testVal.getBinaryRow())) {
				return counter;
			}
		}
		// if not a duplicate, store!
		rValArray[counter] = testVal;
		return ++counter;
	}

	/**
	 * 
	 * @param rVal
	 * @return
	 */
	private byte factorR(RVal rVal) {
		BigInteger n = rVal.getRSquareMod(); // processed down to 1
		int i = 0; // idx for increment through factorbase F, never reset
		short exp = 0; // exponent of each factor, reset beginning of factor
						// processing

		while (i < fSize) {
			// possible factor, tested this iteration
			BigInteger contestant = new BigInteger(Integer.toString(F[i]));
			exp = 0; // reset exp, stays 0 if not a factor

			// loops until this factor no longer works
			// aka gets multiplicity of contestant
			while (n.mod(contestant) == BigInteger.ZERO) {
				exp++;
				n = n.divide(contestant);
			}

			// populate this RVal's exponents and binary arrays for the ith
			// factor
			rVal.setExponentsValue(i, exp);
			rVal.setBinaryValue(i, (byte) (exp % 2));

			// check if this is the last factor (aka n == 1) and return if so
			if (n.compareTo(BigInteger.ONE) == 0) { // n == 1

				rVal.setLargestFactorIdx(i); // store the index of the largest
												// factor
				return 1;
			} else {
				i++;
			}
		} // end factoring while

		// if number is not completely factored (aka n != 1) after reaching
		// the end of the factorbase, then it is not B-smooth. Indicate
		// that it should be discarded
		return 0;
	}

	/**
	 * 
	 * @param j
	 * @param k
	 * @return
	 */
	/*
	 * Name: rGenerator Purpose: obtain a new value of r, create a matching RVal
	 * object, and set the rSquareMod field Parameters: - BigInteger j,k :
	 * varying values which will yield new r's Return: - Rval rVal : new value
	 * to test for validity in matrix
	 */
	public RVal rGenerator(BigInteger j, BigInteger k) {

		// floor(sqrt(N*k))+j
		BigInteger r = squareRoot((N.multiply(k))).add(j);

		RVal rVal = new RVal(r, fSize);

		// r = r^2 mod N
		rVal.setRSquareMod(r.multiply(r).mod(N));

		return rVal;
	}

	/**
	 * Calculate the square root of a BigInteger in logarithmic time
	 * 
	 * @param x
	 * @return
	 */
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