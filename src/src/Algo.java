package src;

import java.math.BigInteger;

public class Algo {
	// TODO this will have to change to something else
	private int F[] = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29 };
	private int L = 12; // TODO L=2^10
	private BigInteger N;

	public Algo(BigInteger N) {
		this.N = N;
	}

	private BigInteger rGenerator(BigInteger j, BigInteger k) {
		BigInteger r = squareRoot((N.multiply(k))).add(j);// floor(sqrt(N*k)) +
															// j

		return r;

	}

	/** Calculate the square root of a BigInteger in logarithmic time */
	// naturally floors it?
	public BigInteger squareRoot(BigInteger x) {
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