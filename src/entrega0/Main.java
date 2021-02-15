package entrega0;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {

	public static BigInteger sqrt(BigInteger x) {
		BigInteger div = BigInteger.ZERO.setBit(x.bitLength() / 2);
		BigInteger div2 = div;
		// Loop until we hit the same value twice in a row, or wind
		// up alternating.
		for (;;) {
			BigInteger y = div.add(x.divide(div)).shiftRight(1);
			if (y.equals(div) || y.equals(div2))
				return y;
			div2 = div;
			div = y;
		}
	}

	public static boolean primalityTest(BigInteger test) {

		boolean isPrime = true;
		for (BigInteger bi = BigInteger.valueOf(2); bi.compareTo(test) < 0; bi = bi.add(BigInteger.ONE))
			if (test.mod(bi).equals((BigInteger.ZERO))) {
				isPrime = false;
			}
		return isPrime;
	}

	public static boolean primalityTestImprove1(BigInteger test) {
		BigInteger sqroot = sqrt(test).add(BigInteger.ONE);
		BigInteger two = BigInteger.valueOf(2L);
		if (test.mod(two).equals(BigInteger.ZERO)) {
			return false;
		}
		for (BigInteger bi = BigInteger.valueOf(3); bi.compareTo(sqroot) < 0; bi = bi.add(two))
			if (test.mod(bi).equals(BigInteger.ZERO)) {
				return false;
			}
		return true;
	}
	


	public static long usePrimalityTest(BigInteger test) {
		long start_timer = System.currentTimeMillis();
		boolean result = primalityTest(test);
		long end_timer = System.currentTimeMillis();
		System.out.println("The number: " + test + (result ? " is prime,\t" : " is not prime,\t")
				+ "Elapsed Time in milli seconds: " + (end_timer - start_timer));
		return end_timer - start_timer;
	}
	
	public static long usePrimalityTestAKS(BigInteger test) {
		long start_timer = System.currentTimeMillis();
		AKS myAKS = new AKS(test);
		long end_timer = System.currentTimeMillis();
		System.out.println("The number: " + test + (myAKS.isPrime ? " is prime,\t" : " is not prime,\t")
				+ "Elapsed Time in milli seconds: " + (end_timer - start_timer));
		return end_timer - start_timer;
	}

	public static long usePrimalityTestImprove1(BigInteger test) {
		long start_timer = System.currentTimeMillis();
		boolean result = primalityTestImprove1(test);
		long end_timer = System.currentTimeMillis();
		System.out.println("The number: " + test + (result ? " is prime,\t" : " is not prime,\t")
				+ "Elapsed Time in milli seconds: " + (end_timer - start_timer));
		return end_timer - start_timer;
	}

	public static void generateTest(int number_of_cases) throws IOException {
		ArrayList<BigInteger> numbers = new ArrayList<>();
		ArrayList<Long> times = new ArrayList<>();
		ArrayList<Boolean> is_prime = new ArrayList<>();

		for (int i = 0; i < number_of_cases; i++) {
			for (long top_exp = 3L; top_exp < 10L; top_exp++) {
				long top_l = (long) Math.pow(10L, top_exp);
				BigInteger top_limit = BigInteger.valueOf(top_l);
				System.out.println("Top limit: " + top_limit + "\tgen#" + i);
				BigInteger test = Main.getRandomBigInteger(top_limit);
				Long result = usePrimalityTest(test);
				numbers.add(test);
				times.add(result);
				is_prime.add(primalityTest(test));
			}
		}
//		JFreeCharScatterChartExample chart = new JFreeCharScatterChartExample("Test de primalidad",
//				"Coste computacional", numbers, times);
//		chart.pack();
//		chart.setVisible(true);
		exportCSV("./data.csv", numbers, times, is_prime);
//				"Coste computacional", numbers, times);
	}

	public static void generateTestImprove1(int number_of_cases) throws IOException {
		ArrayList<BigInteger> numbers = new ArrayList<>();
		ArrayList<Long> times = new ArrayList<>();
		ArrayList<Boolean> is_prime = new ArrayList<>();

		for (int i = 0; i < number_of_cases; i++) {
			for (long top_exp = 3L; top_exp < 17L; top_exp++) {
				long top_l = (long) Math.pow(10L, top_exp);
				BigInteger top_limit = BigInteger.valueOf(top_l);
				System.out.println("Top limit: " + top_limit + "\tgen#" + i);
				BigInteger test = Main.getRandomBigInteger(top_limit);
				Long result = usePrimalityTestImprove1(test);
				numbers.add(test);
				times.add(result);
				is_prime.add(primalityTestImprove1(test));
			}
		}
		exportCSV("./dataImproved.csv", numbers, times, is_prime);
	}
	
	public static void generateTestImprove1OnlyPrimes(int number_of_cases) throws IOException {
		ArrayList<BigInteger> numbers = new ArrayList<>();
		ArrayList<Long> times = new ArrayList<>();
		ArrayList<Boolean> is_prime = new ArrayList<>();

		for (int i = 0; i < number_of_cases; i++) {
			for (long top_exp = 3L; top_exp < 18L; top_exp++) {
				long top_l = (long) Math.pow(10L, top_exp);
				BigInteger top_limit = BigInteger.valueOf(top_l);
				System.out.println("Top limit: " + top_limit + "\tgen#" + i);
				BigInteger test = Main.getRandomBigPrime(top_limit);
				Long result = usePrimalityTestAKS(test);
				numbers.add(test);
				times.add(result);
				AKS myAKS = new AKS(test);
				is_prime.add(myAKS.isPrime);
			}
		}
		exportCSV("./dataImprovedPrimesAKS.csv", numbers, times, is_prime);
	}

	public static BigInteger getRandomBigInteger(BigInteger top_limit) {
		Random rand = new Random();
		BigInteger upperLimit = top_limit;
		BigInteger result;
		do {
			result = new BigInteger(upperLimit.bitLength(), rand);
		} while (result.compareTo(upperLimit) >= 0);
		return result;
	}
	
	public static BigInteger getRandomBigPrime(BigInteger top_limit) {
		BigInteger result;
		while(true) {
			result = Main.getRandomBigInteger(top_limit);
			if(primalityTestImprove1(result)) break;
		}
		return result;
	}

	public static void exportCSV(String output_folder, ArrayList<BigInteger> numbers, ArrayList<Long> times,
			ArrayList<Boolean> is_prime) throws IOException {
		FileWriter writer = new FileWriter(output_folder);

		String result = "Number,Time,isPrime\n";
		for (int i = 0; i < numbers.size(); i++) {
			result += numbers.get(i).toString() + "," + times.get(i).toString() + "," + is_prime.get(i).toString();
			if (i < numbers.size() - 1)
				result += "\n";
		}

		writer.write(result);
		writer.close();
	}

	public static void main(String[] args) throws IOException {
		//generateTestImprove1OnlyPrimes(20);
		BigInteger big = BigInteger.valueOf(7192796323328117L);
		usePrimalityTestImprove1(big);
		usePrimalityTestAKS(big);
		
		System.out.println("generate radom number:");
		generateTestImprove1OnlyPrimes(10);
		System.out.println("\n\nEND");
	}
}
