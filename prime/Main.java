import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
//        long start = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder();
        bufferReaderToList(args[0], new LinkedList<>()).forEach(line -> stringBuilder.append(isPrime(Integer.parseInt(line))));
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("\n"));
        System.out.println(stringBuilder.toString());
//        System.out.println(System.currentTimeMillis() - start);
    }

    private static List<String> bufferReaderToList(String path, List<String> list) throws IOException {
        final BufferedReader in = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
        String line;
        while ((line = in.readLine()) != null) {
            list.add(line);
        }
        in.close();
        return list;
    }

    private static String isPrime(int num) {
        if (num <= 3) return "1\n";
        if (num % 2 == 0) return "0\n";
        if (num % 3 == 0) return "0\n";
        int top = (int) Math.sqrt(num) + 1;
        for (int i = 5; i < top; i += 2) {
            if (num % i == 0) {
                return "0\n";
            }
        }
        return "1\n";
    }
}
