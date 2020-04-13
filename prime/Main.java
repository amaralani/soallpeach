import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(args[0])));
        String[] lines = content.split(System.getProperty("line.separator"));
        for (String line : lines) {
            if (isPrime(Integer.parseInt(line)))
                System.out.println("1");
            else
                System.out.println("0");
        }
    }

    private static boolean isPrime(int num) {
        boolean isPrime = true;
        for (int divisor = 2; divisor <= num / 2; divisor++) {
            if (num % divisor == 0) {
                isPrime = false;
                break; // num is not a prime, no reason to continue checking
            }
        }
        return isPrime;
    }
}
