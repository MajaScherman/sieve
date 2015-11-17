package src;

// import static org.junit.Assert.*;
import java.math.BigInteger;

import org.junit.Test;

public class TestAlgo {
	
	@Test
	public void testGenerateFactorbase() {
		System.out.println("testGenerateFactorbase");
		new Algo(new BigInteger("16637"), 50);
		return;
	}

}
