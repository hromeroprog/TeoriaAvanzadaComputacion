package tools;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Tools {

    public static void exportCSV(String output_folder, ArrayList<BigInteger> numbers, ArrayList<Long> times) throws IOException {
        FileWriter writer = new FileWriter(output_folder);

        StringBuilder result = new StringBuilder("Number,Time\n");
        for (int i = 0; i < numbers.size(); i++) {
            result.append(numbers.get(i).toString()).append(",").append(times.get(i).toString());
            if (i < numbers.size() - 1)
                result.append("\n");
        }
        writer.write(result.toString());
        writer.close();
    }

    public static BigInteger getBigIntegerFromBits(int bits, Random r){
        if (bits == 0) return BigInteger.ZERO;
        String binary = "1";
        for (int i = 0; i < bits-1; i++) binary += r.nextBoolean() ? "1" : "0";
        return new BigInteger(binary, 2);
    }
    
    public static BigInteger getRandomBigInteger(int size_of_number) {
        String str_res = "";
        str_res += ThreadLocalRandom.current().nextInt(1, 10);
        for (int i = 0; i < size_of_number-1 ; i++) {
            str_res += ThreadLocalRandom.current().nextInt(0, 10);
        }
        return new BigInteger(str_res);
    }
    
    public static BigInteger getRandomBigIntegerPrime(int size_of_number) {
    	BigInteger result = BigInteger.valueOf(ThreadLocalRandom.current().nextInt(0, 10));
    	
    	while (result.toString().length() != size_of_number) {
    		result = BigInteger.probablePrime(transformSizeToBits(size_of_number), new Random());
    	}
    	return result;
    }

    private static int transformSizeToBits(int size_of_number) {
        int bottom_limit = (int)(Math.log(Math.pow(10, size_of_number-1))/Math.log(2));
        int top_limit = (int)(Math.log(Math.pow(10, size_of_number))/Math.log(2)) + 1;

        return ThreadLocalRandom.current().nextInt(bottom_limit, top_limit + 1);
    }

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
}
