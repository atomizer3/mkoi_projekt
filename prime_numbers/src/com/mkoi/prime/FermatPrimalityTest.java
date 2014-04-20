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
    static SelfMath math = new SelfMath();

   /* public static long[] prime_tab = new long[]{
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67,
            71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163,
            167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269,
            271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383,
            389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499,
            503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619,
            631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751,
            757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881,
            883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997, 1009};*/

    public FermatPrimalityTest(ILogger logger, IRandomNumberService randomNumberService) {
        this.logger = logger;
        this.randomNumberService = randomNumberService;
    }

    public static BigInteger readNumberFromFile(String file_path) {
        Path path = Paths.get(file_path);
        BigInteger ret;
        int tmp;
        try {
            byte[] data = Files.readAllBytes(path);
            for (int i = 0; i < data.length; i++) {
                tmp = data[i] & 0xFF;
                tmp = tmp * 1;
                data[i] = (byte) tmp;
            }
            ret = new BigInteger(data);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return BigInteger.ZERO;
        }
    }

    private byte[] convertPrime(long prime) {
        byte[] ret = new byte[Long.toString(prime).getBytes().length];
        for (int i = 0; i < Long.toString(prime).getBytes().length; i++) {
            ret[i] = Long.toString(prime).getBytes()[i];
        }
//        System.out.println("a: "+Long.toString(prime).getBytes()[1]+" "+Long.toString(prime).getBytes().length);
        return ret;
    }

    private byte[] convertPrime_s(String prime) {
        byte[] ret = new byte[prime.length()];
        for (int i = 0; i < prime.length(); i++) {
            ret[i] = prime.getBytes()[i];
            System.out.println(ret[i]);
        }
//        System.out.println("a: "+Long.toString(prime).getBytes()[1]+" "+Long.toString(prime).getBytes().length);
        return ret;
    }

    @Override
    public boolean probablyPrime(BigInteger number, int repeats)  {
        System.out.println(number);
        BigInteger a;
        byte[] dividedBy;

        /*
        * turned off, to show that Fermat test cannot properly check Carmichael numbers

        //check if input value is Carmichael number
        if(logger!=null)
        {
            logger.setStepName(new String("01: Carmichael check:"));
            logger.log("\t checking division by first 169 prime numbers to eliminate most Carmichael numbers");
        }

        for (int i = 0; i < 169; i++) {
//            logger.log("\t checking division by "+new String(String.valueOf(prime_tab[i])));
            dividedBy = convertPrime(prime_tab[i]);
            if (number.mod(new BigInteger(dividedBy)) == BigInteger.ZERO) {
                if(logger!=null) {
                    logger.setStepName(new String("99: FALSE:"));
                    logger.log("\t failed - given number is divided by " + prime_tab[i]);
                    logger.log("\t Result - FALSE");
                }
                return false;
            }
        }*/

        //Fermat-method check for values above 1009, MAX_ATTEMPT times
        if(logger!=null) {
            logger.setStepName(new String("02: Fermat algorithm:"));
        }
        //if (number.compareTo(BigInteger.valueOf(1009)) == 1) {
            if(logger!=null) {
                logger.log("Invoking Fermat algorithm " + repeats + " times");
            }
            for (int i = 0; i < repeats; i++) {
                if(logger!=null) {
                    logger.log("\t iteration " + i);
                    logger.log("\t generating random value from 2 to p range");
                }
                a = randomNumberService.generateRandomNumberFromRange(number);
                if(logger!=null) {
                    logger.log("\t a = " + a);
                    logger.log("\t checking GCD value from a and given prime number");
                }
                /*BigInteger gcdRet = number.gcd(a);
                if(logger!=null) {
                    logger.log("\t GCD result : " + gcdRet);
                }
                if (gcdRet.compareTo(BigInteger.ONE) == 0) {*/
                    if(logger!=null) {
                        logger.log("\t checking a power (p - 1) modulo p to provide Fermat's Little Theorem");
                    }
                    BigInteger modPowRet = a.modPow(number.subtract(BigInteger.ONE), number);
                    if(logger!=null) {
                        logger.log("\t result of modulo power : " + modPowRet);
                    }
                    if (modPowRet.compareTo(BigInteger.ONE) != 0) {
                        if(logger!=null) {
                            logger.setStepName(new String("99: FALSE:"));
                            logger.log("\t test failed - given number is not satisfying Fermat equation");
                            logger.log("\t Result - FALSE");
                        }
                        return false;
                    }
                /*} else {
                    if(logger!=null) {
                        logger.setStepName(new String("99: FALSE:"));
                        logger.log("\t failed - given number is not prime");
                        logger.log("\t Result - FALSE");
                    }
                    return false;
                }*/
            }
       /* } else {
            if(logger!=null) {
                logger.setStepName(new String("99: FALSE:"));
                logger.log("\t failed - given number is not prime");
                logger.log("\t Result - FALSE");
            }
            return false;
        }*/
        if(logger!=null) {
            logger.setStepName(new String("100: TRUE:"));
            logger.log("\t success - given number is (probably) prime");
            logger.log("\t Result - SUCCESS");
        }
        return true;
    }
}
