import uc3m.tac.algoritmos.AKS;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Time our src.main.java.AKS implementation
 * 
 * @author Vincent
 *
 * Bugs fixing and modifications for educational purposes @uc3m
 */
public class AksTiming 
{

	static int maxBits = 64;
	static int iterations = 32;
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		
		SecureRandom r = new SecureRandom();
		
		for( int bits = 8; bits <= maxBits; bits += 2 )
		{
			long cumulativeSum;

			cumulativeSum = 0;
			System.out.print("Detecting primes....");
			for( int i = 0; i < iterations; i++ )
			{
				BigInteger n = BigInteger.probablePrime(bits, r);
				AKS a = new AKS(n);
				System.out.print(".");
				long start = System.currentTimeMillis();
				if( !a.isPrime() )
					System.out.print("Wrong answer! " + n);
				long end = System.currentTimeMillis();
				
				cumulativeSum += ((end-start)/1000);
			}
			System.out.println("Bits: " + bits + ", time = " + cumulativeSum/iterations + "s");
			
			cumulativeSum = 0;
			System.out.print("Detecting composites");
			for( int i = 0; i < iterations; i++ )
			{
				BigInteger n;
				n = Tools.getBigIntegerFromBits(bits, r);
				
				AKS a = new AKS(n);
				System.out.print(".");
				long start = System.currentTimeMillis();
				if( a.isPrime() )
					System.out.print("Wrong answer! " + n);
				long end = System.currentTimeMillis();

				cumulativeSum += ((end-start)/1000);
			}
			System.out.println("Bits: " + bits + ", time = " + cumulativeSum/iterations + "s");
		}

	}
	
}
