package com.mkoi.prime;

import java.math.BigInteger;

/**
 * Created by Tomasz Nowak on 2014-04-20.
 */
public interface IPrimalityTest {
    boolean probablyPrime(BigInteger number, int repeats);
}
