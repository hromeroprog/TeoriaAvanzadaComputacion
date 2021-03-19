import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class Tools {

    public static void exportCSV(String output_folder, ArrayList<Integer> numbers, ArrayList<Long> times) throws IOException {
        FileWriter writer = new FileWriter(output_folder);

        StringBuilder result = new StringBuilder("Bit_size,Time\n");
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
}
