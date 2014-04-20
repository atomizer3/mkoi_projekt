package com.mkoi.prime;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by Tomasz Nowak on 2014-04-20.
 */
public class RandomNumberService implements IRandomNumberService {

    private Random random;
    private final static BigInteger TwoConstant = new BigInteger("2");

    public RandomNumberService(Random random) {
        this.random = random;
    }

    @Override
    public BigInteger generateRandomNumber(int bitLength) {

        return BigInteger.probablePrime(bitLength, random);
    }

    @Override
    public BigInteger generateRandomNumberFromRange(BigInteger b) {
        BigInteger result;
        do {
            result = new BigInteger(b.bitLength(), random);
        } while (result.compareTo(b) >= 0 || result.compareTo(TwoConstant) < 0);
        return result;
    }
}
