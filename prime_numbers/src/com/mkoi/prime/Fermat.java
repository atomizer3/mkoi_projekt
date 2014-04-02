package com.mkoi.prime;
/**
 * Created by Tomek on 2014-03-30.
 */
public class Fermat{
    static SelfMath math = new SelfMath();
    public static int[] prime_tab = new int[]{
              2,  3,  5,  7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67,
             71, 73, 79, 83, 89, 97,101,103,107,109,113,127,131,137,139,149,151,157,163,
            167,173,179,181,191,193,197,199,211,223,227,229,233,239,241,251,257,263,269,
            271,277,281,283,293,307,311,313,317,331,337,347,349,353,359,367,373,379,383,
            389,397,401,409,419,421,431,433,439,443,449,457,461,463,467,479,487,491,499,
            503,509,521,523,541,547,557,563,569,571,577,587,593,599,601,607,613,617,619,
            631,641,643,647,653,659,661,673,677,683,691,701,709,719,727,733,739,743,751,
            757,761,769,773,787,797,809,811,821,823,827,829,839,853,857,859,863,877,881,
            883,887,907,911,919,929,937,941,947,953,967,971,977,983,991,997,1009};
    public static String check_prime(long p, int MAX_ATTEMPT){
        long a;
        //check if input value is Carmichael number
        for (int i = 0; i < 168;i++){
            if (p % prime_tab[i] == 0){
                return "FALSE";
            }
        }
        //Fermat-method check for values above 1009, MAX_ATTEMPT times
        if (p > 1009)
        {
            for (int i = 0; i < MAX_ATTEMPT; i++){
                a = math.random_from_range(2,p - 1);
                if ( math.GCD(p,a) !=1 || math.power_modulo(a,p - 1,p) != 1)
                {
                    return "FALSE";
                }
            }
        } else
        {
            return "FALSE";
        }
        return "TRUE";
    }
//    public static void main(String[] args) {
//        System.out.println(check_prime(2999,10));
//    }

}
