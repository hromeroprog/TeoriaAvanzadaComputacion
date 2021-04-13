package tools;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

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
