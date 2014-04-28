package com.mkoi.prime.test;

import com.mkoi.prime.FermatPrimalityTest;
import com.mkoi.prime.ILogger;
import com.mkoi.prime.RandomNumberService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by Tomasz Nowak on 2014-04-20.
 */
public class FermatPrimalityTestTest {
    RandomNumberService randomNumberService;
    FermatPrimalityTest fermatPrimalityTestTest;

    @Before
    public void prepare() {
        randomNumberService = new RandomNumberService(new Random(0));
        fermatPrimalityTestTest = new FermatPrimalityTest(new ILogger() {
            @Override
            public void log(String message) {
                System.out.println(message);
            }

            @Override
            public void setStepName(String stepName) {

            }
        }, randomNumberService);
    }

    @org.junit.Test
    public void testProbablyPrime() throws Exception {

        Assert.assertTrue("Testing 2 is prime", fermatPrimalityTestTest.probablyPrime(new BigInteger("2"), 20, true));

        Assert.assertTrue("Testing 3 is prime", fermatPrimalityTestTest.probablyPrime(new BigInteger("3"), 20, true));

        Assert.assertTrue("Testing 12 is not prime", !fermatPrimalityTestTest.probablyPrime(new BigInteger("12"), 20, true));

        Assert.assertTrue("Testing 123 is not prime", !fermatPrimalityTestTest.probablyPrime(new BigInteger("123"), 20, true));

        Assert.assertTrue("Testing 123 is not prime", !fermatPrimalityTestTest.probablyPrime(new BigInteger("123"), 20, true));

        Assert.assertTrue("Testing 131 is prime", fermatPrimalityTestTest.probablyPrime(new BigInteger("131"), 20, true));

        Assert.assertTrue("Testing 393050634124102232869567034555427371542904833 is prime", fermatPrimalityTestTest.probablyPrime(new BigInteger("393050634124102232869567034555427371542904833"), 20, true));

        Assert.assertTrue("Testing 170141183460469231731687303715884105727 is prime", fermatPrimalityTestTest.probablyPrime(new BigInteger("170141183460469231731687303715884105727"), 20, true));

        Assert.assertTrue("Testing 65537 is prime", fermatPrimalityTestTest.probablyPrime(new BigInteger("65537"), 20, true));

        //Carmichael's numbers

        Assert.assertTrue("Testing 8911 is Carmichael's number", !fermatPrimalityTestTest.probablyPrime(new BigInteger("8911 "), 20, true));
    }
}
