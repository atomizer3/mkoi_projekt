package com.mkoi.prime;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by Tomasz Nowak on 2014-04-20.
 */

/**
 * Basic BigInteger random number generation class.
 */
public class RandomNumberService implements IRandomNumberService {

    private Random random;
    private final static BigInteger TwoConstant = new BigInteger("2");

    public RandomNumberService(Random random) {
        this.random = random;
    }

    /**
     * Generates random number with exactly length.
     * @param bitLength bit length of generated number.
     * @return generated random number.
     */
    @Override
    public BigInteger generateRandomNumber(int bitLength) {

        return BigInteger.probablePrime(bitLength, random);
    }

    /**
     * Generates random number greater than 1 and lower than b.
     * @param b upper bound for generating number.
     * @return generated random number from range.
     */
    @Override
    public BigInteger generateRandomNumberFromRange(BigInteger b) {
        BigInteger result;
        do {
            result = new BigInteger(b.bitLength(), random);
        } while (result.compareTo(b) >= 0 || result.compareTo(TwoConstant) < 0);
        return result;
    }
}
