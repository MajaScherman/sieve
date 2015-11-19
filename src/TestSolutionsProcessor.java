package src;

import static org.junit.Assert.*;
import java.math.BigInteger;

import org.junit.Test;

public class TestSolutionsProcessor {

}	
//	@Test
//	public void testGetXYDiff() {
//		RVal[] rValArray = new RVal[12]; // 12 = test L
//		rValArray[0] = null;
//		rValArray[1] = null;
//		rValArray[2] = new RVal(new BigInteger("291"),
//						new short[] {3,0,0,0,1,0,1,0,0,0},
//						6);
//		rValArray[3] = null;
//		rValArray[4] = null;
//		rValArray[5] = null;
//		rValArray[6] = null;
//		rValArray[7] = new RVal(new BigInteger("431"),
//						new short[] {1,4,0,0,0,0,1,0,0,0},
//						6);
//		rValArray[8] = new RVal(new BigInteger("458"),
//						new short[] {3,0,1,0,1,0,0,0,1,0},
//						8);
//		rValArray[9] = new RVal(new BigInteger("469"),
//						new short[] {5,0,1,0,0,0,0,0,1,0},
//						8);
//		rValArray[10] = null;
//		rValArray[11] = null;
//		
//		final int F[] = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29 };
//		BigInteger N = new BigInteger("16637");
//		
//		SolutionsProcessor sp = new SolutionsProcessor(rValArray, F, null, N);
//		byte[] soln = {0,0,1,0,0,0,0,1,1,1,0,0};
//		
//		BigInteger diff = sp.getXYDiff(soln);
//		
//		assertTrue("y-x", Integer.toString(4061).equalsIgnoreCase(diff.toString()));
//	}
//	
//	@Test
//	public void testFindFactor() {
//		final String LOG_TAG = "testFindFactor";
//		
//		RVal[] rValArray = new RVal[12]; // 12 = test L
//		rValArray[0] = new RVal(new BigInteger("225"),
//						new short[] {1,1,0,1,0,0,1,0,0,0},
//						6);
//		rValArray[1] = null;
//		rValArray[2] = new RVal(new BigInteger("291"),
//						new short[] {3,0,0,0,1,0,1,0,0,0},
//						6);
//		rValArray[3] = new RVal( new BigInteger("292"), 
//						new short[] {0,3,0,1,1,0,0,0,0,0},
//						4);
//		rValArray[4] = null;
//		rValArray[5] = null;
//		rValArray[6] = null;
//		rValArray[7] = new RVal(new BigInteger("431"),
//						new short[] {1,4,0,0,0,0,1,0,0,0},
//						6);
//		rValArray[8] = new RVal(new BigInteger("458"),
//						new short[] {3,0,1,0,1,0,0,0,1,0},
//						8);
//		rValArray[9] = new RVal(new BigInteger("469"),
//						new short[] {5,0,1,0,0,0,0,0,1,0},
//						8);
//		rValArray[10] = null;
//		rValArray[11] = null;
//		
//		final int F[] = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29 };
//		BigInteger N = new BigInteger("16637");
//		
//		SolutionsManager sm = new SolutionsManager();
//		
//		SolutionsProcessor sp = new SolutionsProcessor(rValArray, F, sm, N);
//		
//		// Test good solution first, should not touch bad solution
//		BigInteger q = sp.findFactor();
//		
//		assertTrue(q.toString().equals("131"));
//		System.out.println(LOG_TAG + "-- Good first - no jump notice");
//		
//		// Test bad solution first
//		byte[][] solutions = { {1,0,1,1,0,0,0,0,0,0,0,0},
//				{0,0,1,0,0,0,0,1,1,1,0,0} };
//		sm.setSolutions(solutions);
//		
//		q = sp.findFactor();
//
//		assertTrue(q.toString().equals("131"));
//		System.out.println(LOG_TAG + "-- Bad first - there should be a jump notice above \n");
//		
//	}
//
//}
