package src;

import java.math.*;
import java.util.Arrays;

public class Algo {
	// TODO this will have to change to something else
	private int certainty = 1000;
	private static int F[];
	private int fSize;
	// private int L; // TODO L=2^10
	private static final int BUFFER_CONST = 24;
	private static final int FACTORBASE_CONST = 1000;

	private BigInteger N;
	// private RVal[] rValArray;

	public Algo(BigInteger N) {
		// this.N = N;
		//
		// // generate factorbase
		// generateFactorbase(FACTORBASE_CONST);
		// fSize = F.length;
		// L = fSize + BUFFER_CONST;

	}

	public static void main(String[] args) {
		BigInteger N = new BigInteger("323");
		generateFactorbase(FACTORBASE_CONST);
		int L = F.length + BUFFER_CONST;
		RArray rArray = new RArray(L, N, F);
		RVal[] rValArray = rArray.getArray();
		int[][] M;
		int i = 0;
		for (i = 0; i < rValArray.length; i++) {
			
		}

		System.out.println(getFactors());
	}

	/* HELPER METHODS */
	private void checkQ(BigInteger q, BigInteger p) {
		// check that each is a 12 digit prime number, and that their product =
		// N
		System.out.println("q = " + q.toString() + "  isProbablyPrime: " + q.isProbablePrime(certainty));

		System.out.println("p = " + p.toString() + " isProbablyPrime: " + p.isProbablePrime(certainty));

		System.out.println(N.toString() + " == " + q.multiply(p).toString() + " ?");
	}

	/*
	 * 
	 */
	public static BigInteger[] getFactors() {
		// // get all r values, aka populate rValArray
		// RArray rArray = new RArray(L, N, F);
		// rValArray = rArray.getArray();
		//
		// // TODO Get x*M = 0 Solutions
		// SolutionsManager sm = new SolutionsManager();
		//
		// // TODO cycle through above Solutions until gcd(y-x,N) = p or
		// // q. Send that p or q to testing
		// // Tentatively finished - must edit to fit Gaussian implementation
		// SolutionsProcessor solnProcessor = new SolutionsProcessor(rValArray,
		// F, sm, N);
		// BigInteger q = solnProcessor.findFactor();
		//
		// // TODO Test that solution is correct
		// BigInteger p = N.divide(q);
		// checkQ(q, p);

		return null;
	}

	/*
	 * Generate array of primes (code adapted from project description)
	 */
	private static void generateFactorbase(int size) {
		int n = 0;
		int i, j, k;
		F = new int[size]; // degree of 24

		for (i = 2; n < size; ++i) {
			k = (int) (Math.sqrt((double) i) + 1);

			for (j = 0; j < n && F[j] <= k; ++j) {
				if ((i % F[j]) == 0) {
					j = 0;
					break;
				}
			}

			if (j != 0 || n == 0) {
				F[n++] = i;
			}
		}
	}
}
