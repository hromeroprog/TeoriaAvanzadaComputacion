import org.junit.platform.engine.support.descriptor.FileSystemSource;
import uc3m.tac.algoritmos.AKS;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * @author Vincent
 *
 * Bugs fixing and modifications for educational purposes @uc3m
 */

public class AksSimple {

    public static void main(String[] args) {
        
        BigInteger n = BigInteger.probablePrime(8, new SecureRandom());
        System.out.println(n);
        // Creating an instance of the AKS algorithm
        AKS aks = new AKS(n);
        aks.setVerbose(false);
        long start = System.currentTimeMillis();
        boolean result = aks.isPrime();
        long end = System.currentTimeMillis();

        System.out.println(result);
        System.out.println("In " + (end - start) + " ms");

        int bits = 8;

        SecureRandom r = new SecureRandom();
        for (int i = 0; i< 10; i++){
            n = Tools.getBigIntegerFromBits(bits, r);
            System.out.println("Random " + n);

            n = BigInteger.probablePrime(bits, r);
            System.out.println("Random prime " + n);
        }

    }
}
