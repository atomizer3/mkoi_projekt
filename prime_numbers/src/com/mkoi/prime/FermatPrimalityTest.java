package com.mkoi.prime;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Tomek on 2014-03-30.
 */
public class FermatPrimalityTest implements IPrimalityTest {

    private final IRandomNumberService randomNumberService;
    private final ILogger logger;

    public FermatPrimalityTest(ILogger logger, IRandomNumberService randomNumberService) {
        this.logger = logger;
        this.randomNumberService = randomNumberService;
    }

    /**
     * Checks that number is probably prime.
     * @param number the number to check.
     * @param repeats the number of iterations of algorithm.
     * @param enhancedVerbosity if set to true, the logger shows more communicates.
     * @return false when algorithm found a proof that number is not prime
     * true when is probable prime.
     */
    @Override
    public boolean probablyPrime(BigInteger number, int repeats, boolean enhancedVerbosity)  {
        System.out.println(number);
        BigInteger a;



        if(logger!=null && enhancedVerbosity) {
            logger.log("Invoking Fermat algorithm " + repeats + " times");
        }
        //Fermat-method check, 'repeats' times
        for (int i = 0; i < repeats; i++) {
            if(logger!=null && enhancedVerbosity) {
                logger.setStepName(String.format("Fermat algorithm (%d):", i));
                logger.log("\t iteration " + i);
                logger.log("\t generating random value from 2 to p range");
            }
            a = randomNumberService.generateRandomNumberFromRange(number);
            if(logger!=null && enhancedVerbosity) {
                logger.log(String.format("\t checking %s power (p - 1) modulo p to provide Fermat's Little Theorem", a.toString()));
            }
            //calculate modulo power value
            BigInteger modPowRet = a.modPow(number.subtract(BigInteger.ONE), number);
            if(logger!=null && enhancedVerbosity) {
                logger.log("\t result of modulo power : " + modPowRet);
            }
            //compare with 1 and making a decision
            if (modPowRet.compareTo(BigInteger.ONE) != 0) {
                if(logger!=null) {
                    logger.setStepName(new String("FALSE:"));
                    logger.log("\t test failed - given number is not satisfying Fermat equation");
                    logger.log("\t Result - FALSE");
                }
                return false;
            }
        }
        if(logger!=null) {
            logger.setStepName(new String("TRUE:"));
            logger.log("\t success - given number is (probably) prime");
            logger.log("\t Result - TRUE");
        }
        return true;
    }
}
