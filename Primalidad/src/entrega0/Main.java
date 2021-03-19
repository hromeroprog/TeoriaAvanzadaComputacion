package entrega0;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class Main {

	public static boolean primalityTest(BigInteger test) {

		boolean isPrime = true;
		for (BigInteger bi = BigInteger.valueOf(2); bi.compareTo(test) < 0; bi = bi.add(BigInteger.ONE)) {
			if (test.mod(bi).equals((BigInteger.ZERO))) {
				isPrime = false;
			}
		}
		return isPrime;
	}

	public static boolean primalityTestImprove1(BigInteger test) {
		BigInteger sqroot = Tools.sqrt(test).add(BigInteger.ONE);
		BigInteger two = BigInteger.valueOf(2L);
		if (test.mod(two).equals(BigInteger.ZERO)) {
			return false;
		}
		for (BigInteger bi = BigInteger.valueOf(3); bi.compareTo(sqroot) < 0; bi = bi.add(two)) {
			if (test.mod(bi).equals(BigInteger.ZERO)) {
				return false;
			}
		}
		return true;
	}

	public static TestStructure usePrimalityTest(BigInteger test, String test_type) {
		long start_timer, end_timer;
		boolean result;
		switch (test_type) {
		case ("basic"):
			start_timer = System.currentTimeMillis();
			result = primalityTest(test);
			end_timer = System.currentTimeMillis();
			break;
		case ("improved_1"):
			start_timer = System.currentTimeMillis();
			result = primalityTestImprove1(test);
			end_timer = System.currentTimeMillis();
			break;
		case ("AKS"):
			start_timer = System.currentTimeMillis();
			AKS myAKS = new AKS(test);
			end_timer = System.currentTimeMillis();
			result = myAKS.isPrime;
			break;
		default:
			start_timer = System.currentTimeMillis();
			result = primalityTest(test);
			end_timer = System.currentTimeMillis();
			break;
		}
		return new TestStructure(end_timer - start_timer, result);
	}

	public static void generateTest(int cases_per_size, int lowest_size, int highest_size, String test_type,
			boolean only_primes, String filename) throws IOException {
		ArrayList<BigInteger> numbers = new ArrayList<>();
		ArrayList<Long> times = new ArrayList<>();
		ArrayList<Boolean> primes = new ArrayList<>();

		for (int size_of_number = lowest_size; size_of_number < highest_size; size_of_number++) {
			for (int i = 0; i < cases_per_size; i++) {
				BigInteger test = only_primes ? Tools.getRandomBigPrime(size_of_number)
						: Tools.getRandomBigInteger(size_of_number);
				TestStructure result = usePrimalityTest(test, test_type);
				numbers.add(test);
				times.add(result.time);
				primes.add(result.isPrime);

				if (i % 5 == 0)
					System.out.println("Finished test of size: " + size_of_number + " -- Number: " + i
							+ " -- Elapsed time: " + result.time);
			}
			System.out.println("Finished tests of size: " + size_of_number);
		}
		Tools.exportCSV("./primality_files/" + filename + ".csv", numbers, times, primes);
		System.out.println("File exported successfully");
	}

	public static void main(String[] args) throws IOException {

		generateTest(50, 3, 10, "basic", false, "basic_comp");
		generateTest(50, 3, 10, "basic", true, "basic_primes");
		generateTest(50, 3, 18, "improved_1", false, "improved_1_comp");
		generateTest(50, 3, 18, "improved_1", true, "improved_1_primes");
		generateTest(50, 3, 18, "AKS", false, "AKS_comp");
		generateTest(50, 3, 18, "AKS", true, "AKS_primes");
	}
}
