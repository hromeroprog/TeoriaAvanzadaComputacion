package entrega1;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class Tools {

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
	
	public static BigInteger getRandomBigInteger(int size_of_number) {
		String str_res = "";
		for (int i = 0; i < size_of_number-1 ; i++) {
			str_res += ThreadLocalRandom.current().nextInt(1, 10);
		}
		str_res += ThreadLocalRandom.current().nextInt(1, 10);
		return new BigInteger(str_res);
	}
	
	public static BigInteger getRandomBigPrime(int size_of_number) {
		BigInteger result;
		while(true) {
			result = getRandomBigInteger(size_of_number);
			AKS myAKS = new AKS(result);
			if(myAKS.isPrime) break;
		}
		return result;
	}
	
	public static void exportCSV(String output_folder, ArrayList<BigInteger> numbers, ArrayList<Long> times,
			ArrayList<BigInteger> reductions) throws IOException {
		FileWriter writer = new FileWriter(output_folder);

		String result = "Number,Time,isPrime\n";
		for (int i = 0; i < numbers.size(); i++) {
			result += numbers.get(i).toString() + "," + times.get(i).toString() + "," + reductions.get(i).toString();
			if (i < numbers.size() - 1)
				result += "\n";
		}
		writer.write(result);
		writer.close();
	}
}
