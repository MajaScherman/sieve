package src;

import static org.junit.Assert.*;


import java.math.BigInteger;
import java.util.Arrays;

import org.junit.Test;

// NOTE This test class is uuuuuuggggllly
public class TestRArray {
	
	@Test
	public void testRGenerator() {
		BigInteger N = new BigInteger("16637");
		final int F[] = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29 };
		final int L = 12; // TODO L=2^10

		BigInteger j = new BigInteger("2");
		BigInteger k = new BigInteger("3");
		BigInteger r = new BigInteger("225");
		BigInteger rSquareMod = new BigInteger("714");
		
		RArray rArray = new RArray(L, N, F);
		
		RVal rVal = rArray.rGenerator(j,k);
		
		assertEquals("rVal.getR", r.intValue(), rVal.getR().intValue());
		assertEquals("rVal.getRSquareMod", rSquareMod.intValue(), rVal.getRSquareMod().intValue());
		assertEquals("binary[] length", 10, rVal.getBinaryRow().length);
	}
	
	// for each number, check that binary row, exponent row, and isSmooth are as expected
	@Test
	public void testFactorR() {
		BigInteger N = new BigInteger("16637");
		final int F[] = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29 };
		final int L = 12; // TODO L=2^10
		
		BigInteger j;
		BigInteger k;
		RVal rVal;
		byte isSmooth;
		
		byte[] bin1 = {1,1,0,1,0,0,1,0,0,0};
		byte[] bin2 = {1,1,0,0,0,0,0,0,0,0};
		byte[] bin3 = {1,0,0,0,0,0,0,1,0,0};
		
		short[] exp1 = {1,1,0,1,0,0,1,0,0,0};
		short[] exp2 = {1,1,0,0,0,0,0,0,0,0};
		short[] exp3 = {3,0,0,2,0,0,0,1,0,0};
		
		RArray rArray = new RArray(L, N, F);		
		
		// A smooth number where binary[] == exponents[]
		j = new BigInteger("2");
		k = new BigInteger("3");
		
		rVal = rArray.rGenerator(j,k);
		isSmooth = rArray.factorR(rVal);
		
		assertEquals("Smoothness", 1, isSmooth);
		assertTrue(Arrays.equals( bin1, rVal.getBinaryRow()));
		assertTrue(Arrays.equals( exp1, rVal.getExponentRow()));
		System.out.println( Arrays.toString(rVal.getBinaryRow()));
		System.out.println( Arrays.toString(rVal.getExponentRow()) + "\n");
		
		// A not smooth number
		k = new BigInteger("2");
		
		rVal = rArray.rGenerator(j, k);
		isSmooth = rArray.factorR(rVal);
		
		assertEquals("Smoothness", 0, isSmooth);
		assertTrue(Arrays.equals( bin2, rVal.getBinaryRow()));
		assertTrue(Arrays.equals( exp2, rVal.getExponentRow()));
		System.out.println( Arrays.toString(rVal.getBinaryRow()));
		System.out.println( Arrays.toString(rVal.getExponentRow()) + "\n");
		
		// A smooth number with exponents who have multiplicity > 1
		j = new BigInteger("8");
		k = new BigInteger("13");
		
		rVal = rArray.rGenerator(j, k);
		isSmooth = rArray.factorR(rVal);
		
		assertEquals("Smoothness", 1, isSmooth);
		assertTrue(Arrays.equals( bin3, rVal.getBinaryRow()));
		assertTrue(Arrays.equals( exp3, rVal.getExponentRow()));
		System.out.println( Arrays.toString(rVal.getBinaryRow()));
		System.out.println( Arrays.toString(rVal.getExponentRow()) + "\n");
		
	}
	
	// What sort of asserts can we put here? Now I have it print the items
	// for us to manually check
	@Test
	public void testGetArray() {
		BigInteger N = new BigInteger("16637");
		final int F[] = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29 };
		final int L = 12; // TODO L=2^10
		
		RArray rArray = new RArray(L, N, F);
		rArray.getArray();
		
		RVal[] rValArray = rArray.getRValArray();
		
		for (RVal rVal : rValArray) {
			String r = rVal.getR().toString();
			String rSquareMod = rVal.getRSquareMod().toString();
			String binArray = Arrays.toString(rVal.getBinaryRow());
			String expArray = Arrays.toString(rVal.getExponentRow());
			int largestIdx = rVal.getLargestFactorIdx();
			System.out.println(r + " | " + rSquareMod + " | " +
					binArray + " ;  " + expArray + " ;  idx: " + largestIdx);
		}
		System.out.println();
	}
	
	@Test
	public void testCheckIfDuplicate() {
		BigInteger N = new BigInteger("16637");
		final int F[] = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29 };
		final int L = 12; // TODO L=2^10
		final int fSize = F.length;
		
		BigInteger j;
		BigInteger k;
		RVal[] rValArray = new RVal[4];
		
		assertTrue(rValArray[3] == null);
		
		RArray rArray = new RArray(L, N, F);
		
		j = new BigInteger("2");
		k = new BigInteger("3");
		RVal rVal1 = rArray.rGenerator(j, k);
		rArray.factorR(rVal1);
		rValArray[0] = rVal1;
		
		j = new BigInteger("4");
		k = new BigInteger("4");
		RVal rVal2 = rArray.rGenerator(j, k);
		rArray.factorR(rVal2);
		rValArray[1] = rVal2;
		
		j = new BigInteger("3");
		k = new BigInteger("5");
		RVal rVal3 = rArray.rGenerator(j, k);
		rArray.factorR(rVal3);
		rValArray[2] = rVal3;
		
		j = new BigInteger("4");
		k = new BigInteger("5");
		RVal testValGood = rArray.rGenerator(j, k);
		rArray.factorR(testValGood);
		
		BigInteger r = new BigInteger("395");
		byte[] binary = {0,0,0,0,0,1,0,0,0,0};
		RVal testValBad = new RVal(r, 10);
		testValBad.setBinaryRow(binary);
		
		rArray.setRValArray(rValArray);
		
		rArray.checkIfDuplicate(3,testValBad);
		assertTrue(rValArray[3] == null);
		
		rArray.checkIfDuplicate(3, testValGood);
		assertTrue(rValArray[3] == testValGood);
	}
}
