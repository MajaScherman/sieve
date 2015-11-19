package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class Algo {
	private static int certainty = 1000;
	private static int F[];
	private static final int BUFFER_CONST = 10;
	private static final int FACTORBASE_CONST = 1024;
	private static final String N_const = "220744554721994695419563";

	public static void main(String[] args) {
		BigInteger N = new BigInteger(N_const);
		BigInteger q, p;
		generateFactorbase(FACTORBASE_CONST);
		int L = F.length + BUFFER_CONST;
		RArray rArray = new RArray(L, N, F);
		RVal[] rValArray = rArray.getArray();
		Runtime rn = Runtime.getRuntime();

		// Write matrix to in-file for Gaussian Elimination
		int i, j;
		PrintWriter writer;
		try {
			writer = new PrintWriter("M.in", "ASCII");
			writer.println(rValArray.length + " " + F.length);// (L+10)*L
			for (i = 0; i < rValArray.length; i++) {
				short[] current = rValArray[i].getExponentRow();
				for (j = 0; j < current.length - 1; j++) {
					writer.print(current[j] + " ");
				}
				if (i != rValArray.length - 1) {
					writer.println(current[j]);
				} else {
					writer.print(current[j]);
				}

			}
			writer.close();

		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// Call RunTime to receive Solutions file
		try {
			rn.exec("./GaussBin.o M.in Solns.txt");
			File solutionsFile = new File("Solns.txt");
			SolutionsProcessor solnProc = new SolutionsProcessor(rValArray, F, N);
			q = solnProc.findFactor(solutionsFile);
			p = N.divide(q);
			presentation(q, p);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* HELPER METHODS */
	private static void presentation(BigInteger q, BigInteger p) {
		// check that each is a 12 digit prime number, and that their product =
		// N
		System.out.println("q = " + q.toString() + "  isProbablyPrime: " + q.isProbablePrime(certainty));
		System.out.println("p = " + p.toString() + "  isProbablyPrime: " + p.isProbablePrime(certainty));
		System.out.println(q.toString() + ", " + p.toString());
	}

	/**
	 * Generate array of primes (code adapted from project description)
	 * 
	 * @param size
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
