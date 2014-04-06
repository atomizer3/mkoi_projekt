package com.mkoi.prime;

import java.math.BigInteger;
import java.util.Random;

 /**
 * Created by Tomek on 2014-03-30.
 */
public class SelfMath {
    public long GCD(long a, long b){
        long tmp;
        while (b != 0){
            tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }
    public long power_modulo(long a,long e,long n){//a^e mod n
        long ret = 1;
        for (long m = 1; m != 0; m<<=1){
            if ((e & m) != 0){
                ret = multiply_modulo(ret,a,n);
            }
            a = multiply_modulo(a,a,n);
        }
        return ret;
    }
    private long multiply_modulo(long a, long b, long n){//a*b mod n
        long ret = 0;
        long m = 1;
        while (m != 0){
            if ((b & m) != 0){
                ret = (ret + a) % n;
            }
            a = (a << 1) % n;
            m = m << 1;
        }
        return ret;
    }
    public BigInteger random_from_range(BigInteger b){
        Random rand = new Random();
        BigInteger result;
        do {
            result = new BigInteger(b.bitLength(), rand);
//            for (int i = 0; i < result.toByteArray().length; i++) {
//                System.out.println(result.toByteArray()[i]);
//            }
//            System.out.println(result.compareTo(b));
        } while(result.compareTo(b) >= 0 || result.compareTo(new BigInteger("2")) <  0);
        return result;
    }
}
