package com.mkoi.prime.test;

import com.mkoi.prime.JacobiSymbol;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

/**
 * Created by Tomasz Nowak on 2014-04-20.
 */
public class JacobiSymbolTest {

    JacobiSymbol symbol;

    @Before
    public void prepare() {
        symbol = new JacobiSymbol(new BigInteger("2"), new BigInteger("3"), null);
    }

    @Test
    public void testResolve2Over() throws Exception {
        JacobiSymbol jacobiSymbol = new JacobiSymbol(new BigInteger("2"), new BigInteger("17"), null);
        Assert.assertEquals("2 over 17 != 1", jacobiSymbol.resolve(true), 1);

        jacobiSymbol = new JacobiSymbol(new BigInteger("2"), new BigInteger("23"), null);
        Assert.assertEquals("2 over 23 != 1", jacobiSymbol.resolve(true), 1);

        jacobiSymbol = new JacobiSymbol(new BigInteger("2"), new BigInteger("11"), null);
        Assert.assertEquals("2 over 11 != -1", jacobiSymbol.resolve(true), -1);

        jacobiSymbol = new JacobiSymbol(new BigInteger("2"), new BigInteger("37"), null);
        Assert.assertEquals("2 over 37 != 1", jacobiSymbol.resolve(true), -1);
    }

    @Test
    public void testResolve() throws Exception {

        JacobiSymbol jacobiSymbol = new JacobiSymbol(new BigInteger("1"), new BigInteger("83"), null);
        Assert.assertEquals("1 over 83 != 1", jacobiSymbol.resolve(true), 1);

        jacobiSymbol = new JacobiSymbol(new BigInteger("12"), new BigInteger("13"), null);
        Assert.assertEquals("12 over 13 != 1", jacobiSymbol.resolve(true), 1);

        jacobiSymbol = new JacobiSymbol(new BigInteger("18"), new BigInteger("19"), null);
        Assert.assertEquals("18 over 19 != -1", jacobiSymbol.resolve(true), -1);

        jacobiSymbol = new JacobiSymbol(new BigInteger("6"), new BigInteger("15"), null);
        Assert.assertEquals("6 over 15 != 0", jacobiSymbol.resolve(true), 0);

    }
}
