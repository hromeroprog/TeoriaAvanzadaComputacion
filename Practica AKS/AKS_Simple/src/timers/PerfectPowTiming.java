package timers;

import tools.Tools;
import uc3m.tac.algoritmos.AKS;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

public class PerfectPowTiming {
    static int minBits = 40;
    static int maxBits = 60;
    static int iterations = 5;

    public static void main(String[] args) throws IOException {
        SecureRandom r = new SecureRandom();
        ArrayList<BigInteger> primes = new ArrayList<>();
        ArrayList<BigInteger> composites = new ArrayList<>();
        ArrayList<Long> primes_times = new ArrayList<>();
        ArrayList<Long> composites_times = new ArrayList<>();


        for (int bits= minBits; bits < maxBits; bits+=2){
            System.out.print("\nBits...." + bits);
            for( int i = 0; i < iterations; i++ )
            {
                BigInteger n = BigInteger.probablePrime(bits, r);
                AKS a = new AKS(n);
                System.out.print(".");
                long start = System.currentTimeMillis();
                a.isPower();
                long end = System.currentTimeMillis();
                primes.add(n);
                primes_times.add(end-start);

                n = Tools.getBigIntegerFromBits(bits, r);
                a = new AKS(n);
                System.out.print(".");
                start = System.currentTimeMillis();
                a.isPower();
                end = System.currentTimeMillis();
                composites.add(n);
                composites_times.add(end-start);
            }
        }
        Tools.exportCSV("./outputs/isPower/primes2.csv", primes, primes_times);
        Tools.exportCSV("./outputs/isPower/composites2.csv", composites, composites_times);
    }
}
