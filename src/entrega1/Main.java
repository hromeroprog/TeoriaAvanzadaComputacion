package entrega1;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {

	public static BigInteger factorizationReductionAlgorithm(BigInteger test) {
		Set<BigInteger> set = new HashSet<BigInteger>();
		for (BigInteger bi = BigInteger.valueOf(2); bi.compareTo(test) <= 0; bi = bi.add(BigInteger.ONE))
			if (test.mod(bi).equals((BigInteger.ZERO))) {
				test = test.divide(bi);
				set.add(bi);
			}

		BigInteger result = BigInteger.ONE;
		for (BigInteger factor : set) {
			result = result.multiply(factor);
		}
		return result;
	}

	public static TestStructure usePrimalityTest(BigInteger test) {
		long start_timer = System.currentTimeMillis();
		BigInteger reduction = Main.factorizationReductionAlgorithm(test);
		long end_timer = System.currentTimeMillis();
		return new TestStructure(end_timer - start_timer, reduction);
	}

	public static void generateTest(int cases_per_size, int lowest_size, int highest_size, String filename)
			throws IOException {
		ArrayList<BigInteger> numbers = new ArrayList<>();
		ArrayList<Long> times = new ArrayList<>();
		ArrayList<BigInteger> reductions = new ArrayList<>();

		for (int i = 0; i < cases_per_size; i++) {
			for (int size_of_number = lowest_size; size_of_number < highest_size; size_of_number++) {
				BigInteger test = Tools.getRandomBigPrime(size_of_number);
				TestStructure result = usePrimalityTest(test);
				numbers.add(test);
				times.add(result.time);
				reductions.add(result.result);
			}
		}
		Tools.exportCSV("./" + filename + ".csv", numbers, times, reductions);
	}

	public static void main(String[] args) throws IOException {
		BigInteger big = new BigInteger("10");
		System.out.println(factorizationReductionAlgorithm(big));
	}
}
