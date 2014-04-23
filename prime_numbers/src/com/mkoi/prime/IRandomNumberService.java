package com.mkoi.prime;

import java.math.BigInteger;

/**
 * Created by Tomasz Nowak on 2014-04-20.
 */

/**
 * Provides basic BigInteger random number generation.
 */
public interface IRandomNumberService {

    /**
     * Generates random number with exactly length.
     * @param bitLength bit length of generated number.
     * @return generated random number.
     */
    BigInteger generateRandomNumber(int bitLength);

    /**
     * Generates random number greater than 1 and lower than b.
     * @param b upper bound for generating number.
     * @return generated random number from range.
     */
    BigInteger generateRandomNumberFromRange(BigInteger b);
}
