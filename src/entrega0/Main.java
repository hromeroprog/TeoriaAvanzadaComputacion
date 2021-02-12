package entrega0;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {

	public static boolean primalityTest(BigInteger test) {
		boolean isPrime = true;
		for (BigInteger bi = BigInteger.valueOf(2); bi.compareTo(test) < 0; bi = bi.add(BigInteger.ONE))
			if (test.mod(bi).equals((BigInteger.ZERO))) {
				isPrime = false;
			}
		return isPrime;
	}

	public static long usePrimalityTest(BigInteger test) {
		long start_timer = System.currentTimeMillis();
		boolean result = primalityTest(test);
		long end_timer = System.currentTimeMillis();
		System.out.println("The number: " + test + (result ? " is prime,\t" : " is not prime,\t")
				+ "Elapsed Time in milli seconds: " + (end_timer - start_timer));
		return end_timer - start_timer;
	}

	public static void generateTest(int number_of_cases, BigInteger top_limit) throws IOException {
		ArrayList<BigInteger> numbers = new ArrayList<>();
		ArrayList<Long> times = new ArrayList<>();
		for (int i = 0; i < number_of_cases; i++) {
			BigInteger test = Main.getRandomBigInteger(top_limit);
			Long result = usePrimalityTest(test);
			numbers.add(test);
			times.add(result);
		}
		
//		JFreeCharScatterChartExample chart = new JFreeCharScatterChartExample("Test de primalidad",
//				"Coste computacional", numbers, times);
//		chart.pack();
//		chart.setVisible(true);
		exportCSV(numbers, times);
//				"Coste computacional", numbers, times);
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
	
	public static void exportCSV(ArrayList<BigInteger> numbers, ArrayList<Long> times) throws IOException{
		FileWriter writer = new FileWriter("./data.csv");

	    String result = "Number,Time\n";
	    for (int i = 0; i< numbers.size(); i++) {
			result+= numbers.get(i).toString() + "," + times.get(i).toString();
			if (i < numbers.size()-1) result += "\n";
		}
	    

	    writer.write(result);
	    writer.close();
	}

	public static void main(String[] args) throws IOException{
		generateTest(50, BigInteger.valueOf(10000000));
		System.out.println("\n\nEND");
		
	}
}
