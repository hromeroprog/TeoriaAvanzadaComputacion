package entrega2;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
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
        str_res += ThreadLocalRandom.current().nextInt(0, 10);
        return new BigInteger(str_res);
    }

    public static int transformSizeToBits(int size_of_number) {
        int bottom_limit = (int)(Math.log(Math.pow(10, size_of_number-1))/Math.log(2));
        int top_limit = (int)(Math.log(Math.pow(10, size_of_number))/Math.log(2)) + 1;

        return ThreadLocalRandom.current().nextInt(bottom_limit, top_limit + 1);
    }

    public static void exportCSV(String output_folder, ArrayList<BigInteger> numbers, ArrayList<Long> times,
                                 ArrayList<Boolean> primes) throws IOException {
        FileWriter writer = new FileWriter(output_folder);

        String result = "Number,Time,isPrime\n";
        for (int i = 0; i < numbers.size(); i++) {
            result += numbers.get(i).toString() + "," + times.get(i).toString() + "," + primes.get(i).toString();
            if (i < numbers.size() - 1)
                result += "\n";
        }
        writer.write(result);
        writer.close();
    }
}