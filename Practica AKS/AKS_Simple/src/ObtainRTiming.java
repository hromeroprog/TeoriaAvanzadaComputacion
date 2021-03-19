import uc3m.tac.algoritmos.AKS;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

public class ObtainRTiming {
    static int minBits = 40;
    static int maxBits = 60;
    static int iterations = 5;

    public static void main(String[] args) throws IOException {
        SecureRandom r = new SecureRandom();
        ArrayList<Integer> primes_bits = new ArrayList<>();
        ArrayList<Integer> composites_bits = new ArrayList<>();
        ArrayList<Long> primes_times = new ArrayList<>();
        ArrayList<Long> composites_times = new ArrayList<>();


        for (int bits= minBits; bits < maxBits; bits+=2){
            long cumulativeSumPrimes = 0;
            long cumulativeSumComposites = 0;
            System.out.println("Bits...." + bits);
            for( int i = 0; i < iterations; i++ )
            {
                BigInteger n = BigInteger.probablePrime(bits, r);
                AKS a = new AKS(n);
                System.out.print(".");
                long start = System.currentTimeMillis();
                a.isPower();
                long end = System.currentTimeMillis();
                cumulativeSumPrimes += ((end-start)/1000);

                n = Tools.getBigIntegerFromBits(bits, r);
                a = new AKS(n);
                System.out.print(".");
                start = System.currentTimeMillis();
                a.isPower();
                end = System.currentTimeMillis();
                cumulativeSumComposites += ((end-start)/1000);
            }
            primes_bits.add(bits);
            composites_bits.add(bits);
            System.out.println(cumulativeSumPrimes);
            primes_times.add(cumulativeSumPrimes/iterations);
            composites_times.add(cumulativeSumComposites/iterations);
        }
        Tools.exportCSV("./outputs/obtainR/primes.csv", primes_bits, primes_times);
        Tools.exportCSV("./outputs/obtainR/composites.csv", composites_bits, composites_times);
    }
}
