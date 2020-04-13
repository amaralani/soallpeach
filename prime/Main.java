import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Files.lines(Paths.get(args[0])).forEach(line -> {
            if (isPrime(Integer.parseInt(line)))
                stringBuilder.append("1");
            else
                stringBuilder.append("0");
            stringBuilder.append("\n");
        });
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("\n"));
        System.out.println(stringBuilder.toString());
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
