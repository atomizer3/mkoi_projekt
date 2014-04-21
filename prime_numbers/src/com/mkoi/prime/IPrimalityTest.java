package com.mkoi.prime;

import java.math.BigInteger;

/**
 * Created by Tomasz Nowak on 2014-04-20.
 */

/**
 * Provides basic interface for testing primality of numbers.
 */
public interface IPrimalityTest {
    /**
     * Checks that number is probably prime.
     * @param number the number to check.
     * @param repeats the number of iterations of algorithm.
     * @return false when algorithm found a proof that number is not prime
     * true when is probable prime.
     */
    boolean probablyPrime(BigInteger number, int repeats);
}
