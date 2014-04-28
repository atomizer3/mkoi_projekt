package com.mkoi.prime;

import java.math.BigInteger;

/**
 * Created by Tomasz Nowak on 2014-04-17.
 */
public class JacobiSymbol {

    private final ILogger logger;
    private BigInteger upper;
    private BigInteger lower;

    /**
     * Getter for upper value.
     * @return upper value.
     */
    public BigInteger getUpper() {
        return upper;
    }

    /**
     * Setter for upper value.
     * @param upper new upper value.
     */
    public void setUpper(BigInteger upper) {
        this.upper = upper;
    }

    /**
     * Getter for lower value.
     * @return lower value.
     */
    public BigInteger getLower() {
        return lower;
    }

    /**
     * Setter for lower value.
     * @param lower new lower value.
     */

    public void setLower(BigInteger lower) {
     //   if (!lower.equals(new BigInteger("2")) && lower.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
      //      throw new IllegalArgumentException("2 divides lower");
    //    }
        this.lower = lower;
    }

    /**
     * Resolve value of this Jacobi Symbol. It uses recursion for calculating result,
     * based of the law of quadratic reciprocity for Jacobi Symbols.
     * @param enhancedVerbosity if set to true, the logger shows more communicates.
     * @return 1 iff upper is quadratic residue mod lower,
     * -1 iff upper is not quadratic residue mod lower,
     * 0 when gcd(upper, lower) > 1.
     */
    public int resolve(boolean enhancedVerbosity) {
        if (upper.equals(BigInteger.ZERO))
            return 0;
        if (upper.equals(BigInteger.ONE))
            return 1;

        int rightMostBit = upper.getLowestSetBit();
        BigInteger oddDivisor = upper.shiftRight(rightMostBit);

        int first = resolveTwoOverLower(rightMostBit);

        int third = resolveSign(oddDivisor);

        if (oddDivisor.equals(BigInteger.ONE)) {
            if (this.logger != null && enhancedVerbosity) {
                this.logger.log(String.format("%s over %s = %d", upper.toString(), lower.toString(), first * third));
            }
            return first * third;
        }

        int second = new JacobiSymbol(lower.mod(oddDivisor), oddDivisor, logger).resolve(enhancedVerbosity);

        if (this.logger != null && enhancedVerbosity) {
            this.logger.log(String.format("%s over %s = %d", upper.toString(), lower.toString(), first * second * third));
        }
        return first * second * third;
    }

    /**
     * Basic constructor for JacobiSymbol.
     * @param upper upper value in Jacobi Symbol.
     * @param lower lower value in Jacobi Symbol.
     * @param logger additional logger for this object.
     */
    public JacobiSymbol(BigInteger upper, BigInteger lower, ILogger logger) {
        this.upper = upper;
        this.logger = logger;
        setLower(lower);
    }

    /**
     * Resolves the Jacobi Symbol: (2/lower)^exponent. Lower value is equal to this.lower.
     * @param exponent exponent value
     * @return 1 iff 2 is quadratic residue mod lower,
     * -1 iff 2 is not quadratic residue mod lower,
     * 0 when lower = 2.
     */
    private int resolveTwoOverLower(int exponent) {
        if (lower.equals(new BigInteger("2")))
            return 0;

        BigInteger result = lower.mod(new BigInteger("8"));
        if (result.equals(BigInteger.ONE) || result.equals(new BigInteger("7"))) {
            return 1;
        }
        return exponent % 2 == 0 ? 1 : -1;
    }

    /**
     * Calculates (-1)^((lower-1)(oddDivisor-1)/4)
     * @param oddDivisor the oddDivisor of upper
     * @return 1 when lower = 1 mod 4 or upper = 1 mod 4, else returns -1.
     */
    private int resolveSign(BigInteger oddDivisor) {
        if (lower.mod(new BigInteger("4")).equals(BigInteger.ONE) || oddDivisor.mod(new BigInteger("4")).equals(BigInteger.ONE)) {
            return 1;
        }
        return -1;
    }
}
