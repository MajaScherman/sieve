package src;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SolutionsProcessor {
	RVal[] rValArray;
	File file;
	int[] F;
	BigInteger N;

	public SolutionsProcessor(RVal[] rValArray, int[] F, BigInteger N) {
		// TODO somehow get hold of a solution.
		// This depends on how we implement Gauss stuff

		this.rValArray = rValArray;
		this.F = F;
		// this.file = file;
		this.N = N;
	}

	public BigInteger findFactor(File file) throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		// ArrayList<short> row = new ArrayList<short>();
		byte[] soln = new byte[rValArray.length];

		sc.nextInt();

		int i;
		for (i = 0; i < rValArray.length; i++) {
			soln[i] = sc.nextByte();
		}

		// String x = sc.next();
		int counter = 0;
		while (true) {
			BigInteger xYDiff = getXYDiff(soln);
			BigInteger gcd = N.gcd(xYDiff); // gcd(y-x)

			if (gcd.compareTo(BigInteger.ONE) != 0 && N.compareTo(gcd) != 0) {
				sc.close();
				return gcd;
			}
			System.out.println("Jump notice. " + ++counter);

			for (i = 0; i < rValArray.length; i++) {
				soln[i] = sc.nextByte();
			}
		}
	}

	public BigInteger getXYDiff(byte[] soln) {
		BigInteger x = new BigInteger("1"); // left side of the equation
		BigInteger y = new BigInteger("1"); // right side of the equation

		BigInteger current = null;
		RVal rVal = null; // current RVal to get values from

		int largestIdx; // limit for building y
		int newExp; // (stored exponent)/2, to build y

		// build x and y
		for (int i = 0; i < soln.length; i++) {
			if (soln[i] == 1) { // if this r appears in solution
				rVal = rValArray[i];
				largestIdx = rVal.getLargestFactorIdx() + 1;

				x = x.multiply(rVal.getR());

				for (int j = 0; j < largestIdx; j++) {
					if ((newExp = rVal.getExponent(j)) != 0) {

						current = new BigInteger(Integer.toString(F[j]));
						current = current.pow(newExp);

						y = y.multiply(current);
					}
				}
			}
		}

		y = squareRoot(y);
		return y.subtract(x).mod(N); // y-x
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