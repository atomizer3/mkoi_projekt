package com.mkoi.prime;

import java.math.BigInteger;

/**
 * Created by Tomasz Nowak on 2014-04-20.
 */
public interface IRandomNumberService {

    BigInteger generateRandomNumber(int bitLength);

    BigInteger generateRandomNumberFromRange(BigInteger b);
}
