package com.mkoi.prime;

import java.math.BigInteger;

/**
 * Created by Tomasz Nowak on 2014-04-17.
 */
public class JacobiSymbol {

    private final ILogger logger;
    private BigInteger upper;

    private BigInteger lower;

    public BigInteger getUpper() {
        return upper;
    }

    public void setUpper(BigInteger upper) {
        this.upper = upper;
    }

    public BigInteger getLower() {
        return lower;
    }

    public void setLower(BigInteger lower) {
     //   if (!lower.equals(new BigInteger("2")) && lower.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
      //      throw new IllegalArgumentException("2 divides lower");
    //    }
        this.lower = lower;
    }

    public int resolve() {
        if (upper.equals(BigInteger.ZERO))
            return 0;
        if (upper.equals(BigInteger.ONE))
            return 1;

        int rightMostBit = upper.getLowestSetBit();
        BigInteger oddDivisor = upper.shiftRight(rightMostBit);

        int first = resolveTwoOverLower(rightMostBit);

        int third = resolveSign(oddDivisor);

        if (oddDivisor.equals(BigInteger.ONE)) {
            if (this.logger != null) {
                this.logger.log(String.format("%s over %s = %d", upper.toString(), lower.toString(), first * third));
            }
            return first * third;
        }

        int second = new JacobiSymbol(lower.mod(oddDivisor), oddDivisor, logger).resolve();

        if (this.logger != null) {
            this.logger.log(String.format("%s over %s = %d", upper.toString(), lower.toString(), first * second * third));
        }
        return first * second * third;
    }

    public JacobiSymbol(BigInteger upper, BigInteger lower, ILogger logger) {
        this.upper = upper;
        this.logger = logger;
        setLower(lower);
    }

    private int resolveTwoOverLower(int exponent) {
        if (lower.equals(new BigInteger("2")))
            return 0;

        BigInteger result = lower.mod(new BigInteger("8"));
        if (result.equals(BigInteger.ONE) || result.equals(new BigInteger("7"))) {
            return 1;
        }
        return exponent % 2 == 0 ? 1 : -1;
    }

    private int resolveSign(BigInteger oddDivisor) {
        if (lower.mod(new BigInteger("4")).equals(BigInteger.ONE) || oddDivisor.mod(new BigInteger("4")).equals(BigInteger.ONE)) {
            return 1;
        }
        return -1;
    }
}
