import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        Files.lines(Paths.get(args[0])).forEach(line -> {
            if (isPrime(Integer.parseInt(line)))
                System.out.println("1");
            else
                System.out.println("0");
        });
    }

    private static boolean isPrime(int num) {
        if (num < 3) return true;
        int top = (int) Math.sqrt(num) + 1;
        for (int i = 3; i < top; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
