package timers;

import tools.Tools;
import uc3m.tac.algoritmos.AKS;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

public class TotientTiming {
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
                System.out.print(".");
                long start = System.currentTimeMillis();
                AKS.totient(n);
                long end = System.currentTimeMillis();
                primes.add(n);
                primes_times.add(end-start);

                n = Tools.getBigIntegerFromBits(bits, r);
                System.out.print(".");
                start = System.currentTimeMillis();
                AKS.totient(n);
                end = System.currentTimeMillis();
                composites.add(n);
                composites_times.add(end-start);
            }
            System.out.print(primes_times.get(primes_times.size()-1) + "\t");
        }
        Tools.exportCSV("./outputs/totient/primes.csv", primes, primes_times);
        Tools.exportCSV("./outputs/totient/composites.csv", composites, composites_times);
    }
}
