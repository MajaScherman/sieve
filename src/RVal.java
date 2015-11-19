/*
 * This object class holds all required data for a single r value. The fields
 * are as follows:
 *
 * int[] binary : the binary row which will be written to a file and sent to
 *                the gauss stage for solution generation. populated in
 *                createRList
 * int[] exponents : the unreduced row of exponents. Used in solution
 *                   validation to form y in   x^2 = y^2 mod N. Populated in
 *                   createRList. Note that each value is stored as half the
 *                   true value, since we divide the exponents by 2 to achieve
 *                   a Y^2 form.
 * BigInteger r : the "namesake" of this object. Used in solution validation to
 *                form x in x^2 = y^2 mod N. Populated upon instantiation.
 * BigInteger rSquareMod : the value of r^2 mod N. Used immediately after
 *                         rGeneration to populate binary and exponents.
 *                         Populated in rGenerator.
 * int largestFactorIdx : this is an optimization field. When the last factor is
 *                        stored, its index in factorbase F is loaded into this
 *                        field so that solution validation can stop at this
 *                        greatest point instead of running through the full
 *                        size of F.
 *
 * Class Algo holds a List of these objects as a global variable, for access in
 * all stages. 
 */

package src;

import java.math.BigInteger;

public class RVal {
    private short[] binary;
    private short[] exponents;
    private BigInteger r;
    private BigInteger rSquareMod;
    private int largestFactorIdx;

    /**
     * 
     * @param r
     * @param size length of the factorbase, also length of binary and
              exponents so that matching in solution validation is possible'
     */
    public RVal(BigInteger r, int size) {
        this.r = r;

        binary = new short[size];
        exponents = new short[size];
    }
    
    public RVal(BigInteger r, short[] exponents, int idx) {
    	this.r = r;
    	this.exponents = exponents;
    	this.largestFactorIdx = idx;
    }
    
    /**
     * Setters
     */
    
    public void setBinaryValue( int idx, short val) {
        binary[idx] = val;
    }

    public void setExponentsValue( int idx, short val) {
        exponents[idx] = val;
    }

    public void setRSquareMod( BigInteger rSquareMod) {
        this.rSquareMod = rSquareMod;
    }

    public void setLargestFactorIdx( int largestFactorIdx) {
        this.largestFactorIdx = largestFactorIdx;
    }

    /**
     * Getters
     */

    public short[] getBinaryRow() {
        return binary;
    }
    
    public short[] getExponentRow() {
    	return exponents;
    }

    public int getExponent(int idx) {
        return exponents[idx];
    }

    public int getLargestFactorIdx() {
        return largestFactorIdx;
    }

    public BigInteger getR() {
        return r;
    }

    public BigInteger getRSquareMod() {
        return rSquareMod;
    }

}
