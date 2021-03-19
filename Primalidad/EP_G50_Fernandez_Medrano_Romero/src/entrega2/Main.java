package entrega2;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

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

	public static TestStructure usePrimalityTest(BigInteger test) {
		long start_timer, end_timer;
		boolean result;
		start_timer = System.currentTimeMillis();
		result = primalityTestImprove1(test);
		end_timer = System.currentTimeMillis();
		return new TestStructure(end_timer - start_timer, result);
	}

	public static void generateTest(String filename) throws IOException {

		ArrayList<BigInteger> numbers = getNumbersToTest();

		ArrayList<Float> times = new ArrayList<>();
		ArrayList<Boolean> primes = new ArrayList<>();

		for (int i = 0; i < numbers.size(); i++) {
			TestStructure result = usePrimalityTest(numbers.get(i));
			
			times.add((float) result.time / 1000);
			primes.add(result.isPrime);
			System.out.println("Finished test of number: " + numbers.get(i) + " -- Elapsed time: " + result.time
					+ " Finished: " + i + "/" + numbers.size());
		}
		System.out.println("------------------- End -----------------------");

		Tools.exportCSV("./hito3_files/" + filename + ".csv", numbers, times, primes);
		System.out.println("File exported successfully");
	}

	public static ArrayList<BigInteger> getNumbersToTest() {
		ArrayList<BigInteger> numbers = new ArrayList<>(Arrays.asList(new BigInteger("776159"),
				new BigInteger("776161"), new BigInteger("98982599"), new BigInteger("98982601"),
				new BigInteger("9984605927"), new BigInteger("9984605929"), new BigInteger("999498062999"),
				new BigInteger("999498063001"), new BigInteger("99996460031327"), new BigInteger("99996460031329"),
				new BigInteger("9999940600088207"), new BigInteger("9999940600088209"),
				new BigInteger("999999594000041207"), new BigInteger("999999594000041209"),
				new BigInteger("4611685283988009527"), new BigInteger("4611685283988009529"),
				new BigInteger("9223371593598182327"), new BigInteger("9223371593598182329")));
		return numbers;
	}

	public static void main(String[] args) throws IOException {
		generateTest("test_number_hito3");
	}
}
