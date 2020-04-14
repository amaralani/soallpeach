import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Files.lines(Paths.get(args[0])).forEach(line -> stringBuilder.append(isPrime(Integer.parseInt(line))));
        System.out.println(stringBuilder.toString());
    }

    private static String isPrime(int num) {
        if (num < 3) return "1\n";
        if(num % 2 == 0) return "0\n";
        if(num % 3 == 0) return "0\n";
        if(num % 5 == 0) return "0\n";
        if(num % 7 == 0) return "0\n";
        int top = (int) Math.sqrt(num) + 1;
        for (int i = 3; i < top; i += 2) {
            if (num % i == 0) {
                return "0\n";
            }
        }
        return "1";
    }
}
