package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class Algo {
	// TODO this will have to change to something else
	private static int certainty = 1000;
	private static int F[];
	// private int fSize;
	// private int L; // TODO L=2^10
	private static final int BUFFER_CONST = 24;
	private static final int FACTORBASE_CONST = 1000;
	private static final String N_const = "220744554721994695419563";

	// private static BigInteger N;

	public static void main(String[] args) {
		BigInteger N = new BigInteger(N_const);// new BigInteger("16637");
		BigInteger q, p;
		generateFactorbase(FACTORBASE_CONST);

		System.out.println("factorbase ready");
		int L = F.length + BUFFER_CONST;
		RArray rArray = new RArray(L, N, F);
		System.out.println("hej");
		RVal[] rValArray = rArray.getArray();
		Runtime rn = Runtime.getRuntime();
		System.out.println("factorbase ready2");
		// Write matrix to in-file for Gaussian Elimination
		// TODO start element-wise. Ideally optimize by writing files in
		// parallel and then appending
		int i, j;
		PrintWriter writer;
		try {
			writer = new PrintWriter("M.in", "ASCII");
			writer.println(rValArray.length + " " + F.length);// (L+10)*L,
			for (i = 0; i < rValArray.length; i++) {
				//System.out.print(rValArray[i].getRSquareMod() + ", ");
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
			System.out.println("Writer done.");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Call [Runnable] to receive Solutions file
		try {
			Process process = rn.exec("./GaussBin.o M.in Solns.txt");
			// Get the file
			File solutionsFile = new File("Solns.txt");// however you get a file
			System.out.println("Solutions file ready.");
			SolutionsProcessor solnProc = new SolutionsProcessor(rValArray, F, N);
			q = solnProc.findFactor(solutionsFile);
			System.out.println("Ahhhhhhhhhhhhhh.");
			p = N.divide(q);

			checkQ(q, p);
			System.out.println(q.toString() + ", " + p.toString() + " You are Cute <3");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* HELPER METHODS */
	private static void checkQ(BigInteger q, BigInteger p) {
		// check that each is a 12 digit prime number, and that their product =
		// N
		System.out.println("q = " + q.toString() + "  isProbablyPrime: " + q.isProbablePrime(certainty));

		System.out.println("p = " + p.toString() + " isProbablyPrime: " + p.isProbablePrime(certainty));

		// System.out.println(N.toString() + " == " + q.multiply(p).toString() +
		// " ?");
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
