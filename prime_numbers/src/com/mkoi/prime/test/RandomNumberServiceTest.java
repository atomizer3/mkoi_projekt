package com.mkoi.prime.test;

import com.mkoi.prime.RandomNumberService;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by Tomasz Nowak on 2014-04-20.
 */
public class RandomNumberServiceTest {

    @Test
    public void testGenerateRandomNumberFromRange() throws Exception {

        Random rand = new Random(0);

        RandomNumberService service = new RandomNumberService(rand);

        BigInteger p = new BigInteger("123");

        BigInteger generated = service.generateRandomNumberFromRange(p);

        Assert.assertTrue("Not less than p", generated.compareTo(p) < 0);
        Assert.assertTrue("Not greater than 1", generated.compareTo(new BigInteger("2")) >= 0);

    }
}
