import tools.Tools;
import uc3m.tac.algoritmos.AKS;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * @author Vincent
 * <p>
 * Bugs fixing and modifications for educational purposes @uc3m
 */

public class AksSimple {

    public static void main(String[] args) {

        BigInteger n = BigInteger.probablePrime(10, new SecureRandom());
        System.out.println(n);
        // Creating an instance of the AKS algorithm
        n = new BigInteger("859");
        AKS aks = new AKS(n);
        aks.setVerbose(false);
        long cumulated = 0;
        boolean result = false;
        for (int i = 0; i < 2; i++) {
            long start = System.currentTimeMillis();
            result = aks.isPrime();
            long end = System.currentTimeMillis();
            System.out.println("In " + (end - start) + " ms");
            cumulated += end - start;
        }

        System.out.println(result);
        System.out.println("In " + (cumulated / 15) + " ms");

        BigInteger value = new BigInteger("8");
        System.out.println("Valor: " + value + "  sqrt: " + Tools.sqrt(value));


        System.out.println();

        n = BigInteger.probablePrime(55, new SecureRandom());
        System.out.println("Totient");
        System.out.println(n);
        long start = System.currentTimeMillis();
        BigInteger tot = AKS.totient(n);
        long end = System.currentTimeMillis();
        System.out.println(tot);
        System.out.println(end - start + "ms");

    }
}
