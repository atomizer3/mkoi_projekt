package com.mkoi.prime;

import com.sun.corba.se.impl.orbutil.closure.Constant;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Date;

/**
 * Created by Tomek on 2014-03-30.
 */
public class Fermat extends MainApi{
    static SelfMath math = new SelfMath();
    static private String stepName;
    public static long[] prime_tab = new long[]{
              2,  3,  5,  7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67,
             71, 73, 79, 83, 89, 97,101,103,107,109,113,127,131,137,139,149,151,157,163,
            167,173,179,181,191,193,197,199,211,223,227,229,233,239,241,251,257,263,269,
            271,277,281,283,293,307,311,313,317,331,337,347,349,353,359,367,373,379,383,
            389,397,401,409,419,421,431,433,439,443,449,457,461,463,467,479,487,491,499,
            503,509,521,523,541,547,557,563,569,571,577,587,593,599,601,607,613,617,619,
            631,641,643,647,653,659,661,673,677,683,691,701,709,719,727,733,739,743,751,
            757,761,769,773,787,797,809,811,821,823,827,829,839,853,857,859,863,877,881,
            883,887,907,911,919,929,937,941,947,953,967,971,977,983,991,997,1009};
    public static BigInteger readNumberFromFile(String file_path) {
        Path path = Paths.get(file_path);
        BigInteger ret;
        int tmp;
        try{
            byte[] data = Files.readAllBytes(path);
            for (int i = 0; i < data.length; i++) {
                tmp = data[i] & 0xFF;
                tmp = tmp * 1;
                data[i] = (byte)tmp;
            }
            ret = new BigInteger(data);
            return ret;
        } catch (Exception e){
            e.printStackTrace();
            return BigInteger.ZERO;
        }
    }

    public void logMsg(String message){
        Date date = new Date();
        textArea.setText(textArea.getText()+
                date.toString().substring(11,20)+
                stepName+" "+message+'\n');
    }
    private byte[] convertPrime(long prime){
        byte[] ret = new byte[Long.toString(prime).getBytes().length];
        for (int i = 0; i < Long.toString(prime).getBytes().length; i++) {
            ret[i] = Long.toString(prime).getBytes()[i];
        }
//        System.out.println("a: "+Long.toString(prime).getBytes()[1]+" "+Long.toString(prime).getBytes().length);
        return ret;
    }
    private byte[] convertPrime_s(String prime){
        byte[] ret = new byte[prime.length()];
        for (int i = 0; i < prime.length(); i++) {
            ret[i] = prime.getBytes()[i];
            System.out.println(ret[i]);
        }
//        System.out.println("a: "+Long.toString(prime).getBytes()[1]+" "+Long.toString(prime).getBytes().length);
        return ret;
    }
    public String check_prime(BigInteger p, int MAX_ATTEMPT){
        System.out.println(p);
        BigInteger a;
        byte[] dividedBy;
        //check if input value is Carmichael number
        stepName = new String("01: Carmichael check:");
        logMsg("\t checking division by first 169 prime numbers to eliminate most Carmichael numbers");
        for (int i = 0; i < 169;i++){
//            logMsg("\t checking division by "+new String(String.valueOf(prime_tab[i])));
            dividedBy = convertPrime(prime_tab[i]);
            if (p.mod(new BigInteger(dividedBy)) == BigInteger.ZERO ){
                stepName = new String("99: FALSE:");
                logMsg("\t failed - given number is divided by "+prime_tab[i]);
                logMsg("\t Result - FALSE");
                return "FALSE";
            }
        }
        //Fermat-method check for values above 1009, MAX_ATTEMPT times
        stepName = new String("02: Fermat algorithm:");
        if (p.compareTo(BigInteger.valueOf(1009)) == 1){
            logMsg("Invoking Fermat algorithm "+MAX_ATTEMPT+" times");
            for (int i = 0; i < MAX_ATTEMPT; i++){
                logMsg("\t iteration " + i);
                logMsg("\t generating random value from 2 to p range");
                a = math.random_from_range(p);
                logMsg("\t a = "+a);
                logMsg("\t checking GCD value from a and given prime number");
                BigInteger gcdRet = p.gcd(a);
                logMsg("\t GCD result : "+gcdRet);
                if (gcdRet.compareTo(BigInteger.ONE) == 0){
                    logMsg("\t checking a power (p - 1) modulo p to provide Fermat's Little Theorem");
                    BigInteger modPowRet = a.modPow(p.subtract(BigInteger.ONE),p);
                    logMsg("\t result of modulo power : "+modPowRet);
                    if (modPowRet.compareTo(BigInteger.ONE) != 0){
                        stepName = new String("99: FALSE:");
                        logMsg("\t test failed - given number is not satisfying Fermat equation");
                        logMsg("\t Result - FALSE");
                        return "FALSE";
                    }
                } else {
                    stepName = new String("99: FALSE:");
                    logMsg("\t failed - given number is not prime");
                    logMsg("\t Result - FALSE");
                    return "FALSE";
                }
            }
        } else {
            stepName = new String("99: FALSE:");
            logMsg("\t failed - given number is not prime");
            logMsg("\t Result - FALSE");
            return "FALSE";
        }
        stepName = new String("100: TRUE:");
        logMsg("\t success - given number is (probably) prime");
        logMsg("\t Result - SUCCESS");
        return "TRUE";
    }
}
