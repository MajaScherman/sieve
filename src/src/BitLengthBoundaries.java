/* A simple class to find the size of a BigInteger using the bitLength() 
 * function. We input the upper and lower bound of the 12 digit factors
 * involved in exercises 1 and 2 to determine storage needs as requested in
 * exercise 2. Our assigned N includd for curiosity's sake.
 *
 * code closely adapted from tutorialspoint.com's entry for use of 
 * bitLength().
 *
 */

import java.math.*;

public class BitLengthBoundaries {

    public static void main(String[] args) {

        // Will hold the BigIntegers
        BigInteger lower, upper, assigned;

        // will hold the bit lengths
        int lLower, lUpper, lAssigned;

        lower = new BigInteger("100000000000"); // assign the lower bound
        upper = new BigInteger("999999999999"); // assign the upper bound
        assigned = new BigInteger("220744554721994695419563"); // num to factor

        // use bitLLength
        lLower = lower.bitLength();
        lUpper = upper.bitLength();
        lAssigned = assigned.bitLength();

        String str1 = "Lower bound bitlength = " + lLower;
        String str2 = "Upper bound bitLength = " + lUpper;
        String str3 = "Our 25 digit bitlength = " + lAssigned;

        System.out.println(str1 + "\n" + str2 + "\n" + str3);
    }
}



