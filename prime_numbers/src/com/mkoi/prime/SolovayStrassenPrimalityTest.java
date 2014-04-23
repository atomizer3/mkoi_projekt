package com.mkoi.prime;

import java.math.BigInteger;

/**
 * Created by Tomasz Nowak on 2014-04-17.
 */

/**
 * Provides Solovay-Strassen primality test for big numbers.
 */
public class SolovayStrassenPrimalityTest implements IPrimalityTest{

    private final IRandomNumberService randomNumberService;
    private final ILogger logger;

    /**
     * Basic constructor with logger and random number generation service.
     * @param logger
     * @param randomNumberService
     */
    public SolovayStrassenPrimalityTest(ILogger logger, IRandomNumberService randomNumberService) {
        this.logger = logger;
        this.randomNumberService = randomNumberService;
    }

    /**
     * Checks that number is probably prime.
     * @param number the number to check.
     * @param repeats the number of iterations of algorithm.
     * @return false when algorithm found a proof that number is not prime
     * true when is probable prime.
     */
    @Override
    public boolean probablyPrime(BigInteger number, int repeats) {
        if( number.equals(new BigInteger("2") ))
            return true;

        for (int i = 0; i < repeats; ++i) {
            if (logger != null) {
                logger.log(String.format("Round %d started...", i));
            }

            BigInteger base = randomNumberService.generateRandomNumberFromRange(number);

            JacobiSymbol jacobiSymbol = new JacobiSymbol(base, number, logger);
            int right = jacobiSymbol.resolve();
            BigInteger left = base.modPow(number.subtract(BigInteger.ONE).divide(new BigInteger("2")), number);
            BigInteger bigRight = number.add(new BigInteger(Integer.toString(right))).mod(number);

            if (!left.equals(bigRight)) {
                if (logger != null) {
                    logger.log(String.format("Found a complexity proof by number: %s", base.toString()));
                }
                return false;
            }
        }
        if (logger != null) {
            logger.log(String.format("Test completed, number is probably prime."));
        }
        return true;
    }
}
