package timers;

import tools.Tools;
import uc3m.tac.algoritmos.AKS;

import javax.swing.*;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.*;

public class ObtainRAndCheckMCDTiming {
    static int minBits = 40;
    static int maxBits = 102;
    static int iterations = 5;

    public static void main(String[] args) throws IOException {
        SecureRandom rand = new SecureRandom();
        ArrayList<BigInteger> primes = new ArrayList<>();
        ArrayList<BigInteger> composites = new ArrayList<>();
        ArrayList<Long> primes_times = new ArrayList<>();
        ArrayList<Long> composites_times = new ArrayList<>();
        ArrayList<Long> mcd_primes_times = new ArrayList<>();
        ArrayList<Long> mcd_composites_times = new ArrayList<>();

        for (int bits = minBits; bits < maxBits; bits += 2) {
            System.out.print("\nBits...." + bits);
            for (int i = 0; i < iterations; i++) {
                BigInteger n = BigInteger.probablePrime(bits, rand);
                AKS a = new AKS(n);
                System.out.print(".");
                long start = System.currentTimeMillis();
                BigInteger r = a.obtainR();
                long end = System.currentTimeMillis();
                primes.add(n);
                primes_times.add(end - start);
                start = System.currentTimeMillis();
                a.checkMCD(r);
                end = System.currentTimeMillis();
                mcd_primes_times.add(end-start);

                n = Tools.getBigIntegerFromBits(bits, rand);
                a = new AKS(n);
                System.out.print(".");
                start = System.currentTimeMillis();
                r = a.obtainR();
                end = System.currentTimeMillis();
                composites.add(n);
                composites_times.add(end - start);
                start = System.currentTimeMillis();
                a.checkMCD(r);
                end = System.currentTimeMillis();
                mcd_composites_times.add(end-start);
            }
            System.out.print(primes_times.get(primes_times.size()-1) + "\t");
            System.out.print(mcd_primes_times.get(mcd_primes_times.size()-1));
        }
        Tools.exportCSV("./outputs/obtainR/primes.csv", primes, primes_times);
        Tools.exportCSV("./outputs/obtainR/composites.csv", composites, composites_times);
        Tools.exportCSV("./outputs/checkMCD/primes.csv", primes, mcd_primes_times);
        Tools.exportCSV("./outputs/checkMCD/composites.csv", composites, mcd_composites_times);
    }
}
